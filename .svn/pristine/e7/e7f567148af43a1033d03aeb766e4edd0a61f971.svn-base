package com.tian.sakura.cdd.srv.service.task;

import com.tian.sakura.cdd.common.dict.*;
import com.tian.sakura.cdd.common.exception.BizRuntimeException;
import com.tian.sakura.cdd.common.resp.RespCodeEnum;
import com.tian.sakura.cdd.common.util.BeanMapTransUtils;
import com.tian.sakura.cdd.common.util.IdGenUtil;
import com.tian.sakura.cdd.db.domain.log.UserAmountLog;
import com.tian.sakura.cdd.db.domain.user.SUser;
import com.tian.sakura.cdd.db.domain.user.UserPay;
import com.tian.sakura.cdd.db.domain.user.UserTask;
import com.tian.sakura.cdd.db.domain.userBonusLog.UserBonusLog;
import com.tian.sakura.cdd.db.domain.userTaskLine.UserTaskLine;
import com.tian.sakura.cdd.db.manage.log.UserAmountLogManage;
import com.tian.sakura.cdd.db.manage.log.UserBonusLogManage;
import com.tian.sakura.cdd.db.manage.task.TaskLineManage;
import com.tian.sakura.cdd.db.manage.user.SUserManage;
import com.tian.sakura.cdd.db.manage.user.UserPayManage;
import com.tian.sakura.cdd.db.manage.user.UserTaskLineManage;
import com.tian.sakura.cdd.db.manage.user.UserTaskManage;
import com.tian.sakura.cdd.srv.service.alipay.AliPayService;
import com.tian.sakura.cdd.srv.service.alipay.BaseAlipayMsg;
import com.tian.sakura.cdd.srv.service.params.ParamsService;
import com.tian.sakura.cdd.srv.service.pay.TaskPayCallBack;
import com.tian.sakura.cdd.srv.service.pay.TaskPayCallBackContext;
import com.tian.sakura.cdd.srv.web.task.dto.TaskOrderCreateReq;
import com.tian.sakura.cdd.srv.web.task.dto.TaskOrderCreateRspBody;
import com.tian.sakura.cdd.srv.web.task.dto.TaskOrderPayReq;
import com.tian.sakura.cdd.srv.web.task.dto.TaskOrderPayRspBody;
import com.tian.sakura.cdd.wx.message.pay.PayCallerMsg;
import com.tian.sakura.cdd.wx.message.pay.UnifiedOrderReq;
import com.tian.sakura.cdd.wx.service.WxPayService;
import com.tian.sakura.cdd.wx.util.WxUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.tian.sakura.cdd.srv.GlobalConstants.*;
import static com.tian.sakura.cdd.srv.builder.UserTaskBuilder.*;

/**
 * 用户任务金下单服务
 *
 * @author lvzonggang
 */
@Service
@Transactional
public class UserTaskOrderApiService {
    private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TaskLineManage taskLineManage;
    @Autowired
    private UserTaskManage userTaskManage;
    @Autowired
    private UserTaskLineManage userTaskLineManage;
    @Autowired
    private TaskLineDetermination taskLineDetermination;
    @Autowired
    private ParamsService paramsService;
    @Autowired
    private WxPayService wxPayService;
    @Autowired
    private AliPayService aliPayService;
    @Autowired
    private TaskPayCallBack taskPayCallBack;
    @Autowired
    private UserPayManage userPayManage;
    @Autowired
    private SUserManage userManage;
    @Autowired
    private UserAmountLogManage userAmountLogManage;
    @Autowired
    private UserBonusLogManage userBonusLogManage;
    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 清理用户的任务订单
     *    // 取消userTask 及 userTaskLine
     *             // 释放taskLine冻结的任务线数量
     *             // 取消userPay记录
     * @param user 用户
     */
    public void cleanTaskOrder(SUser user) {
        String userId = user.getId();
        // 查询用户未支付的任务订单
        List<UserTask> userUnpaidTaskList = userTaskManage.selectTaskOrderForPayByUserId(userId);
        logger.info("因用户[{}]主动下新的任务金订单，将自动取消未支付的任务金订单有[{}]条",
                userId, userUnpaidTaskList.size());
        for (UserTask userTask : userUnpaidTaskList) {
            UserPay dbUserPay = userPayManage.selectByUserIdAndBizId(userId, userTask.getId());
            //支付回调 - 取消
            TaskPayCallBackContext context = new TaskPayCallBackContext();
            context.setUserPayId(dbUserPay.getId());
            context.setRemark("商户主动下单");
            context.setUserTaskId(userTask.getId());
            context.setPayStatus(EPayStatus.CANSEL);
            context.setPayTime(new Date());
            taskPayCallBack.payFail(context);
        }

    }

