package com.tian.sakura.cdd.srv.web.login;

import com.tian.sakura.cdd.common.annotation.CustomerApiAuth;
import com.tian.sakura.cdd.common.dict.EUserType;
import com.tian.sakura.cdd.db.manage.login.CustAuthTokenManage;
import com.tian.sakura.cdd.srv.service.login.CustTokenService;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 说明。
 *
 * @author lvzonggang
 */

@RestController
@RequestMapping("cust")
@Api("客户信息模块API")
public class CustomerController {

    @Autowired
    private CustTokenService custTokenService;
    @Autowired
    private CustAuthTokenManage authTokenManage;

    @PostMapping("refreshToken")
    @CustomerApiAuth(value = {EUserType.NORMAL_CUST, EUserType.BUSINESS})
    public Map<String, Object> refreshToken(HttpServletRequest request, HttpServletResponse response) {
        String headerToken = request.getHeader("token");

        Map<String, Object> result = new HashMap<>();
        result.put("token", custTokenService.refreshToken(headerToken));
        return result;
    }
}
