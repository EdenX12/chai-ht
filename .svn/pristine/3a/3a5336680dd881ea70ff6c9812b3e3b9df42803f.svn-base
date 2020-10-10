package com.tian.sakura.cdd.srv.web.order.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * 说明。
 *
 * @author lvzonggang
 */

@Setter
@Getter
@ApiModel
public class PrdOrderCancelReqBody {

//    @ApiModelProperty("订单明细id")
//    @NotBlank(message="订单明细id不能为空")
//    private String orderDetailId;

    @ApiModelProperty("订单id")
    @NotBlank(message="订单id不能为空")
    private String orderId;
    @ApiModelProperty("订单取消原因")
    @NotBlank(message="取消原因不能为空")
    private String cancelReason;
}
