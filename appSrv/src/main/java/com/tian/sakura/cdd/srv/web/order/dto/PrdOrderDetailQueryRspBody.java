package com.tian.sakura.cdd.srv.web.order.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 说明。
 *
 * @author lvzonggang
 */
@Setter
@Getter
@Builder
@ApiModel
public class PrdOrderDetailQueryRspBody {
    @ApiModelProperty("订单明细id")
    private String id;
    
    @ApiModelProperty("订单总id")
    private String orderId;
    
    @ApiModelProperty("店铺名称")
    private String shopName;
    
    @ApiModelProperty("订单状态：0未付款  1已付款待发货  2已发货  3已确认收货 4 申请退货退款 5 已退货退款 6 超时关闭 -1 已取消")
     
    private Integer orderStatus;

    // 物流信息 TODO

    //邮寄地址信息
    @ApiModelProperty("邮寄 收件人")
    private String addressName;
    @ApiModelProperty("邮寄地址")
    private String address;
    @ApiModelProperty("收件人手机")
    private String addressPhone;
    //订单商品列表
    @ApiModelProperty("订单商品列表")
    List<OrderPrdBody> orderProductList;

    //商品合计
    @ApiModelProperty("商品合计")
    private BigDecimal orderAmount;
    //优惠券
    @ApiModelProperty("优惠券ID")
    private String userCouponId;

    @ApiModelProperty("优惠金额")
    private BigDecimal couponAmount;

    //配送方式

    //返现合计
    @ApiModelProperty("返现合计")
    private BigDecimal rtnAmount;

    //赠送拆豆
    @ApiModelProperty("赠送拆豆")
    private Integer orderBean;

    //订单编号
    @ApiModelProperty("订单编号")
    private String orderSn;

    //创建时间
    @ApiModelProperty("创建时间")
    private Date createTime;

    // 支付时间
    @ApiModelProperty("支付时间")
    private Date paymentTime;


    //快递费
    @ApiModelProperty("快递费")
    private BigDecimal shippingFee;
    
    //实付金额
    @ApiModelProperty("实付金额")
    private BigDecimal payAmount;
    
    //昵称
    @ApiModelProperty("昵称")
    private String nickName;

    //等待买家付款倒计时
    @ApiModelProperty("等待买家付款倒计时秒")
    private Long payEndTime;
    
    //订单状态名称
    @ApiModelProperty("订单状态说明")
    private String orderStatusName;
}
