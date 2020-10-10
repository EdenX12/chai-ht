package com.tian.sakura.cdd.srv.web.order.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 说明。
 *
 * @author lvzonggang
 */

@Setter
@Getter
@ApiModel
public class OrderShopPrdBody {

    @ApiModelProperty("店铺id")
    private String shopId;
    @ApiModelProperty("店铺名称")
    private String shopName;
    @ApiModelProperty("商品列表")
    private List<OrderPrdBody> orderPrdBodyList= new ArrayList<>();
    @ApiModelProperty("用户优惠券id")
    private String userCouponId;
    @ApiModelProperty("优惠金额")
    private BigDecimal couponAmount;
    @ApiModelProperty("订单金额")
    private BigDecimal orderAmount;
    @ApiModelProperty("实际支付金额")
    private BigDecimal payAmount;
    @ApiModelProperty("运费")
    private BigDecimal shippingFee;
    @ApiModelProperty("发货备注")
    private String deliverExplain;

}
