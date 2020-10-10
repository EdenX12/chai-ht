package com.tian.sakura.cdd.srv.web.user;

import com.tian.sakura.cdd.common.annotation.CustomerApiAuth;
import com.tian.sakura.cdd.common.dict.EUserType;
import com.tian.sakura.cdd.db.domain.user.SUser;
import com.tian.sakura.cdd.db.domain.user.UserBank;
import com.tian.sakura.cdd.srv.filter.LoginUserThreadLocal;
import com.tian.sakura.cdd.srv.service.user.UserBankApiService;
import com.tian.sakura.cdd.srv.web.user.dto.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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

@Api("用户银行卡模块")
@RestController
@RequestMapping("userBank")
public class UserBankController {

    @Autowired
    private UserBankApiService userBankApiService;

    @ApiOperation("查询银行卡列表")
    @PostMapping("list")
    @CustomerApiAuth(EUserType.NORMAL_CUST)
    public UserBankQueryRspBody list(@RequestBody UserBankQueryReq req) {
        SUser user = LoginUserThreadLocal.getLoginUser();
        return userBankApiService.selectByUserId(user);
    }

    @ApiOperation("添加银行卡")
    @PostMapping("add")
    @CustomerApiAuth(EUserType.NORMAL_CUST)
    public void add(@RequestBody @Valid UserBankAddReq req) {
        SUser user = LoginUserThreadLocal.getLoginUser();
        userBankApiService.addBank(user,req.getBody());
    }

    @ApiOperation("删除银行卡")
    @PostMapping("delete")
    @CustomerApiAuth(EUserType.NORMAL_CUST)
    public void logicDelete(@RequestBody @Valid UserBankDeleteReq req ) {
        userBankApiService.logicDelete(LoginUserThreadLocal.getLoginUser(), req.getBody().getUserBankId());
    }
}