    /**
     * <ul>
     * <li>确定 和 立即支付 两个事件 都需要判断 是否有足够任务 根据 已领取任务+锁定任务 来判断</li>
     * <li>/li>
     * <li>
     * <ul>立即支付:
     * <li> 生成s_user_task一条数据(锁定);</li>
     * <li>s_user_task_line N条数据（锁定）;</li>
     * <li>修改s_task_line  各任务线 锁定任务+1<</li>
     * </ul>
     * </li>
     * <li>
     * <ul>支付回调成功
     * <li>修改s_user_task（1条）</li>
     * <li> s_user_task_line（N条） 支付状态（已支付） 支付时间（sysdate）</li>
     * <li>s_user_line各任务线 锁定任务-1 领取任务+1 </li>
     * </ul>
     * </li>
     * <li>
     * 定时任务（5分钟一次 超过5分钟锁定状态数据） 修改为 未支付
     * 然后 s_task_line各任务线 锁定任务-1
     * </li>
     * </ul>
     *
     * @param user
     * @param req
     */
    @Transactional
    public TaskOrderCreateRspBody createTaskOrder(SUser user, TaskOrderCreateReq req) {
        TaskOrderCreateRspBody result = new TaskOrderCreateRspBody();

        String userId = user.getId();

        //下单处理
        String userTaskId = doCreateTaskOrder(user, req);

        //创建支付信息
        doCreateUserPay(userId, userTaskId, req);

        result.setUserTaskId(userTaskId);
        // 支付截止时间
        Object value = paramsService.getValue(PARAM_KEY_TASK_ORD_PAYTIME);
        long payEndTime = 0L;
        if (value == null) {
            payEndTime = 30 * 1000; //30分
        } else {
            payEndTime = Long.valueOf(value.toString())  * 1000;
        }
        // 秒
        result.setPayEndTime(payEndTime);
        return result;
    }

    private String doCreateTaskOrder(SUser user, TaskOrderCreateReq req) {
        String userId = user.getId();
        //生成 s_user_task记录 锁定
        UserTask userTask = buildNewUserTask(userId, req);
        userTaskManage.insert(userTask);
        logger.info("用户[{}]购买任务- userTask[{}]持久化完毕", user.getUserPhone(), userTask.getId());

        // 根据任务数量，查询产品任务线数据
        int taskCnt = req.getBody().getTaskCount();
        List<String> prdTaskLineIds =
                taskLineDetermination.determinTaskLines(user, req.getBody().getProductId(), req.getBody().getTaskCount());
        // s_task_line  各任务线 锁定任务+1
        taskLineManage.lockTaskByIds(prdTaskLineIds);

        // 任务线s_user_task_line N条数据（锁定）;
        List<UserTaskLine> userTaskLineList = buildNewUserTaskLine(userId, req, userTask.getId(), prdTaskLineIds);
        userTaskLineManage.batchInsert(userTaskLineList);

        return userTask.getId();
    }

    private void doCreateUserPay(String userId, String taskId, TaskOrderCreateReq req) {
        UserPay userPay = buildNewUserPay(userId, taskId, req);
        userPayManage.insert(userPay);
    }

