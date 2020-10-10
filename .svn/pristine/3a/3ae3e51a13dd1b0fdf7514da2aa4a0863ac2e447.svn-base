package com.tian.sakura.video.service.auth;

import com.tian.sakura.cdd.common.req.msg.UserMsgReq;
import com.tian.sakura.cdd.db.domain.msg.UserMsg;
import com.tian.sakura.cdd.db.manage.msg.UserMsgManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMsgService {

    @Autowired
    private UserMsgManage userMsgManage;

    public List<UserMsg> getUserMsgList(UserMsgReq userMsgReq) {
        return userMsgManage.getUserMsgList(userMsgReq.getBody());
    }
}
