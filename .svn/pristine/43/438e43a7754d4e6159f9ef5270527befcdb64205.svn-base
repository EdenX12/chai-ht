package com.tian.sakura.cdd.srv.web.user;

import com.tian.sakura.cdd.common.annotation.CustomerApiAuth;
import com.tian.sakura.cdd.common.dict.EUserType;
import com.tian.sakura.cdd.db.domain.user.SUser;
import com.tian.sakura.cdd.srv.filter.LoginUserThreadLocal;
import com.tian.sakura.cdd.srv.service.user.UserSignApiService;
import com.tian.sakura.cdd.srv.web.user.dto.UserSignQueryRspBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 说明。
 *
 * @author lvzonggang
 */

@Api("用户签到API")
@RestController
@RequestMapping("user")
public class UserSignController {

    @Autowired
    private UserSignApiService userSignApiService;

    @ApiOperation("签到")
    @PostMapping("sign")
    @CustomerApiAuth(EUserType.NORMAL_CUST)
    public void sign() {
        SUser user = LoginUserThreadLocal.getLoginUser();
        userSignApiService.sign(user);
    }

    @ApiOperation("查询当前一周的签到数据")
    @PostMapping("signWeekList")
    @CustomerApiAuth(EUserType.NORMAL_CUST)
    public UserSignQueryRspBody signList() {
        SUser user = LoginUserThreadLocal.getLoginUser();

        return userSignApiService.signWeekList(user);
    }
}
