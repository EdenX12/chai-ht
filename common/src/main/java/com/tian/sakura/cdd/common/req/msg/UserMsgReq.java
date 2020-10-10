package com.tian.sakura.cdd.common.req.msg;

import com.tian.sakura.cdd.common.web.RequestHead;
import lombok.Data;

@Data
public class UserMsgReq {
    private RequestHead head;

    private UserMsgReqBody body;
}
