package com.tian.sakura.cdd.srv.service.task;

import com.alibaba.fastjson.JSON;
import com.tian.sakura.cdd.common.dict.EPayStatus;
import com.tian.sakura.cdd.common.dict.EPayType;
import com.tian.sakura.cdd.common.util.BeanMapTransUtils;
import com.tian.sakura.cdd.common.util.IdGenUtil;
import com.tian.sakura.cdd.common.web.ResponseBody;
import com.tian.sakura.cdd.db.domain.user.SUser;
import com.tian.sakura.cdd.db.manage.task.TaskLineManage;
import com.tian.sakura.cdd.srv.applistener.event.TaskOrderPayEvent;
import com.tian.sakura.cdd.srv.service.pay.TaskPayCallBackContext;
import com.tian.sakura.cdd.srv.web.task.dto.*;
import com.tian.sakura.cdd.wx.message.pay.UnifiedOrderReq;
import com.tian.sakura.cdd.wx.util.WxUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.tian.sakura.cdd.srv.GlobalConstants.*;

/**
 * 说明。
 *
 * @author lvzonggang
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserTaskOrderApiServiceTest {

    @Autowired
    private UserTaskOrderApiService userTaskOrderApiService;

    @Autowired
    private UserTaskOrderApiValidator userTaskOrderApiValidator;

    @Autowired
    private TaskLineManage taskLineManage;

    @Autowired
    private ApplicationContext applicationContext;
    private SUser user;
    @Before
    public void mockUser() {
        this.user = new SUser();
        user.setUserPhone("15900955396");
        user.setUserName("15900955396");
        user.setId("59fbae33b345412a92c3004a72e52649");
        //user.setId("727ce088cbf7e8f19a2eb48d6fb58304");
        user.setRewardBean(400);
    }

    @Test
    public void testLockTask() {
        List<String> ids = Arrays.asList(new String[]{"1","2"});

        taskLineManage.lockTaskByIds(ids);
        taskLineManage.unLockTaskByIds(ids);

    }

    @Test
    public void cleanTask() {

        userTaskOrderApiService.cleanTaskOrder(user);
    }

    @Test
    public void validateTaskOrderForCreate() {
        TaskOrderCreateReq req = new TaskOrderCreateReq();

        TaskOrderCreateReqBody body = new TaskOrderCreateReqBody();
        body.setProductId("3ecf9e7b3c8046e7921e86d4a02b1c4a");
        body.setTaskCount(1);
        body.setPayAmount(new BigDecimal("0.1"));
        body.setTaskTotalAmount(new BigDecimal("0.1"));
        //body.setUserCouponId("1");
        //body.setCouponAmount(BigDecimal.ONE);

        req.setBody(body);
        userTaskOrderApiValidator.validateTaskOrderForCreate(user, req);
    }


    /**
     * 1
     */
    @Test
    public void createTaskOrder() {

        TaskOrderCreateReq req = new TaskOrderCreateReq();

        TaskOrderCreateReqBody body = new TaskOrderCreateReqBody();
        body.setProductId("1c986d07050148cab15df05a37d50abb");
        body.setTaskCount(1);
        body.setPayAmount(new BigDecimal("0.01"));
        body.setTaskTotalAmount(new BigDecimal("0.01"));
        //body.setUserCouponId();
        //body.setCouponAmount();

        req.setBody(body);

        userTaskOrderApiService.createTaskOrder(user, req);
    }

    @Test
    public void cancelTaskOrder() {
        TaskOrderPayReq req = new TaskOrderPayReq();
        TaskOrderPayReqBody body = new TaskOrderPayReqBody();
        body.setPayType("0");
        body.setTaskId("3908b3dfef2d4f5989037a01574d5de2");
        //body.setIp("127.0.0.1");
        req.setBody(body);

        userTaskOrderApiService.cancelPayTaskOrder(user, req);
    }

    @Test
    public void payTaskByBalance() {

        TaskOrderPayReq req = new TaskOrderPayReq();
        TaskOrderPayReqBody body = new TaskOrderPayReqBody();
        body.setPayType(String.valueOf(EPayType.BALANCE.getCode()));
        body.setTaskId("5ec365cb4e544aaa9b89462cc9167cca");

        req.setBody(body);
        TaskOrderPayRspBody rspBody = userTaskOrderApiService.payTaskOrder(user, req);
        System.out.println(JSON.toJSONString(rspBody));

    }

    @Test
    public void publishEvent() {
        TaskPayCallBackContext context = new TaskPayCallBackContext();
        context.setUserId("f372ebb1a9634bd9996272f239fce7c2");

        applicationContext.publishEvent(new TaskOrderPayEvent(context));

        System.out.println("============");
    }


    @Test
    public void payTaskByWx() {
        SUser user = new SUser();
        user.setId("59fbae33b345412a92c3004a72e52649");
        TaskOrderPayReq req = new TaskOrderPayReq();
        TaskOrderPayReqBody body = new TaskOrderPayReqBody();
        body.setPayType(String.valueOf(EPayType.WX_PAY.getCode()));
        body.setTaskId("0503001");
        body.setIp("127.0.0.1");
        req.setBody(body);
        TaskOrderPayRspBody rspBody = userTaskOrderApiService.payTaskOrder(user, req);
        System.out.println(JSON.toJSONString(rspBody));
    }


}
