package com.tian.sakura.cdd.srv.web.order.dto.shopcar;

import com.tian.sakura.cdd.srv.validator.annotation.CustEnumVal;
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
public class ShopCarEditBody {
    @ApiModelProperty("主键标识")
    @NotBlank(message = "购物车标识不能为空")
    private String shopCarId;

    @ApiModelProperty("选中状态；0-未选 1-已选")
    @CustEnumVal(value = "0,1", message = "选中状态的值应为0或1")
    private Integer checkStatus;

    @ApiModelProperty("数量")
    private Integer count;
}
