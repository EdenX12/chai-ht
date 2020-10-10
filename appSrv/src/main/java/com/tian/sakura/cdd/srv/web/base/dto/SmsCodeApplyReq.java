package com.tian.sakura.cdd.srv.web.base.dto;

import com.tian.sakura.cdd.common.web.RequestHead;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;

/**
 * 申请短信验证码请
 *
 * @author lvzonggang
 */
@Setter
@Getter

@ApiModel
public class SmsCodeApplyReq {
    @Valid
    private RequestHead head;
    @Valid
    private SmsCodeApplyReqBody body;

}
