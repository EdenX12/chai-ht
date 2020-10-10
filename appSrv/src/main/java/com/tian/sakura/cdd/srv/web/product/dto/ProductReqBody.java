package com.tian.sakura.cdd.srv.web.product.dto;

import com.tian.sakura.cdd.common.entity.BasePage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class ProductReqBody extends BasePage {
    /**
     * 分类ID
     */
    @ApiModelProperty("一级分类id")
    private String typeId;
    @ApiModelProperty("二级分类id")
    private String type2Id;
    /**
     *  推荐分类ID
     */
    @ApiModelProperty("推荐分类id")
    private String recommendTypeId;

    /**
     * 商品名称
     */
    @ApiModelProperty("商品名称")
    private String productName;
    @ApiModelProperty("排序类型 0-综合排序；1-销量；2-价格")
    private Integer sortType;
    @ApiModelProperty("升序 0-升序；1-降序")
    private Integer sortDirect;
}
