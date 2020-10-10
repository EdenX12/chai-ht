package com.tian.sakura.cdd.srv.web.shop.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class ProductImgReqBody {
	@ApiModelProperty("商品id")
	private String productId;
	@ApiModelProperty("商品图片base64")
	private String productImg;
	@ApiModelProperty("图片类型: 1-缩略图 2-主题图")
	private Integer imgType;
}
