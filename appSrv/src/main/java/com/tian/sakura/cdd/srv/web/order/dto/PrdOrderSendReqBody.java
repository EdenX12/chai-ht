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
public class PrdOrderSendReqBody {

    @ApiModelProperty("订单明细id")
    @NotBlank(message="订单明细id不能为空")
    private String orderDetailId;

    @ApiModelProperty("快递编号")
    @NotBlank(message="快递编码不能为空")
    private String shippingCode;
    @ApiModelProperty("快递公司id")
    @NotBlank(message="快递公司id不能为空")
    private Integer shippingExpressId;
}
