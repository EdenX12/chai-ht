package com.tian.sakura.cdd.srv.web.user;

import com.tian.sakura.cdd.common.annotation.CustomerApiAuth;
import com.tian.sakura.cdd.common.dict.EUserType;
import com.tian.sakura.cdd.db.domain.user.UserAddress;
import com.tian.sakura.cdd.srv.filter.LoginUserThreadLocal;
import com.tian.sakura.cdd.srv.service.user.UserAddressApiService;
import com.tian.sakura.cdd.srv.web.user.dto.UserAddressReq;
import com.tian.sakura.cdd.srv.web.user.dto.UserAddressRspBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 用户地址api
 *
 * @author lvzonggang
 */

@RestController
@RequestMapping("address")
@Api("用户地址模块api")
public class UserAddressController {

    @Autowired
    private UserAddressApiService userAddressApiService;

    @ApiOperation("用户地址列表")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @CustomerApiAuth(EUserType.NORMAL_CUST)
    public List<UserAddressRspBody> list() {
        return userAddressApiService.selectByUserId(LoginUserThreadLocal.getLoginUser().getId());
    }

    @ApiOperation("新增地址")
    @PostMapping("add")
    @CustomerApiAuth(EUserType.NORMAL_CUST)
    public void add(@RequestBody @Valid UserAddressReq req) {
        UserAddress userAddress = doBuildUserAddress(req.getBody());
        userAddressApiService.addAddress(userAddress);
    }

    @ApiOperation("修改地址")
    @PostMapping("update")
    @CustomerApiAuth(EUserType.NORMAL_CUST)
    public void update(@RequestBody @Valid UserAddressReq req) {
        UserAddress userAddress = doBuildUserAddress(req.getBody());
        userAddressApiService.updateAddress(userAddress);
    }

    private UserAddress doBuildUserAddress(UserAddressRspBody body) {
        UserAddress userAddress = new UserAddress();
        BeanUtils.copyProperties(body, userAddress);
        userAddress.setIsDefault(body.getDefaultFlag() ? "1" : "0");
        userAddress.setUserId(LoginUserThreadLocal.getLoginUser().getId());
        return userAddress;
    }

    @ApiOperation("删除地址")
    @ApiParam(name="addressId", value = "地址标识")
    @CustomerApiAuth(EUserType.NORMAL_CUST)
    @PostMapping("delete/{addressId}")
    public void delete(@PathVariable String addressId) {
        userAddressApiService.deleteById(addressId);
    }
}
