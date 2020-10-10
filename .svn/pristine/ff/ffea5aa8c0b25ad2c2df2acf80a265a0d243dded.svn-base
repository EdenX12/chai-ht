package com.tian.sakura.cdd.srv.web.base.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@ApiModel
public class ProductRspBody {
	@ApiModelProperty("商品id")
	private String productId;
	@ApiModelProperty("商品图片")
	private String productImg;
	@ApiModelProperty("商品图片列表")
	private List<String> productImgList;
	@ApiModelProperty("商品名称")
	private String productName;
	@ApiModelProperty("商品简介")
	private String productDesc;
	@ApiModelProperty("商品详情")
	private String productDetail;
	@ApiModelProperty("商品价格")
	private BigDecimal productPrice;
	@ApiModelProperty("任务金")
	private BigDecimal taskPrice;
	@ApiModelProperty("买家立返")
	private BigDecimal successReward;
	@ApiModelProperty("任务躺赢金")
	private BigDecimal everyReward;
	@ApiModelProperty("已拆满任务线数量")
	private Integer totalNumber;
	@ApiModelProperty("一个商品的任务数")
	private Integer taskNumber;
	@ApiModelProperty("当前未满任务线的份额数")
	private Integer receivedTask;
	@ApiModelProperty("参与的拆家人数")
	private Integer userCount;
	@ApiModelProperty("关注人数")
	private Integer totalFocus;
	@ApiModelProperty("登陆者是否关注该商品")
	private Boolean focus;
	@ApiModelProperty("商品状态   0 未发布, 1 已发布 未成交, 2 已成交, 3 已下架")
	private Integer productStatus;
	
	@ApiModelProperty("销量")
	private Integer sellQuantity;
	
	@ApiModelProperty("库存")
	private Integer stockQuantity;
	
	@ApiModelProperty("拆家已躺赢人次")
	private Integer hasWinCnt;
	
	
	private String shopId;
	private String shopName;
}
