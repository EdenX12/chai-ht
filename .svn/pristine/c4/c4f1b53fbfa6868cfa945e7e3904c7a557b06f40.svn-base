package com.tian.sakura.cdd.srv.web.shop.dto.data;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class ShopTodayDataReqBody {
	@ApiModelProperty("查询时间段类型，0-今天，1-昨天，2-近7天，3-近30天,4-自定义起止时间段")
	private Integer qryDurationType;
	
	@ApiModelProperty("起始日期")
	private Date startDate;
	
	@ApiModelProperty("截止日期")
	private Date endDate;

}
