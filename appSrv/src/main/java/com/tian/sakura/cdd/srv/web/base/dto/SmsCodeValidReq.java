package com.tian.sakura.cdd.srv.web.base.dto;

import com.tian.sakura.cdd.common.web.RequestHead;
import com.tian.sakura.cdd.srv.web.login.dto.PhoneReqBody;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;

/**
 *
 *
 * @author lvzonggang
 * @Date 2019/8/17
 */

@Setter
@Getter
public class SmsCodeValidReq {
    @Valid
    private RequestHead head;
    @Valid
    private PhoneReqBody body;

}
