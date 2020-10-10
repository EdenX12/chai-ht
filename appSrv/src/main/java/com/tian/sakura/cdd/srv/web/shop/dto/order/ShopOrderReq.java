package com.tian.sakura.cdd.srv.web.shop.dto.order;

import com.tian.sakura.cdd.common.web.RequestHead;

import lombok.Data;

@Data
public class ShopOrderReq {
	private RequestHead head;
	private ShopOrderReqBody body;
}
