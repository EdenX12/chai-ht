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
public class PrdOrderUnPayQueryRspBody {
    @ApiModelProperty("订单id")
    private String orderId;

    @ApiModelProperty("支付截止时间")
    private Long payEndTime;

    private List<PrdOrderQueryRspBody> shopOrderList;
}
