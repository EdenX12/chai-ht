package com.tian.sakura.cdd.srv.web.order.dto.shopcar;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 新增购物车请求体
 *
 * @author lvzonggang
 */

@Setter
@Getter
@ApiModel
public class ShopCarAddReqBody {
    @ApiModelProperty("产品标识")
    @NotBlank(message = "产品标识不能为空")
    private String productId;
    @ApiModelProperty("产品规格标识")
    @NotBlank(message = "产品规格标识不能为空")
    private String productSpecId;

//    @ApiModelProperty("产品价格")
//    @NotNull(message = "产品价格不能为空")
//    private BigDecimal price;
    @ApiModelProperty("产品数量")
    @NotNull(message = "产品数量不能为空")
    private Integer count;

}
