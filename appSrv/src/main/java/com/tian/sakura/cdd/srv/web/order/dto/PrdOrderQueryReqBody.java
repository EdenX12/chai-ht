package com.tian.sakura.cdd.srv.web.order.dto;

import com.tian.sakura.cdd.common.dict.EOrderQueryStatus;
import com.tian.sakura.cdd.common.entity.BasePage;
import com.tian.sakura.cdd.srv.validator.annotation.CustEnumVal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 说明。
 *
 * @author lvzonggang
 */

@ApiModel
@Setter
@Getter
public class PrdOrderQueryReqBody extends BasePage {

    @ApiModelProperty("订单状态：0-待付款;1-待发货;2-待收货;3-退还货;4-已完成, 5-待取货")
    @CustEnumVal(value = "0,1,2,3,4,5", message = "订单状态参数错误")
    private Integer orderStatus;

}
