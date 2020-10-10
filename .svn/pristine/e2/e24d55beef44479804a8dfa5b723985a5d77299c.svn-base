package com.tian.sakura.cdd.db.dao.order;

import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.common.entity.KeyValuePair;
import com.tian.sakura.cdd.common.req.order.AdminOrderDetailReq;
import com.tian.sakura.cdd.db.domain.order.OrderDetail;
import com.tian.sakura.cdd.db.manage.order.vo.OrderDetailQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface OrderDetailMapper extends AbstractSingleMapper<OrderDetail, String> {
	// 查询其他状态的订单  以order_detail表为基表
	List<OrderDetail> selectByQueryVo(OrderDetailQueryVo queryVo);

	// 查询未支付的订单  以order表为基表
	List<OrderDetail> selectUnPayByQueryVo(OrderDetailQueryVo queryVo);

	List<KeyValuePair> selectCntOfOrderStatusByUserId(@Param("userId") String userId);

	List<OrderDetail> selectByOrderId(String orderId);

	int updateOrderDetailByOrderId(OrderDetail orderDetail);

	List<OrderDetail> listOrderDetail(AdminOrderDetailReq req);

	Map hasPaidOrderCnt(@Param("shopId") String shopId, @Param("startDate") Date startDate,
			@Param("endDate") Date endDate);

	List<OrderDetail> queryNotShipped(@Param("shopId") String shopId);

	// 根据状态统计
	Map statOrderCnt(@Param("shopId") String shopId, @Param("startDate") Date startDate, @Param("endDate") Date endDate,
			@Param("orderStatus") Integer orderStatus);
	
	// 拆单统计
	Map taskOrderCnt(@Param("shopId") String shopId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	// 新增客户数统计
	Map statCustCnt(@Param("shopId") String shopId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

	//重复购买客户数
	Map repeatCustCnt(@Param("shopId") String shopId);
	
	//商品销量
	Map getSellQuantityByPrdId(String productId);

	// 根据状态统计
	List<Map> statOrderCntByStatus(@Param("shopId") String shopId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	//商铺销量
	Map  getSellQuantityByShopId(String shopId);

    List<OrderDetail> orderDetailShippingCode();

	int updateShippingCode(@Param("id") String id, @Param("shippingCode") String shippingCode);
}