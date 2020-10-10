package com.tian.sakura.cdd.db.manage.order;

import com.tian.sakura.cdd.common.entity.AbstractSingleManage;
import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.common.req.order.AdminOrderReq;
import com.tian.sakura.cdd.db.dao.order.OrderMapper;
import com.tian.sakura.cdd.db.domain.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 说明。
 *
 * @author lvzonggang
 */
@Service
@Transactional
public class OrderManage extends AbstractSingleManage<Order, String> {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    protected AbstractSingleMapper<Order, String> getSingleMapper() {
        return orderMapper;
    }

    public List<Order> queryOrderList(AdminOrderReq req) {
        return orderMapper.queryOrderList(req);
    }

    public List<Order> selectUnPayOrder(Date endTime){
        return orderMapper.selectUnPayOrder(endTime);
    }
}
