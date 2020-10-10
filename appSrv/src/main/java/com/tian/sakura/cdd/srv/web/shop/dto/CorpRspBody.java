package com.tian.sakura.cdd.srv.web.shop.dto;

import java.util.HashMap;
import java.util.List;

import com.tian.sakura.cdd.db.domain.shopCatlog.ShopCatlog;
import com.tian.sakura.cdd.db.domain.shopReturn.ShopReturn;
import com.tian.sakura.cdd.srv.web.shop.dto.order.AddressBody;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class CorpRspBody {
	@ApiModelProperty("店铺ID")
	private Integer shopId;

	@ApiModelProperty("店铺编号")
	private String shopNo;

	@ApiModelProperty("店铺名称")
	private String shopName;
	
	@ApiModelProperty("店铺省份")
	private String province;

	@ApiModelProperty("店铺省份id")
	private Integer provinceId;

	@ApiModelProperty("店铺城市")
	private String city;

	@ApiModelProperty("店铺城市Id")
	private Integer cityId;

	@ApiModelProperty("店铺区县")
	private String country;

	@ApiModelProperty("店铺区县id")
	private Integer countryId;

	
	@ApiModelProperty("店铺详细地址")
	private String address;
	
	@ApiModelProperty("经营类目")
	private List<HashMap> shopCatlog;
	
	@ApiModelProperty("法人姓名")
	private String individualName;
	
	@ApiModelProperty("手机号")
	private String mobile;
	
	@ApiModelProperty("常用邮箱")
	private String email;
	
	@ApiModelProperty("客服电话")
	private String callCenter;
	
	@ApiModelProperty("身份证正面URL")
	private String idCardFront;
	
	@ApiModelProperty("身份证背面URL")
	private String idCardBack;
	
	@ApiModelProperty("营业执照图片URL")
	private String license;
	
	@ApiModelProperty("身份证有效期")
	private String idCardExpire;
	
	@ApiModelProperty("身份证号")
	private String idCardNo;
	
	@ApiModelProperty("企业名称")
	private String corpName;
	
	@ApiModelProperty("营业执照号")
	private String licenseNo;

	@ApiModelProperty("店铺图标URL")
	private String shopLogo;

	@ApiModelProperty("客服微信")
	private String weixin;
	
	@ApiModelProperty("客服 QQ")
	private String qq;
	
	@ApiModelProperty("退货地址")
	private List<AddressBody> shopReturnList;

	@ApiModelProperty("自提货地址")
	private List<AddressBody> selfAddressList;
	
	@ApiModelProperty("店铺类型 0-个人  1-企业")
	private Integer shopType;

}
