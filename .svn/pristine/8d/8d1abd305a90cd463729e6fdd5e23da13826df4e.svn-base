package com.tian.sakura.cdd.srv.web.shop.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.tian.sakura.cdd.db.domain.shopGroup.ShopGroup;
import com.tian.sakura.cdd.srv.web.shop.dto.order.AddressBody;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class ShopProductRspBody {

	@ApiModelProperty("商品ID")
	private String productId;
	
	@ApiModelProperty("商品名称")
	private String productName;
	
	@ApiModelProperty("一级分类ID")
	private String productType1Id;

	@ApiModelProperty("一级分类名称")
	private String productType1Name;

	@ApiModelProperty("二级分类ID")
	private String productType2Id;

	@ApiModelProperty("二级分类名称")
	private String productType2Name;

	@ApiModelProperty("分组")
	private List<Map> groups;

	@ApiModelProperty("配送方式,1线下自提，2商家送货，3快递包邮，4快递收费")
	private Integer deliveryType;
	
	@ApiModelProperty("运费")
	private BigDecimal freight;
	
	@ApiModelProperty("商品售价")
	private BigDecimal price;
	
	@ApiModelProperty("折扣比例")
	private BigDecimal discountRate;
	
	@ApiModelProperty("折后价格")
	private BigDecimal discountPrice;
	
	@ApiModelProperty("库存数量")
	private Integer quantity;
	
	@ApiModelProperty("售卖时间")
	private Date startDate;

	@ApiModelProperty("商品详情")
	private String  prdDetail;
	
	@ApiModelProperty("商品图片")
	private List<String>  prdImgList;
	
	@ApiModelProperty("商品缩略图")
	private String  imgUrl;
	
	@ApiModelProperty("自提货地址")
	private List<AddressBody> selfAddressList;
	
	@ApiModelProperty("关注人数")
	private Integer focusCnt;
	
	@ApiModelProperty("商品状态  0 未发布 1 已发布 未成交 2 已成交 3 已下架")
	private Integer productStatus;
	
	@ApiModelProperty("买家立返")
	private BigDecimal successReward;
	
	@ApiModelProperty("任务躺赢")
	private BigDecimal everyReward;
	
	@ApiModelProperty("已参与人数")
	private Integer userCount;

}
