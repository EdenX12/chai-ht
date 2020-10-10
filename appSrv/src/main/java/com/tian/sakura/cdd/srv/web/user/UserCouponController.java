package com.tian.sakura.cdd.srv.web.user;

import com.tian.sakura.cdd.common.annotation.CustomerApiAuth;
import com.tian.sakura.cdd.common.dict.EUserType;
import com.tian.sakura.cdd.srv.filter.LoginUserThreadLocal;
import com.tian.sakura.cdd.srv.service.user.UserCouponApiService;
import com.tian.sakura.cdd.srv.web.user.dto.UserCouponQueryReq;
import com.tian.sakura.cdd.srv.web.user.dto.UserCouponRsqBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * 用户优惠券模块api
 *
 * @author lvzonggang
 */
@Api("用户优惠券API")
@RestController
@RequestMapping("user/coupon")
public class UserCouponController {

    @Autowired
    private UserCouponApiService userCouponApiService;

    @ApiOperation("查询用户下的优惠券")
    @PostMapping("list")
    @CustomerApiAuth(EUserType.NORMAL_CUST)
    public List<UserCouponRsqBody> list(@RequestBody @Valid UserCouponQueryReq queryReq) {
        String userId = LoginUserThreadLocal.getLoginUser().getId();
        //String userId = "59fbae33b345412a92c3004a72e52649";
        return userCouponApiService.list(userId,queryReq);
    }


}
