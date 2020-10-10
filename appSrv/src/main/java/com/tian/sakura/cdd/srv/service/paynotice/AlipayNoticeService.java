package com.tian.sakura.cdd.srv.service.paynotice;

import com.tian.sakura.cdd.common.dict.EPayStatus;
import com.tian.sakura.cdd.common.dict.EPayType;
import com.tian.sakura.cdd.common.util.DateUtils;
import com.tian.sakura.cdd.db.domain.user.UserPay;
import com.tian.sakura.cdd.db.manage.user.UserPayManage;
import com.tian.sakura.cdd.srv.service.pay.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * 说明。
 *
 * @author lvzonggang
 */
@Service
public class AlipayNoticeService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserPayManage userPayManage;
    @Autowired
    private TaskPayCallBack taskPayCallBack;

    @Autowired
    private PrdOrderPayCallBack prdOrderPayCallBack;
    /**
     * 接受支付宝支付通知接口
     *  <p>状态 TRADE_SUCCESS 的通知触发条件是商户签约的产品支持退款功能的前提下，买家付款成功；
     * 交易状态 TRADE_FINISHED 的通知触发条件是商户签约的产品不支持退款功能的前提下，买家付款成功；
     *  或者，商户签约的产品支持退款功能的前提下，交易已经成功并且已经超过可退款期限。
     *  </p>
     *
     *  <ul>
     *      <li>商户需要验证该通知数据中的 out_trade_no 是否为商户系统中创建的订单号</li>
     *      <li>判断 total_amount 是否确实为该订单的实际金额（即商户订单创建时的金额）</li>
     *      <li>校验通知中的 seller_id（或者 seller_email ) 是否为 out_trade_no 这笔单据的对应的操作方</li>
     *      <li>验证 app_id 是否为该商户本身</li>
     *  </ul>
     *   上述 1、2、3、4有任何一个验证不通过，则表明本次通知是异常通知，务必忽略。
     *  正确的进行不同的业务处理，并且过滤重复的通知结果数据。
     *  在支付宝的业务通知中，只有交易通知状态为 TRADE_SUCCESS 或 TRADE_FINISHED 时，支付宝才会认定为买家付款成功。
     *
     * @param params
     */
    public void notice(Map<String,String> params) {
        String outTradeNo = params.get("out_trade_no");

        UserPay userPay = userPayManage.selectByPaySn(outTradeNo);
        if (userPay == null) {
            return ;
        } else if (userPay.getPayStatus() != EPayStatus.TO_BE_PAY.getCode()
                && userPay.getPayStatus() != EPayStatus.LOCK.getCode()) {
            return ;
        }

        //trade_status
        String tradeStatus = params.get("trade_status");
        logger.info("商户支付号[{}]的交易状态[{}]", outTradeNo, tradeStatus);
        Date payTime = null;
        if (StringUtils.isNotBlank(params.get("gmt_payment"))) {
            payTime =  DateUtils.parseStrToDate1(params.get("gmt_payment"));
        }

        if ("TRADE_SUCCESS,TRADE_FINISHED".contains(tradeStatus)) {
            // 1 领取任务支付 2 购买订单支付 3 转让任务报价 4 充值
            if (1 == userPay.getPayType()) {
                TaskPayCallBackContext context = new TaskPayCallBackContext();
                context.setUserId(userPay.getUserId());
                context.setUserPayId(userPay.getId());
                context.setUserTaskId(userPay.getRelationId());
                context.setPayType(EPayType.WX_PAY.getCode());
                context.setPayStatus(EPayStatus.PAYED);
                context.setPayTime(payTime);
                taskPayCallBack.payOk(context);
            } else if (2 == userPay.getPayType()){
                PrdOrderPayCallBackContext context = PrdOrderPayCallBackContextBuilder.buildForPayed(userPay.getRelationId());
                context.setPayType(EPayType.WX_PAY.getCode());
                context.setUserId(userPay.getUserId());
                context.setUserPayId(userPay.getId());
                context.setPayStatus(EPayStatus.PAYED);
                context.setPayTime(payTime);
                prdOrderPayCallBack.callback(context);
            }
        }
    }


}
