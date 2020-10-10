package com.tian.sakura.cdd.srv.web.shop.dto.order;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.tian.sakura.cdd.srv.web.order.dto.OrderPrdBody;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class ShopOrderRspBody   {
	@ApiModelProperty("用户id")
	private String userId;
	@ApiModelProperty("用户昵称")
	private String  nickName;
	@ApiModelProperty("用户头像")
	private String  userImg;
    @ApiModelProperty("店铺名称")
    private String shopName;
    @ApiModelProperty("订单明细id")
    private String id;
    @ApiModelProperty("订单编号")
    private String orderSn;
    @ApiModelProperty("实付金额")
    private BigDecimal payAmount;
	@ApiModelProperty("商品总数量")
    private Integer taotalProductNumber = 0;
    @ApiModelProperty("订单状态：订单状态  0未付款  1已付款待发货  2已发货  3已确认收货 4 申请退货退款 5 已退货退款 6 超时关闭 -1 已取消 ")
    private Integer orderStatus;
    @ApiModelProperty("订单状态名称")
    private String orderStatusName;
    @ApiModelProperty("订单时间")
    private Long createTime;
    @ApiModelProperty("收货人")
    private String addresName;
    @ApiModelProperty("收货人电话")
    private String addressPhone;
    @ApiModelProperty("收货人地址")
    private String addressDetail;
    
    @ApiModelProperty("店铺logo")
    private String shopLogo;
    
    
    List<OrderPrdBody> orderProductList;
}
