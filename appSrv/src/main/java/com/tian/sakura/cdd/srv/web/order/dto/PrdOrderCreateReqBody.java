package com.tian.sakura.cdd.srv.web.order.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author lvzonggang
 */

@Setter
@Getter
@ApiModel
public class PrdOrderCreateReqBody {

    @ApiModelProperty("店铺商品列表")
    private List<OrderShopPrdBody> orderShopPrdBodyList= new ArrayList<>();
    @ApiModelProperty("订单总金额")
    private BigDecimal totalAmount;
    @ApiModelProperty("优惠总金额")
    private BigDecimal totalCouponAmount;

    @ApiModelProperty("实际支付金额")
    private BigDecimal payAmount;
    //运费
    private BigDecimal shippingFee;
    //邮寄地址信息
    @ApiModelProperty("邮寄地址")
    private String addressId;



}
