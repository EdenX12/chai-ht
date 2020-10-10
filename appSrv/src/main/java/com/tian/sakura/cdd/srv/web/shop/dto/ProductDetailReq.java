package com.tian.sakura.cdd.srv.web.shop.dto;

import com.tian.sakura.cdd.common.web.RequestHead;

import lombok.Data;

@Data
public class ProductDetailReq {
	private RequestHead head;
	private ProductDetailReqBody body;
}
