package com.tian.sakura.cdd.db.dao.order;

import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.domain.order.OrderProduct;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderProductMapper extends AbstractSingleMapper<OrderProduct, String> {

    List<OrderProduct> selectByOrderDetailIdAndUserId(
            @Param("orderDetailId") String orderDetailId, @Param("userId") String userId);

    List<OrderProduct> queryByOrderDetailId(@Param("orderDetailId") String orderDetailId);
}