package com.tian.sakura.cdd.srv.web.product.dto;

import com.tian.sakura.cdd.srv.service.product.PrdCommissionCalculateResult;
import com.tian.sakura.cdd.srv.web.base.dto.ProductRspBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author lvzonggang
 */

@ApiModel
@Setter
@Getter
public class ProductDetailRspBody extends ProductRspBody {

    @ApiModelProperty("商品类型下所有的规格集合")
    //private List<ProductSpecValueListVo> typeSpecValueList;
    private Map<String, List<ProductSpecValueVo>> typeSpecValueMap;
    @ApiModelProperty("商品下设置的商品规格数据集合")
    private List<ProductSpecValueVo> specValueVoList;
}
