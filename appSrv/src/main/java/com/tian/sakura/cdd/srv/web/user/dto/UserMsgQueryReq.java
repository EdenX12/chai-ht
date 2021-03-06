package com.tian.sakura.cdd.srv.web.user.dto;

import com.tian.sakura.cdd.common.web.RequestHead;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;

/**
 * 说明。
 *
 * @author lvzonggang
 */

@Setter
@Getter
@ApiModel
public class UserMsgQueryReq {
    @Valid
    private RequestHead head;
    @Valid
    private UserMsgQueryReqBody body;
}
