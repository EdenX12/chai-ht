package com.tian.sakura.cdd.db.dao.order;

import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.common.req.order.AdminOrderReq;
import com.tian.sakura.cdd.db.domain.order.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderMapper  extends AbstractSingleMapper<Order, String> {

    List<Order> queryOrderList(AdminOrderReq req);

    List<Order> selectUnPayOrder(@Param("endPayTime") Date endTime);
}