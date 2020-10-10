package com.tian.sakura.cdd.srv.web.order.dto.shopcar;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 说明。
 *
 * @author lvzonggang
 */

@Setter
@Getter
@ApiModel
public class ShopCarQueryRsqBodyNoPage {
    @ApiModelProperty("店铺id")
    private String shopId;
    @ApiModelProperty("店铺名称")
    private String shopName;

    @ApiModelProperty("商品数据")
    List<ShopCarQueryRsqBody> shopPrdList = new ArrayList<>();
}
