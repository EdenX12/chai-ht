package com.tian.sakura.cdd.srv.service.user;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tian.sakura.cdd.common.dict.EAmtChangeQueryType;
import com.tian.sakura.cdd.common.dict.EAmtChangeType;
import com.tian.sakura.cdd.common.util.DateUtils;
import com.tian.sakura.cdd.db.domain.log.UserAmountLog;
import com.tian.sakura.cdd.db.domain.user.SUser;
import com.tian.sakura.cdd.db.manage.log.AmountLogQueryVo;
import com.tian.sakura.cdd.db.manage.log.UserAmountLogManage;
import com.tian.sakura.cdd.db.manage.msg.UserMsgManage;
import com.tian.sakura.cdd.db.manage.task.TaskLineManage;
import com.tian.sakura.cdd.db.manage.user.SUserManage;
import com.tian.sakura.cdd.db.manage.user.UserCouponManage;
import com.tian.sakura.cdd.srv.builder.LoginBuilder;
import com.tian.sakura.cdd.srv.web.login.dto.LoginReq;
import com.tian.sakura.cdd.srv.web.login.dto.LoginRsp;
import com.tian.sakura.cdd.srv.web.user.dto.AmountLogQueryReq;
import com.tian.sakura.cdd.srv.web.user.dto.AmountLogQueryRsqBody;
import com.tian.sakura.cdd.srv.web.user.dto.UserMsgQueryReq;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 说明。
 *
 * @author lvzonggang
 */
@Service
@Transactional
public class UserApiService {

    private transient Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SUserManage userManage;

    @Autowired
    private UserAmountLogManage amountLogManage;

    @Autowired
    private UserMsgManage userMsgManage;

    @Autowired
    private UserInfoDecorator userInfoDecorator;

    public LoginRsp refresh(SUser loginUser) {
        LoginRsp loginRsp = LoginBuilder.instance().buildLoginRsp(loginUser);
        //计算接收任务上限，当前接收的任务数，优惠券等信息
        userInfoDecorator.decorate(loginUser, loginRsp);
        return loginRsp;
    }

    public void updateNickName(String nickName, String userId) {
        SUser user = new SUser();
        user.setId(userId);
        user.setNickName(nickName);
        userManage.updateByPrimaryKeySelective(user);
    }

    /**
     * 查询用户收益明细记录
     *
     * @param user 用户信息
     * @param req 请求体
     * @return
     */
    public PageInfo<AmountLogQueryRsqBody> queryAmountLog(SUser user ,AmountLogQueryReq req) {
        PageHelper.startPage(req.getBody().getPageNum(), req.getBody().getPageSize());

        AmountLogQueryVo queryVo = new AmountLogQueryVo();
        queryVo.setUserId(user.getId());
        if (req.getBody().getChangeType() != null) {
            toChangeType(req.getBody().getChangeType(), queryVo);
        }

        queryVo.setAmtDirect(req.getBody().getAmtDirect());
        if (req.getBody().getBeginDate() != null) {
            String strBeginDate = DateUtils.formatDateWithyyyy_MM_dd(req.getBody().getBeginDate());
            queryVo.setBeginDate(DateTime.parse(strBeginDate.concat("T00:00:01")).toDate());
        }
        if (req.getBody().getEndDate() != null) {
            String strBeginDate = DateUtils.formatDateWithyyyy_MM_dd(req.getBody().getEndDate());
            queryVo.setEndDate(DateTime.parse(strBeginDate.concat("T23:59:59")).toDate());
        }
        List<UserAmountLog> dataList =  amountLogManage.selectByPage(queryVo);
        PageInfo<UserAmountLog> pageInfo = PageInfo.of(dataList);

        //转化处理
        PageInfo<AmountLogQueryRsqBody> result = new PageInfo<>();
        BeanUtils.copyProperties(pageInfo, result);
        List<AmountLogQueryRsqBody> bodyList = new ArrayList<>();
        for (UserAmountLog item : dataList) {
            AmountLogQueryRsqBody body = AmountLogQueryRsqBody.builder()
                    .id(item.getId())
                    .changeType(item.getChangeType())
                    .changeAmount(item.getChangeAmount())
                    .changeTime(item.getChangeTime().getTime())
                    .oldAmount(item.getOldAmount())
                    .changeName(EAmtChangeType.getNameFromCode(item.getChangeType()))
                    .build();
            bodyList.add(body);
        }
        result.setList(bodyList);
        return result;

    }

    private void toChangeType(int queryType, AmountLogQueryVo queryVo) {
        if (queryType == EAmtChangeQueryType.BUY_RTN_CASH.getCode()) {
            queryVo.setChangeType(EAmtChangeType.BUY_RTN_CASH.getCode());
        } else if (queryType == EAmtChangeQueryType.REWARD.getCode()) {
            StringBuilder sb = new StringBuilder("(")
                    .append(EAmtChangeType.TASK_LINE_REWARD.getCode()).append(",")
                    .append(EAmtChangeType.VER_REWARD.getCode())
                    .append(",")
                    .append(EAmtChangeType.HOR_REWARD.getCode())
                    .append(")");
            queryVo.setChangeTypeValues(sb.toString());
        } else if (queryType == EAmtChangeQueryType.RTN_TASK.getCode()) {
            queryVo.setChangeType(EAmtChangeType.RETURN_TASK.getCode());
        } else if (queryType == EAmtChangeQueryType.WITHDRAW.getCode()) {
            queryVo.setChangeType(EAmtChangeType.WITHDRAW.getCode());
        } else if (queryType == EAmtChangeQueryType.PAY.getCode()){
            StringBuilder sb = new StringBuilder("(")
                    .append(EAmtChangeType.PAY_GOODS.getCode()).append(",")
                    .append(EAmtChangeType.PAY_TASK.getCode())
                    .append(")");
            queryVo.setChangeTypeValues(sb.toString());
        }
    }

    /**
     * 查询用户的消息记录
     *
     * @param user 用户信息
     * @return
     */
    public PageInfo selectUserMsg(SUser user, UserMsgQueryReq req) {
        return userMsgManage.selectByUserId(user.getId(), req.getBody().getPageNum(), req.getBody().getPageSize());
    }
}
