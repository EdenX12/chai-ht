package com.tian.sakura.cdd.srv.web.shop.dto.data;

import com.tian.sakura.cdd.common.web.RequestHead;

import lombok.Data;

@Data
public class ShopTodayDataReq {
	private RequestHead head;
	private ShopTodayDataReqBody body;
}
