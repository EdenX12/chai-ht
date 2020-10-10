package com.tian.sakura.cdd.common.req.order;

import com.tian.sakura.cdd.common.entity.BasePage;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class AdminOrderDetailReq extends BasePage {
    private String orderId;


    private String shopId;


    private String userId;


    private String orderSn;


    private Integer paymentType;


    private Integer paymentState;


    private Date paymentTime;


    private BigDecimal orderAmount;


    private BigDecimal shippingFee;


    private BigDecimal couponAmount;


    private String userCouponId;


    private BigDecimal payAmount;


    private String shippingExpressCode;


    private Integer shippingExpressId;


    private String shippingCode;


    private Date shippingTime;


    private String addressId;

    private String orderMessage;


    private Integer orderStatus;


    private String addressName;


    private String addressPhone;


    private String addressDetail;


    private Integer channel;


    private Date createTime;


    private String deliverExplain;

    private String shopName;

    private String cancelReason;

    private String nickName;
}
