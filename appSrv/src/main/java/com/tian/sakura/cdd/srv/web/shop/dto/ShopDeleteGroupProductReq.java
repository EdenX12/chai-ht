package com.tian.sakura.cdd.srv.web.shop.dto;

import com.tian.sakura.cdd.common.web.RequestHead;

import lombok.Data;

@Data
public class ShopDeleteGroupProductReq {
	private RequestHead head;
	private ShopDeleteGroupProductReqBody body;
}
