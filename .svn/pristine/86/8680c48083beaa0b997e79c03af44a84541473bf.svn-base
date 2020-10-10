package com.tian.sakura.cdd.srv.web.login;

import com.tian.sakura.cdd.common.annotation.CustomerApiAuth;
import com.tian.sakura.cdd.common.dict.EUserType;
import com.tian.sakura.cdd.db.domain.user.SUser;
import com.tian.sakura.cdd.srv.filter.LoginUserThreadLocal;
import com.tian.sakura.cdd.srv.service.login.CustTokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * 登录
 *
 * @author lvzonggang
 * @Date 2019/8/17
 */
@Api("退出登录")
@RestController
@RequestMapping("logout")
public class LogoutController {

    @Autowired
    private CustTokenService custTokenService;

    @ApiOperation("退出登录")
    @GetMapping
    @CustomerApiAuth(value = {EUserType.NORMAL_CUST})
    public void logout(HttpServletResponse response) {
        SUser user = LoginUserThreadLocal.getLoginUser();
        custTokenService.removeToken(user.getUserPhone());

    }


}
