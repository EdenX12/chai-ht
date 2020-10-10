package com.tian.sakura.video.service.auth;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tian.sakura.cdd.common.req.order.AdminOrderDetailReq;
import com.tian.sakura.cdd.common.req.order.AdminOrderReq;
import com.tian.sakura.cdd.common.req.order.AdminUserTaskReq;
import com.tian.sakura.cdd.db.domain.express.Express;
import com.tian.sakura.cdd.db.domain.order.Order;
import com.tian.sakura.cdd.db.domain.order.OrderDetail;
import com.tian.sakura.cdd.db.domain.user.UserTask;
import com.tian.sakura.cdd.db.manage.express.ExpressManage;
import com.tian.sakura.cdd.db.manage.order.OrderDetailManage;
import com.tian.sakura.cdd.db.manage.order.OrderManage;
import com.tian.sakura.cdd.db.manage.order.OrderProductManage;
import com.tian.sakura.cdd.db.manage.user.UserTaskManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderManage orderManage;
    @Autowired
    private OrderDetailManage orderDetailManage;
    @Autowired
    private ExpressManage expressManage;
    @Autowired
    private OrderProductManage orderProductManage;
    @Autowired
    private UserTaskManage userTaskManage;

    public PageInfo<Order> queryOrderList(AdminOrderReq req) {
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        List<Order> orderList = orderManage.queryOrderList(req);
        return new PageInfo<>(orderList);
    }

    public PageInfo<OrderDetail> listOrderDetail(AdminOrderDetailReq req) {
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        List<OrderDetail> resultList = orderDetailManage.listOrderDetail(req);
        resultList.forEach(e -> {
            e.setOrderProductList(orderProductManage.queryByOrderDetailId(e.getId()));
        });
        return new PageInfo<>(resultList);
    }

    public void updateOrderLogistics(OrderDetail orderDetail) {
        OrderDetail update = new OrderDetail();
        update.setId(orderDetail.getId());
        update.setShippingExpressId(orderDetail.getShippingExpressId());
        Express express = expressManage.selectByPrimary(String.valueOf(orderDetail.getShippingExpressId()));
        update.setShippingExpressCode(express.getECode());
        update.setShippingCode(orderDetail.getShippingCode());
        update.setShippingTime(new Date());
        update.setDeliverExplain(orderDetail.getDeliverExplain());
        update.setOrderStatus(2);
        orderDetailManage.updateByPrimaryKeySelective(update);
    }

    public PageInfo<UserTask> listTaskOrder(AdminUserTaskReq userTask) {
        PageHelper.startPage(userTask.getPageNum(), userTask.getPageSize());
        List<UserTask> list = userTaskManage.listTaskOrder(userTask);
        return new PageInfo<>(list);
    }
}
