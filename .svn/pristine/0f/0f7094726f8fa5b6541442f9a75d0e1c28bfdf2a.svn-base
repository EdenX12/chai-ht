package com.tian.sakura.cdd.srv.web.task;

import com.tian.sakura.cdd.common.annotation.CustomerApiAuth;
import com.tian.sakura.cdd.common.dict.EUserType;
import com.tian.sakura.cdd.db.domain.user.SUser;
import com.tian.sakura.cdd.srv.filter.LoginUserThreadLocal;
import com.tian.sakura.cdd.srv.service.task.UserTaskOrderApiService;
import com.tian.sakura.cdd.srv.service.task.UserTaskOrderApiValidator;
import com.tian.sakura.cdd.srv.web.task.dto.TaskOrderCreateReq;
import com.tian.sakura.cdd.srv.web.task.dto.TaskOrderCreateRspBody;
import com.tian.sakura.cdd.srv.web.task.dto.TaskOrderPayReq;
import com.tian.sakura.cdd.srv.web.task.dto.TaskOrderPayRspBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 任务金下单模块
 *
 * @author lvzonggang
 */

@Api("用户任务金订单模块")
@RestController
@RequestMapping("userTask")
public class UserTaskOrderController {

    @Autowired
    private UserTaskOrderApiService taskOrderApiService;
    @Autowired
    private UserTaskOrderApiValidator userTaskOrderApiValidator;

    @ApiOperation("创建任务金订单")
    @PostMapping("createOrder")
    @CustomerApiAuth(EUserType.NORMAL_CUST)
    public TaskOrderCreateRspBody createTaskOrder(@RequestBody @Valid TaskOrderCreateReq req) {
        SUser user = LoginUserThreadLocal.getLoginUser();
        //清理未支付的任务金订单
        taskOrderApiService.cleanTaskOrder(user);
        // 校验请求
        userTaskOrderApiValidator.validateTaskOrderForCreate(user, req);
        return taskOrderApiService.createTaskOrder(user, req);
    }

    @ApiOperation("支付任务金订单")
    @PostMapping("payOrder")
    public TaskOrderPayRspBody payTaskOrder(@RequestBody @Valid TaskOrderPayReq req) {
        SUser user = LoginUserThreadLocal.getLoginUser();
        userTaskOrderApiValidator.validateTaskOrderForPay(user, req);
        return taskOrderApiService.payTaskOrder(user, req);
    }

    @ApiOperation("取消支付任务订单")
    @PostMapping("canlePayOrder")
    public void cancelPayTaskOrder(@RequestBody TaskOrderPayReq req) {
        SUser user = LoginUserThreadLocal.getLoginUser();
        taskOrderApiService.cancelPayTaskOrder(user, req);
    }
}
