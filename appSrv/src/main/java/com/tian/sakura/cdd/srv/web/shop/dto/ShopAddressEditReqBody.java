package com.tian.sakura.cdd.srv.web.shop.dto;

import java.util.List;

import com.tian.sakura.cdd.srv.web.shop.dto.order.AddressBody;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class ShopAddressEditReqBody {
	@ApiModelProperty("商铺id")
	private Integer shopId;
	
	@ApiModelProperty("店铺省份")
	private Integer province;
	
	@ApiModelProperty("店铺城市")
	private Integer city;
	
	@ApiModelProperty("店铺区县")
	private Integer country;
	
	@ApiModelProperty("店铺详细地址")
	private String address;

	
	@ApiModelProperty("退货地址")
	private List<AddressBody> shopReturnList;

	@ApiModelProperty("自提货地址")
	private List<AddressBody> selfAddressList;

}
