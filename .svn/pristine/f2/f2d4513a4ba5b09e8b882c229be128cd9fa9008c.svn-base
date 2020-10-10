package com.tian.sakura.cdd.srv.web.order.dto;

import com.tian.sakura.cdd.srv.validator.annotation.CustEnumVal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 订单支付请求体
 *
 * @author lvzonggang
 */

@ApiModel
@Setter
@Getter
public class PrdOrderPayReqBody {

    @ApiModelProperty("支付类型0-微信；1-支付宝；2-余额")
    @NotNull(message = "支付类型不能为空")
    @CustEnumVal(value = "0,1,2", message = "支付类型参数错误")
    private Integer payType;

//    @ApiModelProperty("订单明细id, 如果单笔订单支付，必传")
//    //@NotBlank(message="订单明细id不能为空")
//    private String orderDetailId;

    @ApiModelProperty("订单id")
    @NotBlank(message="订单id不能为空")
    private String orderId;

    private String ip;
}
