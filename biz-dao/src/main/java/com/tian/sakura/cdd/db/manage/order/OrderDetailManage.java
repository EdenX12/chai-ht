package com.tian.sakura.cdd.db.manage.order;

import com.tian.sakura.cdd.common.entity.AbstractSingleManage;
import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.common.entity.KeyValuePair;
import com.tian.sakura.cdd.common.req.order.AdminOrderDetailReq;
import com.tian.sakura.cdd.db.dao.order.OrderDetailMapper;
import com.tian.sakura.cdd.db.domain.order.OrderDetail;
import com.tian.sakura.cdd.db.manage.order.vo.OrderDetailQueryVo;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 订单明细表数据访问
 *
 * @author lvzonggang
 */
@Service
@Transactional
public class OrderDetailManage extends AbstractSingleManage<OrderDetail, String> {

	@Autowired
	private OrderDetailMapper orderDetailMapper;

	@Override
	protected AbstractSingleMapper<OrderDetail, String> getSingleMapper() {
		return orderDetailMapper;
	}

	public List<OrderDetail> selectByQueryVo(OrderDetailQueryVo queryVo) {
		return orderDetailMapper.selectByQueryVo(queryVo);
	}

	public List<OrderDetail> selectUnPayByQueryVo(OrderDetailQueryVo queryVo) {
		return orderDetailMapper.selectUnPayByQueryVo(queryVo);
	}

	public List<KeyValuePair> selectCntOfOrderStatusByUserId(String userId) {
		return orderDetailMapper.selectCntOfOrderStatusByUserId(userId);
	}

	public List<OrderDetail> selectByOrderId(String orderId) {
		return orderDetailMapper.selectByOrderId(orderId);
	}

	public void updateOrderDetailByOrderId(OrderDetail orderDetail) {
		orderDetailMapper.updateOrderDetailByOrderId(orderDetail);
	}

	public List<OrderDetail> listOrderDetail(AdminOrderDetailReq req) {
		return orderDetailMapper.listOrderDetail(req);
	}

	public Map hasPaidOrderCnt(String shopId, Date startDate, Date endDate) {
		return orderDetailMapper.hasPaidOrderCnt(shopId, startDate, endDate);
	}

	public List<OrderDetail> queryNotShipped(String shopId) {
		return orderDetailMapper.queryNotShipped(shopId);
	}

	public Map statOrderCnt(String shopId, Date startDate, Date endDate, Integer orderStatus) {
		return orderDetailMapper.statOrderCnt(shopId, startDate, endDate, orderStatus);
	}

	public Map taskOrderCnt(String shopId, Date startDate, Date endDate) {
		return orderDetailMapper.taskOrderCnt(shopId, startDate, endDate);
	}

	// 新增客户数统计
	public Map statCustCnt(String shopId, Date startDate, Date endDate) {
		return orderDetailMapper.statCustCnt(shopId, startDate, endDate);
	}

	// 重复购买客户数
	public Map repeatCustCnt(@Param("shopId") String shopId) {
		return orderDetailMapper.repeatCustCnt(shopId);
	}

	public Map getSellQuantityByPrdId(String productId) {
		return orderDetailMapper.getSellQuantityByPrdId(productId);
	}

	// 根据状态分组统计
	public List<Map> statOrderCntByStatus(String shopId, Date startDate, Date endDate) {
		return orderDetailMapper.statOrderCntByStatus(shopId, startDate, endDate);
	}
	
	//商铺销量
	public Map getSellQuantityByShopId(String shopId) {
		return orderDetailMapper.getSellQuantityByShopId(shopId);
	}
}
