package com.tian.sakura.cdd.db.domain.order;

import com.tian.sakura.cdd.common.entity.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.*;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table s_order_detail
 *
 * @mbg.generated do_not_delete_during_merge
 */
@Data
public class OrderDetail extends BaseEntity<String> {

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

    private List<OrderProduct> orderProductList = new ArrayList<>();
    private Map<String, Integer> prdStockMap = new HashMap<>();
    private String shopName;

    private String cancelReason;

    private String nickName;
    
    //客户自提货，提货之后商家置状态
    private Integer pickupFlag;
    
    //头像
    private String userImg;
}