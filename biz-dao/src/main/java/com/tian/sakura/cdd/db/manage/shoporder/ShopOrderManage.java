package com.tian.sakura.cdd.db.manage.shoporder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tian.sakura.cdd.common.entity.AbstractSingleManage;
import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.dao.order.OrderProductMapper;
import com.tian.sakura.cdd.db.domain.order.OrderProduct;

@Service
public class ShopOrderManage extends AbstractSingleManage<OrderProduct, String> {
    @Autowired
    private OrderProductMapper orderProductMapper;

	@Override
	protected AbstractSingleMapper<OrderProduct, String> getSingleMapper() {

		return orderProductMapper;
	}

 }
