package com.tian.sakura.cdd.srv.web.shop.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 经营类目字典
 * @author liuhg
 *
 */
@ApiModel
@Data
public class CatalogRspBody {
	@ApiModelProperty("类目id")
	private Integer catlogId;
	
	@ApiModelProperty("类目名称")
	private String catalogName;
}
