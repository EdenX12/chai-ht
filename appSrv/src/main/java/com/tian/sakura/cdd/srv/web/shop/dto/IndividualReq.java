package com.tian.sakura.cdd.srv.web.shop.dto;
import com.tian.sakura.cdd.common.web.RequestHead;

import lombok.Data;
/**
 * 个人入驻基本信息请求参数
 * @author liuhg
 *
 */
@Data
public class IndividualReq {
	private RequestHead head;
	private IndividualReqBody body;
}
