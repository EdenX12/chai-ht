package com.tian.sakura.cdd.srv.web.user;

import com.github.pagehelper.PageInfo;
import com.tian.sakura.cdd.common.annotation.CustomerApiAuth;
import com.tian.sakura.cdd.common.dict.EUserType;
import com.tian.sakura.cdd.db.domain.user.SUser;
import com.tian.sakura.cdd.db.manage.log.AmountLogQueryVo;
import com.tian.sakura.cdd.srv.filter.LoginUserThreadLocal;
import com.tian.sakura.cdd.srv.service.user.UserApiService;
import com.tian.sakura.cdd.srv.web.user.dto.AmountLogQueryReq;
import com.tian.sakura.cdd.srv.web.user.dto.AmountLogQueryRsqBody;
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
@RequestMapping("amountlog")
public class UserAmountlogController {

    @Autowired
    private UserApiService userApiService;

    @PostMapping("list")
    @CustomerApiAuth(EUserType.NORMAL_CUST)
    public PageInfo<AmountLogQueryRsqBody> queryAmountLog(@RequestBody  @Valid AmountLogQueryReq req) {
        SUser user = LoginUserThreadLocal.getLoginUser();

        return userApiService.queryAmountLog(user, req);
    }
}