    /**
     * 如果是微信支付，返回预订单id
     * 如果是支付宝支付，返回处理好的签名字符串
     * 如果是账户支付，执行正常数据
     *
     * @param user
     * @param req
     */
    @Transactional
    public TaskOrderPayRspBody payTaskOrder(SUser user, TaskOrderPayReq req) {
        TaskOrderPayRspBody rspBody = new TaskOrderPayRspBody();
        String payType = req.getBody().getPayType();
        String userId = user.getId();
        String userTaskId = req.getBody().getTaskId();
        //  查找或创建 支付信息
        UserPay dbUserPay = checkUserPay(user, req);
        String payNo = dbUserPay.getPaySn();
        logger.info("用户[{}]查询到的任务金支付编号[{}]", userId, payNo);
        //支付金额
        BigDecimal payAmount = dbUserPay.getPayAmount();

        String taskBean = (String)paramsService.getValue(PARAM_KEY_ORDER_BEAN_CNT);

        //支付号
        if (Integer.valueOf(payType) == EPayType.WX_PAY.getCode()) {
            //金额处理成分
            long totalFee = payAmount.multiply(new BigDecimal(100)).longValue();
            //do WxPay
            UnifiedOrderReq unifiedOrderReq = UnifiedOrderReq.builder()
                    .appid(paramsService.getValueNoCache(PARAM_KEY_OPEN_APP_ID))
                    .mchId(paramsService.getValueNoCache(PARAM_KEY_WX_PAY_MCH_ID))
                    .nonceStr(IdGenUtil.uuid())
                    .body("拆哆哆商户平台")
                    .outTradeNo(payNo)
                    .totalFee(String.valueOf(totalFee))
                    .spbillCreateIp(req.getBody().getIp())
                    .notifyUrl(paramsService.getValueNoCache(PARAM_KEY_WX_PAY_NOTICY_URL))
                    .tradeType("APP")
                    .build();
            String key = paramsService.getValueNoCache(PARAM_KEY_WX_PAY_MCH_KEY);
            String sign = WxUtil.generateSignature(BeanMapTransUtils.transBean2Map(unifiedOrderReq, false), key);
            unifiedOrderReq.setSign(sign);
            String preOrderId = wxPayService.unifiedOrder(unifiedOrderReq);
            //String preOrderId = "test12312312fwwsfwe";
            PayCallerMsg payCallerMsg = doWxPay(preOrderId);
            rspBody.setWxPayMsg(payCallerMsg);
        } else if (Integer.valueOf(payType) == EPayType.ALI_PAY.getCode()) {
            // TODO
            BaseAlipayMsg alipayMsgMsg = new BaseAlipayMsg();
            alipayMsgMsg.setOutTradeNo(payNo);
            alipayMsgMsg.setGoodsDes(payNo);
            alipayMsgMsg.setGoodsTitle("拆哆哆商户平台");
            alipayMsgMsg.setGoodsType("0");
            //baseRechargeMsg.setNoticeUrl("");
            //alipayMsgMsg.setFeeRate(feeRate);
            alipayMsgMsg.setTotalAmt(payAmount);
            Map<String, String> orderInfoMap = aliPayService.pay(alipayMsgMsg);

            rspBody.setAliPayMsg(orderInfoMap.get("orderInfo"));
        } else if (Integer.valueOf(payType) == EPayType.BALANCE.getCode()) {
            //检查余额是否充足；
            SUser dbUser = userManage.selectByPrimary(userId);
            // 可用余额
            BigDecimal balance = dbUser.getTotalAmount();
            balance = balance == null ? BigDecimal.ZERO : balance;
            if (payAmount.compareTo(balance) == 1) {
                throw new BizRuntimeException(RespCodeEnum.BLANCE_ACCT_NOT_ENOUGH);
            }

            //拆豆

            // 实际支付金额大于零时，记录账户流水及变化
            if (payAmount.compareTo(BigDecimal.ZERO) == 1) {
                // 记录账户变动流水
                doAmountLog(dbUser, payAmount, userTaskId);
                // 记录任务金冻结金额- 根据任务数量，
                doBonusLog(dbUser, userTaskId);
            }
            // 记录猎豆流水 TODO

            // 账户可用资金减少， 冻结资金添加
            SUser updUser = new SUser();
            updUser.setId(userId);
            updUser.setTotalAmount(dbUser.getTotalAmount().subtract(payAmount));
            logger.info("用户[{}]可用金额[{}], 本次任务金冻结金额[{}]", userId, dbUser.getTotalAmount(), payAmount);
            updUser.setLockAmount(dbUser.getLockAmount().add(payAmount));
            updUser.setRewardBean(dbUser.getRewardBean() + Integer.valueOf(taskBean));
            userManage.updateByPrimaryKeySelective(updUser);


            //支付成功的通知
            TaskPayCallBackContext context = new TaskPayCallBackContext();
            context.setUserId(userId);
            context.setUserPayId(dbUserPay.getId());
            context.setUserTaskId(userTaskId);
            context.setPayType(Integer.valueOf(req.getBody().getPayType()));
            context.setPayStatus(EPayStatus.PAYED);
            context.setPayTime(new Date());
            taskPayCallBack.payOk(context);

            applicationContext.publishEvent(context);
        }

        rspBody.setPayAmount(payAmount);
        // TODO
        rspBody.setBenifitAmt(BigDecimal.ZERO);
        rspBody.setBeanCnt(Integer.valueOf(taskBean));

        return rspBody;
    }

