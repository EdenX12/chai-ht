package com.tian.sakura.cdd.srv.web.shop;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.tian.sakura.cdd.common.annotation.CustomerApiAuth;
import com.tian.sakura.cdd.common.dict.EUserType;
import com.tian.sakura.cdd.db.domain.user.SUser;
import com.tian.sakura.cdd.srv.filter.LoginUserThreadLocal;
import com.tian.sakura.cdd.srv.service.order.PrdOrderApiServcie;
import com.tian.sakura.cdd.srv.service.order.PrdOrderQueryService;
import com.tian.sakura.cdd.srv.service.shop.ShopOrderService;
import com.tian.sakura.cdd.srv.web.order.dto.PrdOrderCancelReq;
import com.tian.sakura.cdd.srv.web.order.dto.PrdOrderDetailQueryReq;
import com.tian.sakura.cdd.srv.web.order.dto.PrdOrderDetailQueryReqBody;
import com.tian.sakura.cdd.srv.web.order.dto.PrdOrderDetailQueryRspBody;
import com.tian.sakura.cdd.srv.web.order.dto.PrdOrderQueryReq;
import com.tian.sakura.cdd.srv.web.order.dto.PrdOrderQueryRspBody;
import com.tian.sakura.cdd.srv.web.order.dto.PrdOrderSendReq;
import com.tian.sakura.cdd.srv.web.shop.dto.order.ShopOrderReq;
import com.tian.sakura.cdd.srv.web.shop.dto.order.ShopOrderReqBody;
import com.tian.sakura.cdd.srv.web.shop.dto.order.ShopOrderRspBody;

import io.swagger.annotations.Api;
/**
 * 商铺订单
 * @author liuhg
 *
 */
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("shopOrder")
@Api("商铺-订单")
public class ShopOrderController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private PrdOrderQueryService orderQueryService;
    @Autowired
    private PrdOrderApiServcie prdOrderApiServcie;
    @Autowired
    private ShopOrderService shopOrderService;
    
    @ApiOperation("商铺订单查询")
    @PostMapping("list")
    @CustomerApiAuth(EUserType.NORMAL_CUST)
    public PageInfo<ShopOrderRspBody> selectOrderList(@RequestBody  @Valid ShopOrderReq req) {
        return orderQueryService.selectShopOrderByPage(LoginUserThreadLocal.getLoginUser(), req.getBody());
    }

    @ApiOperation("订单详情")
    @PostMapping("orderDetail")
    @CustomerApiAuth(EUserType.NORMAL_CUST)
    public PrdOrderDetailQueryRspBody orderDetail(@RequestBody  @Valid PrdOrderDetailQueryReq req) {
    	
    	return orderQueryService.selectOrderDetailForShop(LoginUserThreadLocal.getLoginUser(), req);
    }
    
    
    @ApiOperation("商铺发货")
    @PostMapping("delivery")
    @CustomerApiAuth(EUserType.NORMAL_CUST)
    public void delivery(@RequestBody PrdOrderSendReq req) {
    	shopOrderService.sendPrdOrder(LoginUserThreadLocal.getLoginUser(), req);
    }

    @ApiOperation("已取货")
    @PostMapping("pickedUp") 
    @CustomerApiAuth(EUserType.NORMAL_CUST)
    public void pickedUp(@RequestBody PrdOrderDetailQueryReq req) {
    	shopOrderService.setPickupFlag(LoginUserThreadLocal.getLoginUser(), req.getBody());
    }

    @ApiOperation("取消订单")
    @PostMapping("orderCancel")
    @CustomerApiAuth(EUserType.NORMAL_CUST)
    public void cancelOrder(@RequestBody PrdOrderCancelReq req) {
    	shopOrderService.cancelPrdOrder(LoginUserThreadLocal.getLoginUser(), req);
    }
    
    @ApiOperation("同意退货")
    @PostMapping("agree")
    @CustomerApiAuth(EUserType.NORMAL_CUST)
    public void agree() {
    	
    }
    @ApiOperation("拒绝退货")
    @PostMapping("disagree")
    @CustomerApiAuth(EUserType.NORMAL_CUST)
    public void disagree() {
    	
    }
    @ApiOperation("同意退款")
    @PostMapping("agreeReturnMoney")
    @CustomerApiAuth(EUserType.NORMAL_CUST)
    public void agreeReturnMoney() {
    	
    }

}
