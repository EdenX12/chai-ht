package com.tian.sakura.cdd.srv.service.order;

import com.tian.sakura.cdd.common.dict.*;
import com.tian.sakura.cdd.common.exception.BizRuntimeException;
import com.tian.sakura.cdd.common.resp.RespCodeEnum;
import com.tian.sakura.cdd.common.util.BeanMapTransUtils;
import com.tian.sakura.cdd.common.util.IdGenUtil;
import com.tian.sakura.cdd.db.domain.log.UserAmountLog;
import com.tian.sakura.cdd.db.domain.order.Order;
import com.tian.sakura.cdd.db.domain.order.OrderDetail;
import com.tian.sakura.cdd.db.domain.order.OrderProduct;
import com.tian.sakura.cdd.db.domain.user.SUser;
import com.tian.sakura.cdd.db.domain.user.UserPay;
import com.tian.sakura.cdd.db.domain.userBonusLog.UserBonusLog;
import com.tian.sakura.cdd.db.manage.log.UserAmountLogManage;
import com.tian.sakura.cdd.db.manage.log.UserBonusLogManage;
import com.tian.sakura.cdd.db.manage.order.OrderDetailManage;
import com.tian.sakura.cdd.db.manage.order.OrderManage;
import com.tian.sakura.cdd.db.manage.order.OrderProductManage;
import com.tian.sakura.cdd.db.manage.product.ProductSpecManage;
import com.tian.sakura.cdd.db.manage.user.SUserManage;
import com.tian.sakura.cdd.db.manage.user.UserPayManage;
import com.tian.sakura.cdd.srv.GlobalConstants;
import com.tian.sakura.cdd.srv.builder.PrdOrderBuilder;
import com.tian.sakura.cdd.srv.filter.LoginUserThreadLocal;
import com.tian.sakura.cdd.srv.service.alipay.AliPayService;
import com.tian.sakura.cdd.srv.service.alipay.BaseAlipayMsg;
import com.tian.sakura.cdd.srv.service.params.ParamsService;
import com.tian.sakura.cdd.srv.service.pay.PrdOrderPayCallBack;
import com.tian.sakura.cdd.srv.service.pay.PrdOrderPayCallBackContext;
import com.tian.sakura.cdd.srv.service.pay.PrdOrderPayCallBackContextBuilder;
import com.tian.sakura.cdd.srv.web.order.dto.*;
import com.tian.sakura.cdd.wx.message.pay.PayCallerMsg;
import com.tian.sakura.cdd.wx.message.pay.UnifiedOrderReq;
import com.tian.sakura.cdd.wx.service.WxPayService;
import com.tian.sakura.cdd.wx.util.WxUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

import static com.tian.sakura.cdd.srv.GlobalConstants.*;

/**
 * 产品下单服务磊
 *
 * @author lvzonggang
 */
@Service
@Transactional
public class PrdOrderApiServcie {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private OrderManage orderManage;
    @Autowired
    private ProductSpecManage productSpecManage;
    @Autowired
    private OrderDetailManage orderDetailManage;
    @Autowired
    private OrderProductManage orderProductManage;
    @Autowired
    private PrdOrderBuilder prdOrderBuilder;
    @Autowired
    private UserPayManage userPayManage;
    @Autowired
    private SUserManage userManage;
    @Autowired
    private UserAmountLogManage userAmountLogManage;

    @Autowired
    private PrdOrderPayCallBack orderPayCallBack;
    @Autowired
    private UserBonusLogManage userBonusLogManage;
    @Autowired
    private ParamsService paramsService;
    @Autowired
    private WxPayService wxPayService;
    @Autowired
    private AliPayService aliPayService;
    /**
     * 下单方法
     * 1.校验
     * 2.创建订单逻辑
     * 减库存
     * s_order表记录
     * s_order_detail
     * s_order_product
     * s_user_pay
     *
     * @param user
     * @param req
     */
    public String createPrdOrder(SUser user, PrdOrderCreateReq req) {
        String userId = user.getId();
        //校验参数

        //s_order
        Order order = PrdOrderBuilder.buildNewOrder(userId, req);
        orderManage.insert(order);

        String orderId = order.getId();
        //s_order_detail s_order_product
        List<OrderDetail> orderDetailList = prdOrderBuilder.buildNewOrderDetail(userId, orderId, req);
        orderDetailList.forEach(item -> {
            orderDetailManage.insert(item);
            logger.info("用户[{}]下单-订单明细[id={}]数持久化完毕", user.getId(), item.getId());
            orderProductManage.batchInsertPrd(item.getOrderProductList());
            logger.info("用户[{}]下单-订单产品[size={}]数持久化完毕",
                    user.getId(), item.getOrderProductList().size());
            // 扣减商品库存- 冻结
            Map<String, Integer> prdStockMap = item.getPrdStockMap();
            for(Map.Entry<String, Integer> entry : prdStockMap.entrySet()){
                Integer prdSpecId = Integer.valueOf(entry.getKey());
                productSpecManage.decreaseStock(prdSpecId, entry.getValue());
            }


        });

        // s_user_pay
        UserPay userPay = prdOrderBuilder.buildNewUserPay(userId, orderId, req);
        userPayManage.insert(userPay);
        logger.info("用户[{}]下单-支付数据[{}]持久化完毕！", user.getId(), userPay.getPaySn());

        return orderId;
    }

