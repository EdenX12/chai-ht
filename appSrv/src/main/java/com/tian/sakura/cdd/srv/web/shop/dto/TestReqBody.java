package com.tian.sakura.cdd.srv.web.shop.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class TestReqBody {
	@ApiModelProperty("errorInfo")
	private String content;

}
