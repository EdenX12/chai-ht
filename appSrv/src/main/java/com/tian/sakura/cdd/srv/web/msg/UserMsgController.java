package com.tian.sakura.cdd.srv.web.msg;

import com.tian.sakura.cdd.common.req.msg.UserMsgReq;
import com.tian.sakura.video.service.auth.UserMsgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/pub/msg")
@Api(value = "消息相关")
public class UserMsgController {

    @Autowired
    private UserMsgService userMsgService;

    @ApiOperation(value = "获取消息列表")
    @PostMapping("/getMsgList")
    public Object getUserMsgList(@RequestBody UserMsgReq userMsgReq) {
        return userMsgService.getUserMsgList(userMsgReq);
    }
}
