package com.tian.sakura.cdd.srv.service.user;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tian.sakura.cdd.common.dict.EAcctType;
import com.tian.sakura.cdd.common.dict.EAmtChangeType;
import com.tian.sakura.cdd.common.dict.EWithdrawStatus;
import com.tian.sakura.cdd.common.exception.BizRuntimeException;
import com.tian.sakura.cdd.common.resp.RespCodeEnum;
import com.tian.sakura.cdd.common.util.DateUtils;
import com.tian.sakura.cdd.common.util.IdGenUtil;
import com.tian.sakura.cdd.db.domain.base.Bank;
import com.tian.sakura.cdd.db.domain.log.UserAmountLog;
import com.tian.sakura.cdd.db.domain.user.SUser;
import com.tian.sakura.cdd.db.domain.user.UserBank;
import com.tian.sakura.cdd.db.domain.user.UserWithdraw;
import com.tian.sakura.cdd.db.manage.base.BankManage;
import com.tian.sakura.cdd.db.manage.log.UserAmountLogManage;
import com.tian.sakura.cdd.db.manage.user.SUserManage;
import com.tian.sakura.cdd.db.manage.user.UserBankManage;
import com.tian.sakura.cdd.db.manage.user.UserWithdrawManage;
import com.tian.sakura.cdd.db.manage.user.vo.WithdrawQueryVo;
import com.tian.sakura.cdd.srv.web.user.dto.WithdrawApplyReq;
import com.tian.sakura.cdd.srv.web.user.dto.WithdrawQueryReq;
import com.tian.sakura.cdd.srv.web.user.dto.WithdrawQueryRsqBody;
import com.tian.sakura.cdd.srv.web.user.dto.WithdrawRspBody;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 说明。
 *
 * @author lvzonggang
 */