    private void doAmountLog(SUser user,BigDecimal payAmount, String userTaskId) {


        UserAmountLog amountLog = new UserAmountLog();
        amountLog.setId(IdGenUtil.uuid());
        amountLog.setChangeAmount(payAmount.negate());
        amountLog.setUserId(user.getId());
        amountLog.setChangeType(EAmtChangeType.PAY_TASK.getCode());
        amountLog.setChangeTime(new Date());
        amountLog.setOldAmount(user.getTotalAmount());
        amountLog.setRelationId(userTaskId);
        amountLog.setAcctStatus(EAcctType.BALANCE.getCode());
        amountLog.setRemark("支付任务金，扣减可用余额");
        userAmountLogManage.insert(amountLog);
    }
    private void doBonusLog(SUser user,String userTaskId) {
        List<UserTaskLine> taskLineList = userTaskLineManage.selectByTaskId(userTaskId);
        for (UserTaskLine userTaskLine : taskLineList) {
            UserBonusLog bonusLog = new UserBonusLog();
            bonusLog.setId(IdGenUtil.uuid());
            bonusLog.setUserId(user.getId());
            bonusLog.setUserTaskLineId(userTaskLine.getId());
            bonusLog.setTaskLineId(userTaskLine.getTaskLineId());
            bonusLog.setProductId(userTaskLine.getProductId());

            bonusLog.setBonusType(EBonusType.TASK_OUT.getCode());
            bonusLog.setCreateTime(new Date());
            bonusLog.setUpdateTime(new Date());
            bonusLog.setBonusAmount(userTaskLine.getPayAmount());
            bonusLog.setLogStatus(0);
            bonusLog.setRemark("支付任务金，增加冻结金额");
            userBonusLogManage.insert(bonusLog);
        }

    }

