package com.tian.sakura.cdd.srv.service.paynotice;

import com.tian.sakura.cdd.common.dict.EPayStatus;
import com.tian.sakura.cdd.common.dict.EPayType;
import com.tian.sakura.cdd.common.util.DateUtils;
import com.tian.sakura.cdd.db.domain.user.UserPay;
import com.tian.sakura.cdd.db.manage.user.UserPayManage;
import com.tian.sakura.cdd.srv.service.pay.*;
import com.tian.sakura.cdd.wx.message.pay.PayNoticeMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

/**
 * 说明。
 *
 * @author lvzonggang
 */
@Service

public class WxPayNoticeService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserPayManage userPayManage;
    @Autowired
    private TaskPayCallBack taskPayCallBack;

    @Autowired
    private PrdOrderPayCallBack prdOrderPayCallBack;

    public void notic(PayNoticeMsg payNoticeMsg) {
        String outTradeNo = payNoticeMsg.getOut_trade_no();
        logger.info("商户支付订单号-{}", outTradeNo);
        //根据支付号查询userPay数据
        UserPay userPay = userPayManage.selectByPaySn(outTradeNo);
        if (userPay == null) {
            logger.warn("微信支付通知-外部订单号{}不存在", outTradeNo);
            return ;
        }
        // 校验金额是否一致
        int totalFee = payNoticeMsg.getTotal_fee();
        int payAmt = userPay.getPayAmount().multiply(new BigDecimal(100))
                .setScale(0,RoundingMode.HALF_UP).intValue();
        if (totalFee != payAmt) {
            logger.warn("微信支付通知-外部订单号{},通知金额和订单金额不匹配", outTradeNo, totalFee, payAmt);
            return;
        }
        // 支付时间
        String payEndTime = payNoticeMsg.getTime_end();
        Date payTime = DateUtils.parseStrToDate1(payEndTime, "yyyyMMddHHmmss");

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