@Service
@Transactional
public class WithdrawApiService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BankManage bankManage;
    @Autowired
    private UserBankManage userBankManage;
    @Autowired
    private UserWithdrawManage withdrawManage;
    @Autowired
    private SUserManage userManage;
    @Autowired
    private UserAmountLogManage amountLogManage;

    public PageInfo<WithdrawRspBody> list(SUser user, WithdrawQueryReq req) {
        PageHelper.startPage(req.getBody().getPageNum(), req.getBody().getPageSize());

        WithdrawQueryVo queryVo = doBuildQueryVo(user, req);
        List<UserWithdraw> withdrawList = withdrawManage.selectByPage(queryVo);
        PageInfo<UserWithdraw> pageInfo = PageInfo.of(withdrawList);

        //转化成页面所需数据
        return toPage(pageInfo);
    }

    private WithdrawQueryVo doBuildQueryVo(SUser user, WithdrawQueryReq req) {
        Date beginDate = null;
        Date endDate = null;
        if (req.getBody().getBeginDate() != null) {
            String strBeginDate = DateUtils.formatDateWithyyyy_MM_dd(req.getBody().getBeginDate());
            beginDate = DateTime.parse(strBeginDate.concat("T00:00:01")).toDate();
        }
        if (req.getBody().getEndDate() != null) {
            String strBeginDate = DateUtils.formatDateWithyyyy_MM_dd(req.getBody().getEndDate());
            endDate = DateTime.parse(strBeginDate.concat("T23:59:59")).toDate();
        }

        WithdrawQueryVo queryVo = WithdrawQueryVo.builder()
                .userId(user.getId())
                .beginDate(beginDate)
                .endDate(endDate)
                .withdrawStatus(req.getBody().getWithdrawStatus())
                .build();
        return queryVo;
    }

    private PageInfo<WithdrawRspBody> toPage(PageInfo<UserWithdraw> pageInfo) {
        PageInfo<WithdrawRspBody> result = new PageInfo<>();
        BeanUtils.copyProperties(pageInfo, result);
        //copy list数据
        List<WithdrawRspBody>  rspBodies = new ArrayList<>();
        pageInfo.getList().forEach(item -> {
            WithdrawRspBody body = WithdrawRspBody.builder()
                    .amount(item.getAmount())
                    .createTime(item.getCreateTime().getTime())
                    .withdrawStatus(item.getWithdrawStatus())
                    .withdrawStatusName(EWithdrawStatus.getNameByCode(item.getWithdrawStatus()))
                    .build();
            rspBodies.add(body);
        });

        result.setList(rspBodies);
        return result;
    }

    /**
     * 申请提现
     *    提现表s_user_withdraw 增加记录
     *    suser  totalAmount 减少， lockAmount 增肌
     *    记录 流水 s_user_amount_log
     *
     * @param user
     * @param req
     */
    public void applyWithdraw(SUser user, WithdrawApplyReq req) {
        //银行卡id
        String userBankId = req.getBody().getUserBankId();
        // 校验金额
        BigDecimal amount = req.getBody().getAmount();
        if (amount.compareTo(BigDecimal.ZERO) <=0) {
            throw new BizRuntimeException(RespCodeEnum.AMOUNT_MORE_THAN_ZERO, "提现");
        }
        // TODO 考虑使用数据库悲观锁
        SUser dbUser = userManage.selectByPrimary(user.getId());

        BigDecimal totalAmount = dbUser.getTotalAmount();

        if (totalAmount.compareTo(amount) < 0) {
            throw new BizRuntimeException(RespCodeEnum.BLANCE_ACCT_NOT_ENOUGH);
        }

        //查询银行卡数据
        UserBank dbUserBank = userBankManage.selectByPrimary(userBankId);
        if (dbUserBank == null) {
            throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE,"用户银行卡");
        }
        if (!dbUserBank.getUserId().equals(user.getId())) {
            throw new BizRuntimeException(RespCodeEnum.NOT_QUERY_RECORED);
        }
        Bank bank = bankManage.selectByPrimary(dbUserBank.getBankId());

        // 插入提现记录 s_user_withdraw
        String bizId = doInsertWithdraw(user, req, bank, dbUserBank);

        // 冻结账号提现金额 s_user  totalAmount
        doUpdateAcct(dbUser, amount);
        // 记录金额变化流水
        doAmountLog(dbUser, amount, bizId);
    }

    private String doInsertWithdraw(SUser user,WithdrawApplyReq req, Bank bank, UserBank userBank) {
        UserWithdraw withdraw = new UserWithdraw();
        withdraw.setId(IdGenUtil.uuid());
        withdraw.setUserId(user.getId());
        withdraw.setBankCode(bank.getBankCode());
        withdraw.setBankName(bank.getBankName());

        withdraw.setCardNum(userBank.getCardNum());
        withdraw.setRealName(userBank.getRealName());
        withdraw.setIdCard(userBank.getIdCard());

        withdraw.setAmount(req.getBody().getAmount());
        withdraw.setWithdrawStatus(EWithdrawStatus.APPLY.getCode());

        withdrawManage.insert(withdraw);
        logger.info("用户[id={}]申请提现[id={},amount={}]",
                user.getId(), withdraw.getId(), req.getBody().getAmount());
        return withdraw.getId();
    }

    private void doUpdateAcct(SUser dbUser, BigDecimal amount) {
        SUser updUser = new SUser();
        updUser.setId(dbUser.getId());
        updUser.setTotalAmount(dbUser.getTotalAmount().subtract(amount));
        updUser.setLockAmount(dbUser.getLockAmount().add(amount));
        userManage.updateByPrimaryKeySelective(updUser);
        logger.info("用户[id={}]因申请提现引起余额变化[提现前总余额={},提现金额={}]",
                dbUser.getId(), dbUser.getTotalAmount(), amount);
        logger.info("用户[id={}]因申请提现引起余额变化[提现前总冻结额={},提现金额={}]",
                dbUser.getId(), dbUser.getLockAmount(), amount);
    }

    private void doAmountLog(SUser dbuser, BigDecimal amount, String bizId) {
        UserAmountLog amountLog = new UserAmountLog();
        amountLog.setId(IdGenUtil.uuid());
        amountLog.setChangeAmount(amount.negate());
        amountLog.setUserId(dbuser.getId());
        amountLog.setChangeType(EAmtChangeType.WITHDRAW.getCode());
        amountLog.setChangeTime(new Date());
        amountLog.setOldAmount(dbuser.getTotalAmount());
        amountLog.setRelationId(bizId);
        amountLog.setAcctStatus(EAcctType.BALANCE.getCode());
        amountLog.setRemark("提现申请，扣减可用余额");
        amountLogManage.insert(amountLog);
    }
}
