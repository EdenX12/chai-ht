package com.tian.sakura.cdd.srv.web.order.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 说明。
 *
 * @author lvzonggang
 */

@ApiModel
@Setter
@Getter
public class PrdOrderQueryRspBody {
    @ApiModelProperty("订单明细id")
    private String id;

    @ApiModelProperty("店铺名称")
    private String shopName;

    @ApiModelProperty("订单状态编码：0-待付款;1-待发货;2-待收货;3-退还货;4-已完成")
    private Integer orderStatus;
    @ApiModelProperty("订单状态名称")
    private String orderStatusName;

    private Long createTimeValue;
    private long payEndTime;

    List<OrderPrdBody> orderProductList;
}
