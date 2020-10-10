package com.tian.sakura.cdd.srv.web.shop.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.tian.sakura.cdd.db.domain.shopReturn.ShopReturn;
import com.tian.sakura.cdd.srv.web.shop.dto.order.AddressBody;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@Data
public class CorpReqBody {
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
	
	@ApiModelProperty("法人姓名")
	private String individualName;
	
	@ApiModelProperty("手机号")
	private String mobile;
	
	@ApiModelProperty("常用邮箱")
	private String email;
	
	@ApiModelProperty("客服电话")
	private String callCenter;
	
	@ApiModelProperty("商铺ID")
	private Integer shopId;
	
	@ApiModelProperty("身份证正面二进制流")
	private MultipartFile idCardFront;
	
	@ApiModelProperty("身份证背面二进制流")
	private MultipartFile idCardBack;
	
	@ApiModelProperty("营业执照图片二进制流")
	private MultipartFile license;
	
	@ApiModelProperty("身份证有效期")
	private String idCardExpire;
	
	@ApiModelProperty("身份证号")
	private String idCardNo;
	
	@ApiModelProperty("企业名称")
	private String corpName;
	
	@ApiModelProperty("营业执照号")
	private String licenseNo;

	@ApiModelProperty("客服微信")
	private String weixin;
	
	@ApiModelProperty("客服 QQ")
	private String qq;
	
	@ApiModelProperty("退货地址")
	private List<AddressBody> shopReturnList;

	@ApiModelProperty("自提货地址")
	private List<AddressBody> selfAddressList;

}
