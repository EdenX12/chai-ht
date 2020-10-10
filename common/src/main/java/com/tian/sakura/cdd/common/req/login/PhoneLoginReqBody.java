package com.tian.sakura.cdd.common.req.login;

import lombok.Data;

@Data
public class PhoneLoginReqBody {
    private String mobile;
    private String smsCode;
}
