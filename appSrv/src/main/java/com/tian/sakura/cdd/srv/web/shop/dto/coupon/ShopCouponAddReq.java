package com.tian.sakura.cdd.srv.web.shop.dto.coupon;

import com.tian.sakura.cdd.common.web.RequestHead;

import lombok.Data;

@Data
public class ShopCouponAddReq {
	private RequestHead head;
	private ShopCouponAddReqBody body;
}
