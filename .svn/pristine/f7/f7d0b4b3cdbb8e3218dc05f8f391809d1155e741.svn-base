package com.tian.sakura.cdd.srv.web.user;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.tian.sakura.cdd.common.annotation.CustomerApiAuth;
import com.tian.sakura.cdd.common.dict.EUserType;
import com.tian.sakura.cdd.db.domain.user.SUser;
import com.tian.sakura.cdd.srv.filter.LoginUserThreadLocal;
import com.tian.sakura.cdd.srv.service.user.UserApiService;
import com.tian.sakura.cdd.srv.web.login.dto.LoginRsp;
import com.tian.sakura.cdd.srv.web.user.dto.UpdateNickNameReq;
import com.tian.sakura.cdd.srv.web.user.dto.UserMsgQueryReq;
import com.tian.sakura.cdd.srv.web.user.dto.UserMsgQueryRspBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 说明。
 *
 * @author lvzonggang
 */

@RestController
@RequestMapping("user")
@Api("用户信息模块")
public class UserController {

    @Autowired
    private UserApiService userApiService;

    @PostMapping("/updateNickName")
    @CustomerApiAuth(EUserType.NORMAL_CUST)
    @ApiOperation("更新昵称")
    public void updateNickName(@RequestBody @Valid UpdateNickNameReq req) {
        SUser user = LoginUserThreadLocal.getLoginUser();
        userApiService.updateNickName(req.getBody().getNickName(), user.getId());
    }

    @PostMapping("refresh")
    @CustomerApiAuth(EUserType.NORMAL_CUST)
    @ApiOperation("刷新用户信息")
    public LoginRsp refresh() {
        return userApiService.refresh(LoginUserThreadLocal.getLoginUser());
    }

    @PostMapping("/msgList")
    @CustomerApiAuth(EUserType.NORMAL_CUST)
    @ApiOperation("查询用户的系统信息")
    public UserMsgQueryRspBody selectMsg(@RequestBody @Valid UserMsgQueryReq req) {
        SUser user = LoginUserThreadLocal.getLoginUser();
        PageInfo pageInfo = userApiService.selectUserMsg(user, req);
        return UserMsgQueryRspBody.builder().pageInfo(pageInfo).build();
    }
}
