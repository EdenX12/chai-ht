package com.tian.sakura.cdd.srv.service.order;

import com.alibaba.fastjson.JSON;
import com.tian.sakura.cdd.db.domain.order.Order;
import com.tian.sakura.cdd.db.domain.user.SUser;
import com.tian.sakura.cdd.db.manage.order.OrderManage;
import com.tian.sakura.cdd.srv.service.order.PrdOrderQueryService;
import com.tian.sakura.cdd.srv.web.order.dto.PrdOrderDetailQueryReq;
import com.tian.sakura.cdd.srv.web.order.dto.PrdOrderDetailQueryReqBody;
import com.tian.sakura.cdd.srv.web.order.dto.PrdOrderQueryReq;
import com.tian.sakura.cdd.srv.web.order.dto.PrdOrderQueryReqBody;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.tian.sakura.cdd.srv.GlobalConstants.PARAM_KEY_TASK_ORD_PAYTIME;

/**
 * 说明。
 *
 * @author lvzonggang
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PrdOrderQueryServiceTest {

    private SUser user;

    @Autowired
    private PrdOrderQueryService orderQueryService;
    @Autowired
    private OrderManage orderManage;

    @Before
    public void init() {
        user = new SUser();
        user.setId("3dc4db16e90848e18bcb91553f35eff7");
        user.setUserName("");
    }

    @Test
    public void selectByQueryVo() {
        PrdOrderQueryReq req = new PrdOrderQueryReq();
        PrdOrderQueryReqBody body = new PrdOrderQueryReqBody();
        body.setOrderStatus(null);
        req.setBody(body);

        System.out.println(JSON.toJSONString(orderQueryService.selectOrderByPage(user, req)));
    }

    @Test
    public void detail() {
        PrdOrderDetailQueryReq req = new PrdOrderDetailQueryReq();
        PrdOrderDetailQueryReqBody body = new PrdOrderDetailQueryReqBody();
        body.setId("dcb077369fe64eaca0239fc1686e40aa");
        req.setBody(body);

        System.out.println(JSON.toJSONString(orderQueryService.selectOrderDetailForCust(user, req)));
    }

    @Test
    public void testPayEndTime() {
        // 开始时间
        String orderId = "ffe3dd10b47f43fdbb7b6a277961b8f1";
        Order order = orderManage.selectByPrimary(orderId);
        long startTime = order.getCreateTime().getTime();
        long nowTime = System.currentTimeMillis();
        long diffTime = (nowTime - startTime) / 1000;
        Object value = 60;
        long payEndTime = Long.valueOf(value.toString()) * 60 -  diffTime;
        System.out.println(payEndTime);
    }
}
