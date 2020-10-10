package com.tian.sakura.cdd.srv.web.shop.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class IndividualBasicReqBody {
	@ApiModelProperty("店铺名称")
	private String shopName;
	
	@ApiModelProperty("店铺省份")
	private Integer province;
	
	@ApiModelProperty("店铺城市")
	private Integer city;
	
	@ApiModelProperty("店铺区县")
	private Integer country;
	
	@ApiModelProperty("店铺详细地址")
	private String address;
	
	@ApiModelProperty("经营类目")
	private List<Integer> shopCatlog;
	
	@ApiModelProperty("个人姓名")
	private String individualName;
	
	@ApiModelProperty("个人手机号")
	private String mobile;
	
	@ApiModelProperty("常用邮箱")
	private String email;
	
	@ApiModelProperty("客服电话")
	private String callCenter;
	
	@ApiModelProperty("商铺ID")
	private Integer shopId;
	
	@ApiModelProperty("身份证有效期")
	private String idCardExpire;
	
	@ApiModelProperty("身份证号")
	private String idCardNo;

}
