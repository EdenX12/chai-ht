package com.tian.sakura.cdd.srv.web.product.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 产品类型返回体
 *
 * @author lvzonggang
 */

@ApiModel
@Setter
@Getter
@Builder
public class PrdTypeRspBody {

    @ApiModelProperty("类型id")
    private String productdTypeId;
    @ApiModelProperty("类型名称")
    private String typeName;
    @ApiModelProperty("类型图片")
    private String typeImg;
    @ApiModelProperty("上级Id")
    private String parentId;
    @ApiModelProperty("序号")
    private int showOrder;
    @ApiModelProperty("子类型")
    private List<PrdTypeRspBody> children;



}
