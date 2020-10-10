package com.tian.sakura.cdd.controller;

import com.tian.sakura.cdd.common.dto.ResultDto;
import com.tian.sakura.cdd.common.req.user.AdminUserLevel;
import com.tian.sakura.cdd.common.req.user.AdminUserReq;
import com.tian.sakura.cdd.common.req.user.AdminUserWithdraw;
import com.tian.sakura.cdd.db.domain.user.SUser;
import com.tian.sakura.cdd.db.domain.user.UserCoupon;
import com.tian.sakura.cdd.db.domain.user.UserLevel;
import com.tian.sakura.cdd.db.domain.user.UserWithdraw;
import com.tian.sakura.video.service.auth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/listUser")
    public ResultDto listUser(@RequestBody AdminUserReq adminUserReq) {
        return ResultDto.success().setObj(userService.listUser(adminUserReq));
    }

    @PostMapping("/listUserLevel")
    public ResultDto listUserLevel(@RequestBody UserLevel userLevel) {
        return ResultDto.success().setObj(userService.listUserLevel(userLevel));
    }

    @PostMapping("/insertUser")
    public ResultDto insertUser(@RequestBody SUser user) {
        userService.insertUser(user);
        return ResultDto.success();
    }

    @PostMapping("/updateUser")
    public ResultDto updateUser(@RequestBody SUser user) {
        userService.updateUser(user);
        return ResultDto.success();
    }

    @PostMapping("/listUserLevelPage")
    public ResultDto listUserLevelPage(@RequestBody AdminUserLevel userLevel) {
        return ResultDto.success().setObj(userService.listUserLevelPage(userLevel));
    }

    @PostMapping("/insertUserLevel")
    public ResultDto insertUserLevel(@RequestBody UserLevel userLevel) {
        userService.insertUserLevel(userLevel);
        return ResultDto.success();
    }

    @PostMapping("/updateUserLevel")
    public ResultDto updateUserLevel(@RequestBody UserLevel userLevel) {
        userService.updateUserLevel(userLevel);
        return ResultDto.success();
    }

    @PostMapping("/deleteUserLevel")
    public ResultDto deleteUserLevel(@RequestBody UserLevel userLevel) {
        userService.deleteUserLevel(userLevel);
        return ResultDto.success();
    }

    @PostMapping("/listUserCoupon")
    public ResultDto listUserCoupon(@RequestBody UserCoupon userCoupon) {
        return ResultDto.success().setObj(userService.listUserCoupon(userCoupon));
    }

    @PostMapping("/getUserCouponDetail")
    public ResultDto getUserCouponDetail(@RequestBody UserCoupon userCoupon) {
        return userService.getUserCouponDetail(userCoupon);
    }

    @PostMapping("/listUserWithdraw")
    public ResultDto listUserWithdraw(@RequestBody AdminUserWithdraw adminUserWithdraw) {
        return ResultDto.success().setObj(userService.listUserWithdraw(adminUserWithdraw));
    }

    @PostMapping("/updateUserWithdrawStatus")
    public ResultDto updateUserWithdrawStatus(@RequestBody UserWithdraw userWithdraw) {
        return userService.updateUserWithdrawStatus(userWithdraw);
    }
}
