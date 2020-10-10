package com.tian.sakura.cdd.srv.web.login.dto;

import com.tian.sakura.cdd.common.web.RequestHead;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;

/**
 * 注册账户请求
 *
 * @author lvzonggang
 */
@Setter
@Getter
public class RegisterReq {
    @Valid
    private RequestHead head;
    @Valid
    private RegisterReqBody body;


}
