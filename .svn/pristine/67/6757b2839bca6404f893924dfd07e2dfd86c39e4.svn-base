package com.tian.sakura.cdd.srv.web.user;

import com.github.pagehelper.PageInfo;
import com.tian.sakura.cdd.common.annotation.CustomerApiAuth;
import com.tian.sakura.cdd.common.dict.EUserType;
import com.tian.sakura.cdd.db.domain.user.SUser;
import com.tian.sakura.cdd.srv.filter.LoginUserThreadLocal;
import com.tian.sakura.cdd.srv.service.user.WithdrawApiService;
import com.tian.sakura.cdd.srv.web.user.dto.WithdrawApplyReq;
import com.tian.sakura.cdd.srv.web.user.dto.WithdrawQueryReq;
import com.tian.sakura.cdd.srv.web.user.dto.WithdrawQueryRsqBody;
import com.tian.sakura.cdd.srv.web.user.dto.WithdrawRspBody;
import io.swagger.annotations.Api;
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

@Api("提现模块")
@RestController
@RequestMapping("withdraw")
public class UserWithdrawController {

    @Autowired
    private WithdrawApiService withdrawApiService;

    @ApiOperation("提现申请")
    @PostMapping("apply")
    @CustomerApiAuth(EUserType.NORMAL_CUST)
    public void applyWithdraw(@RequestBody @Valid WithdrawApplyReq req) {
        SUser user = LoginUserThreadLocal.getLoginUser();

        withdrawApiService.applyWithdraw(user, req);
    }
    @ApiOperation("查询提现列表")
    @PostMapping("list")
    @CustomerApiAuth(EUserType.NORMAL_CUST)
    public PageInfo<WithdrawRspBody> list(@RequestBody WithdrawQueryReq req) {
        SUser user = LoginUserThreadLocal.getLoginUser();

        return withdrawApiService.list(user, req);
    }
}
