package com.tian.sakura.cdd.common.req.login;

import com.tian.sakura.cdd.common.web.RequestHead;
import lombok.Data;

@Data
public class PhoneLoginReq {
    private RequestHead head;
    private PhoneLoginReqBody body;
}
