package com.tian.sakura.cdd.srv.web.order.dto.shopcar;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import java.util.List;

/**
 * 说明。
 *
 * @author lvzonggang
 */
@Setter
@Getter
@ApiModel
public class ShopCarEditReqBody {

    @ApiModelProperty("批量编辑的购物商品数据")
    List<ShopCarEditBody> shopCarList;
}