    /**
     * 用户取消支付
     *
     * @param user
     * @param req
     */
    public void cancelPayTaskOrder(SUser user, TaskOrderPayReq req) {
        String taskId = req.getBody().getTaskId();
        //检查支付状态是否满足
        //根据订单支付号，查询任务金支付的业务主键 user_task记录
        UserTask dbUserTask = userTaskManage.selectByPrimary(taskId);
        if (dbUserTask == null) {
            logger.warn("未查询到关联的业务记录-用户任务记录[{}]", taskId);
            return;
        }
        //查看任务是否属于当前登录者
        if (!StringUtils.equals(dbUserTask.getUserId(), user.getId())) {
            throw new BizRuntimeException(RespCodeEnum.NOT_OPERATE_RECORED);
        }
        //状态是否支持取消操作
        if (dbUserTask.getPayStatus() != EPayStatus.TO_BE_PAY.getCode()) {
            throw new BizRuntimeException(RespCodeEnum.STATUS_NOT_MATCH_OPERATE, "取消支付");
        }
        //支付记录
        UserPay dbUserPay = userPayManage.selectByUserIdAndBizId(user.getId(), taskId);
        if (dbUserPay == null) {
            return ;
        }

        //支付回调
        TaskPayCallBackContext context = new TaskPayCallBackContext();
        context.setUserPayId(dbUserPay.getId());
        context.setUserTaskId(req.getBody().getTaskId());
        context.setPayStatus(EPayStatus.CANSEL);
        context.setPayTime(new Date());
        context.setRemark("手工取消");
        taskPayCallBack.payFail(context);

    }

    private UserPay checkUserPay(SUser user, TaskOrderPayReq req) {
        String userId = user.getId();
        String taskId = req.getBody().getTaskId();
        UserPay dbUserPay = userPayManage.selectByUserIdAndBizId(userId, taskId);
        if (dbUserPay == null) {
            throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE, "支付订单");
        } else {
            //校验状态
            if (dbUserPay.getPayStatus() == EPayStatus.LOCK.getCode()) {
                throw new BizRuntimeException(RespCodeEnum.CHECK_PAYING);
            }
            if (Integer.valueOf(req.getBody().getPayType()) != EPayType.BALANCE.getCode()) {
                //更新支付方式 和 状态
                UserPay updUserPay = new UserPay();
                updUserPay.setId(dbUserPay.getId());
                updUserPay.setPayStatus(EPayStatus.LOCK.getCode());
                // updUserPay.setPayType(Integer.valueOf(req.getBody().getPayType()));
                userPayManage.updateByPrimaryKeySelective(updUserPay);
            }
        }
        return dbUserPay;
    }

    private PayCallerMsg doWxPay(String preOrderId) {
        //构建支付调用请求消息体
        PayCallerMsg payCallerMsg = new PayCallerMsg();
        payCallerMsg.set_package("Sign=WXPay");
        payCallerMsg.setAppid(paramsService.getValueNoCache(PARAM_KEY_OPEN_APP_ID));
        payCallerMsg.setPartnerid(paramsService.getValueNoCache(PARAM_KEY_WX_PAY_MCH_ID));
        payCallerMsg.setPrepayid(preOrderId);
        //标准北京时间，时区为东八区，自1970年1月1日 0点0分0秒以来的秒数
        long nowTime = System.currentTimeMillis() / 1000;
        payCallerMsg.set_timestamp(String.valueOf(nowTime));
        payCallerMsg.setNoncestr(IdGenUtil.uuid());

        Map<String, String> paramMap = toMap(payCallerMsg);
        String key = paramsService.getValueNoCache(PARAM_KEY_WX_PAY_MCH_KEY);

        try {
            String sign = WxUtil.generateSignature(paramMap, key);
            payCallerMsg.setSign(sign);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return payCallerMsg;
    }

    private Map<String, String> toMap(PayCallerMsg payCallerMsg) {
        Map<String, String> paramMap = new HashMap<>();

        paramMap.put("appid", payCallerMsg.getAppid());
        paramMap.put("partnerid", payCallerMsg.getPartnerid());
        paramMap.put("prepayid", payCallerMsg.getPrepayid());
        paramMap.put("package", payCallerMsg.get_package());
        paramMap.put("noncestr", payCallerMsg.getNoncestr());
        paramMap.put("timestamp", payCallerMsg.get_timestamp());

        return paramMap;
    }

}
