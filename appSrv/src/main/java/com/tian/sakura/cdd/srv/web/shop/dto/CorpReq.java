package com.tian.sakura.cdd.srv.web.shop.dto;

import com.tian.sakura.cdd.common.web.RequestHead;

import lombok.Data;

/**
 * 企业入驻基本信息
 * @author liuhg
 *
 */
@Data
public class CorpReq {
	private RequestHead head;
	private CorpReqBody body;
}
