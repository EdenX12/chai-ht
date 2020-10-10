package com.tian.sakura.cdd.srv.web.task.dto;

import com.tian.sakura.cdd.common.web.RequestHead;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;

/**
 * 产品分享结果通知请求类
 *
 * @author lvzonggang
 */
@Setter
@Getter
@ApiModel
public class ShareNoticeReq {

    @Valid
    private RequestHead head;
    @Valid
    private ShareNoticeReqBody body;
}
