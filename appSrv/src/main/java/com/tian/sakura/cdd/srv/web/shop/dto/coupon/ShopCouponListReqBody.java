package com.tian.sakura.cdd.srv.web.shop.dto.coupon;

import com.tian.sakura.cdd.common.entity.BasePage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class ShopCouponListReqBody extends BasePage {
	@ApiModelProperty("券状态  0-未开始 1-生效中   2-已结束")
	private Integer couponStatus;
}
