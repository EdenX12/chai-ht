package com.tian.sakura.cdd.srv.service.order;

import com.tian.sakura.cdd.common.dict.EPayType;
import com.tian.sakura.cdd.common.web.RequestHead;
import com.tian.sakura.cdd.db.domain.user.SUser;
import com.tian.sakura.cdd.db.domain.user.UserPay;
import com.tian.sakura.cdd.db.manage.product.ProductSpecManage;
import com.tian.sakura.cdd.db.manage.user.UserPayManage;
import com.tian.sakura.cdd.srv.GlobalConstants;
import com.tian.sakura.cdd.srv.service.params.ParamsService;
import com.tian.sakura.cdd.srv.web.order.dto.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 说明。
 *
 * @author lvzonggang
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class PrdOrderApiServiceTest {
    private SUser user;

    @Autowired
    private PrdOrderApiServcie prdOrderApiServcie;

    @Autowired
    private ProductSpecManage productSpecManage;
    @Autowired
    private UserPayManage userPayManage;
    @Autowired
    private ParamsService paramsService;

    @Before
    public void init() {
        user = new SUser();
        user.setId("4c1a585dfa1f4f0b90ecde9bff5e4ede");
        user.setUserName("15900955397");
    }

    @Test
    public void xx() {
        productSpecManage.decreaseStock(19, 1);
    }

    @Test
    public void creatOrder() {
        PrdOrderCreateReq req = new PrdOrderCreateReq();
        RequestHead head = new RequestHead();
        req.setHead(head);

        PrdOrderCreateReqBody body = new PrdOrderCreateReqBody();
        //地址信息
        body.setAddressId("1");

        //店铺-产品信息
        List<OrderShopPrdBody> orderShopPrdBodyList = new ArrayList<>();
        OrderShopPrdBody shopPrdBody = new OrderShopPrdBody();

        shopPrdBody.setShopId("1"); //

        //产品
        List<OrderPrdBody> orderPrdBodyList = new ArrayList<>();
        OrderPrdBody orderPrdBody = OrderPrdBody.builder()
                .productId("1c986d07050148cab15df05a37d50abb")
                .productName("龙脑洗发水")
                .productPrice(new BigDecimal("68"))
                .productNumber(2)
                .productSpecId("28")
                .productSpecValueName("瓶")
                .build();
        orderPrdBodyList.add(orderPrdBody);
        shopPrdBody.setOrderPrdBodyList(orderPrdBodyList);

        //shopPrdBody.setUserCouponId("");
        //shopPrdBody.setCouponAmount(new BigDecimal());
        BigDecimal productNumber = new BigDecimal(orderPrdBody.getProductNumber());
        BigDecimal orderAmt = orderPrdBody.getProductPrice().multiply(productNumber);

        shopPrdBody.setOrderAmount(orderAmt);
        shopPrdBody.setPayAmount(orderAmt);
        shopPrdBody.setShippingFee(BigDecimal.ZERO);
        shopPrdBody.setDeliverExplain("备注---");
        orderShopPrdBodyList.add(shopPrdBody);

        body.setOrderShopPrdBodyList(orderShopPrdBodyList);
        //总费用
        body.setTotalAmount(orderAmt);
        body.setPayAmount(orderAmt);
        body.setTotalCouponAmount(BigDecimal.ZERO);


        req.setBody(body);
        prdOrderApiServcie.createPrdOrder(user, req);
    }

    @Test
    public void testRetrun() {
        UserPay userPay = userPayManage.selectByRelationId("3fceeca8128441c899940dbe359e9ca2");

        Object radio = paramsService.getValue(GlobalConstants.BUYER_RATE);
        BigDecimal rtnCash = userPay.getTotalAmount().multiply(new BigDecimal(radio.toString()));
        System.out.println("rtnCash " + rtnCash);

        Object beanCnt = paramsService.getValue(GlobalConstants.PARAM_KEY_PRODUCT_BEAN_CNT);
        System.out.println(Integer.valueOf(beanCnt.toString()));
    }

    @Test
    public void payOrder() {
        PrdOrderPayReq payReq = new PrdOrderPayReq();

        PrdOrderPayReqBody body = new PrdOrderPayReqBody();
        body.setPayType(EPayType.BALANCE.getCode());
        body.setOrderId("e69220eaa44247c4aa28a135b539f30b");

        payReq.setBody(body);

        prdOrderApiServcie.payPrdOrder(user, payReq);
    }

    @Test
    public void cancelOrder() {
        PrdOrderCancelReq req = new PrdOrderCancelReq();
        PrdOrderCancelReqBody body = new PrdOrderCancelReqBody();
        body.setOrderId("4a6fab274e43423d8ee16e04256e0171");
        body.setCancelReason("信息填写错误");
        req.setBody(body);

        prdOrderApiServcie.cancelPrdOrder(user, req);
    }


    @Test
    public void confirmReceiveOrder() {
        PrdOrderReceiveReq req = new PrdOrderReceiveReq();

        PrdOrderReceiveReqBody body = new PrdOrderReceiveReqBody();
        body.setOrderDetailId("5ec219875a8b4b5da12b2974e1fb70e7");
        req.setBody(body);

        prdOrderApiServcie.confirmToReceive(user, req);

    }
}
