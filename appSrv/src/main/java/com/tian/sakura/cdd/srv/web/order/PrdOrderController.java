package com.tian.sakura.cdd.srv.web.order;

import com.github.pagehelper.PageInfo;
import com.tian.sakura.cdd.common.annotation.CustomerApiAuth;
import com.tian.sakura.cdd.common.dict.EUserType;
import com.tian.sakura.cdd.db.domain.user.SUser;
import com.tian.sakura.cdd.srv.filter.LoginUserThreadLocal;
import com.tian.sakura.cdd.srv.service.order.PrdOrderApiServcie;
import com.tian.sakura.cdd.srv.service.order.PrdOrderQueryService;
import com.tian.sakura.cdd.srv.service.params.ParamsService;
import com.tian.sakura.cdd.srv.web.order.dto.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.tian.sakura.cdd.srv.GlobalConstants.PARAM_KEY_TASK_ORD_PAYTIME;

/**
 * 说明。
 *
 * @author lvzonggang
 */

@Api("商品订单模块API")
@RestController
@RequestMapping("order")
public class PrdOrderController {

    @Autowired
    private PrdOrderQueryService orderQueryService;
    @Autowired
    private PrdOrderApiServcie prdOrderApiServcie;
    @Autowired
    private ParamsService paramsService;

    @ApiOperation("订单查询-未支付")
    @PostMapping("unPaylist")
    @CustomerApiAuth(EUserType.NORMAL_CUST)
    public List<PrdOrderUnPayQueryRspBody> selectUnPayOrderList(@RequestBody PrdOrderQueryReq req) {
        return orderQueryService.selectUnPayOrderList(LoginUserThreadLocal.getLoginUser());
    }

    @ApiOperation("订单查询")
    @PostMapping("list")
    @CustomerApiAuth(EUserType.NORMAL_CUST)
    public PageInfo<PrdOrderQueryRspBody> selectOrderList(@RequestBody  @Valid PrdOrderQueryReq req) {
        return orderQueryService.selectOrderByPage(LoginUserThreadLocal.getLoginUser(), req);
    }

    @ApiOperation("订单详情")
    @PostMapping("detail")
    @CustomerApiAuth(EUserType.NORMAL_CUST)
    public PrdOrderDetailQueryRspBody selectOrderDetail(@RequestBody  @Valid PrdOrderDetailQueryReq req) {
        return orderQueryService.selectOrderDetailForCust(LoginUserThreadLocal.getLoginUser(), req);
    }

    @ApiOperation("商品下单")
    @PostMapping("create")
    @CustomerApiAuth(EUserType.NORMAL_CUST)
    public Map<String,Object> createPrdOrder(@RequestBody  @Valid PrdOrderCreateReq req) {
        //  支付成功 赠送的拆豆  商品数量 * 8
        String orderId = prdOrderApiServcie.createPrdOrder(LoginUserThreadLocal.getLoginUser(), req);
        Map<String, Object> result = new HashMap<>();
        result.put("orderId", orderId);
        // 支付截止时间
        Object value = paramsService.getValue(PARAM_KEY_TASK_ORD_PAYTIME);
        long payEndTime = 0L;
        if (value == null) {
            payEndTime = 30 * 60; //30分
        } else {
            payEndTime = Long.valueOf(value.toString())  * 60;
        }
        result.put("payEndTime", payEndTime);
        return result;
    }

    @ApiOperation("取消订单")
    @PostMapping("cancel")
    @CustomerApiAuth(EUserType.NORMAL_CUST)
    public void cancelPrdOrder(@RequestBody  @Valid PrdOrderCancelReq req) {
        prdOrderApiServcie.cancelPrdOrder(LoginUserThreadLocal.getLoginUser(), req);
    }

    @ApiOperation("支付订单")
    @PostMapping("pay")
    @CustomerApiAuth(EUserType.NORMAL_CUST)
    public PrdOrderPayRspBody payPrdOrder(@RequestBody  @Valid PrdOrderPayReq req) {
        //
        SUser user = LoginUserThreadLocal.getLoginUser();
        return prdOrderApiServcie.payPrdOrder(user, req);
    }


    @ApiOperation("确认收货")
    @PostMapping("confirmToReceive")
    @CustomerApiAuth(EUserType.BUSINESS)
    public void confirmToReceive(@RequestBody  @Valid PrdOrderReceiveReq req) {
        prdOrderApiServcie.confirmToReceive(LoginUserThreadLocal.getLoginUser(), req);
    }
}
