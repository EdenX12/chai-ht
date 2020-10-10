package com.tian.sakura.cdd.srv.web.mytask.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 正在进行中的任务线
 * @author liuhg
 *
 */
@Data
@ApiModel
public class MyTaskLineRspBody {
	@ApiModelProperty("任务线id")
	private String taskLineId ;
	@ApiModelProperty("任务线上的人数")
	private Integer totalPeople;
	@ApiModelProperty("当前用户在任务线上的位置")
	private List<HashMap> myPosition;
}