    /**
     * 支付订单
     * 根据订单id，查询订单信息
     * 校验数据是否存在
     * 校验数据是否属于当前登录者
     * 支付回调- 支付成功
     *
     * @param user   用户信息
     * @param payReq 支付请求
     */
    public PrdOrderPayRspBody payPrdOrder(SUser user, PrdOrderPayReq payReq) {
        PrdOrderPayRspBody body = new PrdOrderPayRspBody();
        String ip = LoginUserThreadLocal.getIp();
        String userId = user.getId();
        String orderId = payReq.getBody().getOrderId();
        Order dbOrder = doCheckOrder(orderId, userId, EPayStatus.TO_BE_PAY, "支付订单");
        // 账户支付
        UserPay userPay = userPayManage.selectByRelationId(orderId);
        // 订单金额
        BigDecimal orderAmount = userPay.getTotalAmount();
        // 购买返现
        Object radio = paramsService.getValue(GlobalConstants.BUYER_RATE);
        BigDecimal rtnCash = orderAmount.multiply(new BigDecimal(radio.toString()));

        // 支付方式
        Integer payType = payReq.getBody().getPayType();
        if (EPayType.BALANCE.getCode() == payType) {
            // 账户支付
            SUser dbUser = userManage.selectByPrimary(userId);
            // 对比可用余额是否充足
            if (dbUser.getTotalAmount().compareTo(dbOrder.getPayAmount()) < 0) {
                throw new BizRuntimeException(RespCodeEnum.BLANCE_ACCT_NOT_ENOUGH);
            }
            // 扣余额 记录账户流水
            doAmountLog(dbUser, dbOrder.getPayAmount(), orderId);
            // 冻结返现金额
            //doBonusLog(dbUser, rtnCash, orderId);
            SUser updUser = new SUser();
            updUser.setId(userId);
            updUser.setTotalAmount(dbUser.getTotalAmount().subtract(dbOrder.getPayAmount()));
            // updUser.setLockAmount(dbUser.getLockAmount().add(rtnCash));
            userManage.updateByPrimaryKeySelective(updUser);

            PrdOrderPayCallBackContext context = PrdOrderPayCallBackContextBuilder.buildForPayed(orderId);
            orderPayCallBack.callback(context);

        } else if (EPayType.WX_PAY.getCode() == payType) {
            //金额处理成分
            long totalFee = dbOrder.getPayAmount().multiply(new BigDecimal(100)).longValue();
            //do WxPay
            UnifiedOrderReq unifiedOrderReq = UnifiedOrderReq.builder()
                    .appid(paramsService.getValueNoCache(PARAM_KEY_OPEN_APP_ID))
                    .mchId(paramsService.getValueNoCache(PARAM_KEY_WX_PAY_MCH_ID))
                    .nonceStr(IdGenUtil.uuid())
                    .body("拼哆哆商户平台")
                    .outTradeNo(userPay.getPaySn())
                    .totalFee(String.valueOf(totalFee))
                    .spbillCreateIp(ip)
                    .notifyUrl(paramsService.getValueNoCache(PARAM_KEY_WX_PAY_NOTICY_URL))
                    .tradeType("APP")
                    .build();
            String key = paramsService.getValueNoCache(PARAM_KEY_WX_PAY_MCH_KEY);
            String sign = WxUtil.generateSignature(BeanMapTransUtils.transBean2Map(unifiedOrderReq, false), key);
            unifiedOrderReq.setSign(sign);
            String preOrderId = wxPayService.unifiedOrder(unifiedOrderReq);
            //String preOrderId = "test12312312fwwsfwe";
            PayCallerMsg payCallerMsg = doWxPay(preOrderId);
            body.setWxPayMsg(payCallerMsg);
        } else if (EPayType.ALI_PAY.getCode() == payType){
            BaseAlipayMsg alipayMsgMsg = new BaseAlipayMsg();
            alipayMsgMsg.setOutTradeNo(userPay.getPaySn());
            alipayMsgMsg.setGoodsDes(userPay.getPaySn());
            alipayMsgMsg.setGoodsTitle("拆哆哆商户平台");
            alipayMsgMsg.setGoodsType("0");
            //baseRechargeMsg.setNoticeUrl("");
            //alipayMsgMsg.setFeeRate(feeRate);
            alipayMsgMsg.setTotalAmt(userPay.getPayAmount());
            // TODO 测试金额
            alipayMsgMsg.setTotalAmt(new BigDecimal(0.01));

            Map<String, String> orderInfoMap = aliPayService.pay(alipayMsgMsg);

            body.setAliPayMsg(orderInfoMap.get("orderInfo"));
        }

        body.setPayAmount(dbOrder.getPayAmount());
        body.setRtnAmt(rtnCash.setScale(2,RoundingMode.HALF_UP));

        // 奖励豆
        Object beanCnt = paramsService.getValue(GlobalConstants.PARAM_KEY_PRODUCT_BEAN_CNT);
        body.setBeanCnt(Integer.valueOf(beanCnt.toString()));
        return body;
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

    private void doBonusLog(SUser dbUser, BigDecimal rtnCash, String orderId) {
        List<OrderDetail> orderDetailList = orderDetailManage.selectByOrderId(orderId);

        orderDetailList.forEach(item -> {
            UserBonusLog bonusLog = new UserBonusLog();
            bonusLog.setId(IdGenUtil.uuid());
            bonusLog.setUserId(dbUser.getId());
            bonusLog.setOrderDetailId(item.getId());
            bonusLog.setBonusType(EBonusType.BUY_RTN_CASH.getCode());
            bonusLog.setCreateTime(new Date());
            bonusLog.setUpdateTime(new Date());
            bonusLog.setBonusAmount(rtnCash);
            bonusLog.setLogStatus(0);
            bonusLog.setRemark("支付任务金，增加冻结金额");
            userBonusLogManage.insert(bonusLog);
        });


    }

    private void doAmountLog(SUser user, BigDecimal payAmount, String orderId) {
        UserAmountLog amountLog = new UserAmountLog();
        amountLog.setId(IdGenUtil.uuid());
        amountLog.setChangeAmount(payAmount.negate());
        amountLog.setUserId(user.getId());
        amountLog.setChangeType(EAmtChangeType.PAY_GOODS.getCode());
        amountLog.setChangeTime(new Date());
        amountLog.setOldAmount(user.getTotalAmount());
        amountLog.setRelationId(orderId);
        amountLog.setAcctStatus(EAcctType.BALANCE.getCode());
        amountLog.setRemark("支付订单，扣减可用余额");
        userAmountLogManage.insert(amountLog);

    }

    /**
     * 用户取消订单
     * 根据订单id，查询订单信息
     * 校验数据是否存在
     * 校验数据是否属于当前登录者
     * 支付回调-取消订单
     *
     * @param user
     * @param req
     */
    public void cancelPrdOrder(SUser user, PrdOrderCancelReq req) {
        String orderId = req.getBody().getOrderId();
        String userId = user.getId();
        doCheckOrder(orderId, userId, EPayStatus.TO_BE_PAY, "取消订单");

        PrdOrderPayCallBackContext context = PrdOrderPayCallBackContextBuilder.buildForCancel(orderId, req.getBody().getCancelReason());
        orderPayCallBack.callback(context);

    }

    /**
     * 确认收货
     *
     * @param user
     * @param req
     */
    public void confirmToReceive(SUser user, PrdOrderReceiveReq req) {
        //check
        OrderDetail dbOrderDetail = orderDetailManage.selectByPrimary(req.getBody().getOrderDetailId());

        if (dbOrderDetail != null) {
            //确认订单归属者
            if (!StringUtils.equals(user.getId(), dbOrderDetail.getUserId())) {
                throw new BizRuntimeException(RespCodeEnum.NOT_OPERATE_RECORED);
            }
            //check status
            if (dbOrderDetail.getOrderStatus() != EOrderStatus.SENDED.getCode()) {
                throw new BizRuntimeException(RespCodeEnum.STATUS_NOT_MATCH_OPERATE, "确认收货");
            }
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setId(dbOrderDetail.getId());
            orderDetail.setOrderStatus(EOrderStatus.CONFIRM_RECEIVED.getCode());
            orderDetailManage.updateByPrimaryKeySelective(orderDetail);
        }
    }

    private Order doCheckOrder(String orderId, String userId, EPayStatus payStatus, String operate) {
        Order dbOrder = orderManage.selectByPrimary(orderId);
        if (dbOrder == null) {
            throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE, "商品订单");
        }
        if (!StringUtils.equals(userId, dbOrder.getUserId())) {
            throw new BizRuntimeException(RespCodeEnum.NOT_QUERY_RECORED);
        }
        if (dbOrder.getPaymentState() != payStatus.getCode()) {
            throw new BizRuntimeException(RespCodeEnum.STATUS_NOT_MATCH_OPERATE, operate);
        }
        return dbOrder;
    }

    private void checkAndUpdateOrderProduct(String userId, String orderDetailId) {
        List<OrderProduct> orderProductList =
                orderProductManage.selectByOrderDetailIdAndUserId(orderDetailId, userId);

        List<Map<String, Object>> productIds = new ArrayList<>();
        orderProductList.forEach(item -> {
            //释放库存

        });
    }

}
