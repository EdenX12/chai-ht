package com.tian.sakura.cdd.db.manage.order;

import com.tian.sakura.cdd.common.entity.AbstractSingleManage;
import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.dao.order.OrderMapper;
import com.tian.sakura.cdd.db.dao.order.OrderProductMapper;
import com.tian.sakura.cdd.db.domain.order.Order;
import com.tian.sakura.cdd.db.domain.order.OrderProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 说明。
 *
 * @author lvzonggang
 */
@Service
@Transactional
public class OrderProductManage extends AbstractSingleManage<OrderProduct, String> {

    @Autowired
    private OrderProductMapper orderProductMapper;

    @Override
    protected AbstractSingleMapper<OrderProduct, String> getSingleMapper() {
        return orderProductMapper;
    }

    public List<OrderProduct> selectByOrderDetailIdAndUserId(String orderDetailId, String userId) {
        return orderProductMapper.selectByOrderDetailIdAndUserId(orderDetailId, userId);
    }

    public void batchInsertPrd(List<OrderProduct> orderProducts) {
        for (OrderProduct orderProduct : orderProducts) {
            orderProductMapper.insert(orderProduct);
        }
    }

    public List<OrderProduct> queryByOrderDetailId(String id) {
        return orderProductMapper.queryByOrderDetailId(id);
    }
}
