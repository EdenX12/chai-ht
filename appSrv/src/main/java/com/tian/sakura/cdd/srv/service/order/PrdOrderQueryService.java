package com.tian.sakura.cdd.srv.service.order;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tian.sakura.cdd.common.dict.EDeliveryType;
import com.tian.sakura.cdd.common.dict.EOrderQueryStatus;
import com.tian.sakura.cdd.common.dict.EOrderStatus;
import com.tian.sakura.cdd.common.dict.EPayStatus;
import com.tian.sakura.cdd.common.exception.BizRuntimeException;
import com.tian.sakura.cdd.common.resp.RespCodeEnum;
import com.tian.sakura.cdd.common.util.DateUtils;
import com.tian.sakura.cdd.db.domain.order.OrderDetail;
import com.tian.sakura.cdd.db.domain.order.OrderProduct;
import com.tian.sakura.cdd.db.domain.shop.Shop;
import com.tian.sakura.cdd.db.domain.shopUser.ShopUser;
import com.tian.sakura.cdd.db.domain.user.SUser;
import com.tian.sakura.cdd.db.manage.order.OrderDetailManage;
import com.tian.sakura.cdd.db.manage.order.OrderProductManage;
import com.tian.sakura.cdd.db.manage.order.vo.OrderDetailQueryVo;
import com.tian.sakura.cdd.db.manage.params.ParamsManage;
import com.tian.sakura.cdd.db.manage.shop.ShopManage;
import com.tian.sakura.cdd.db.manage.shop.ShopUserManage;
import com.tian.sakura.cdd.db.manage.user.SUserManage;
import com.tian.sakura.cdd.srv.builder.PrdOrderBuilder;
import com.tian.sakura.cdd.srv.service.params.ParamsService;
import com.tian.sakura.cdd.srv.web.order.dto.*;
import com.tian.sakura.cdd.srv.web.product.dto.PrdRspBody;
import com.tian.sakura.cdd.srv.web.shop.dto.order.ShopOrderReqBody;
import com.tian.sakura.cdd.srv.web.shop.dto.order.ShopOrderRspBody;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.tian.sakura.cdd.srv.GlobalConstants.BUYER_RATE;
import static com.tian.sakura.cdd.srv.GlobalConstants.PARAM_KEY_PRODUCT_BEAN_CNT;
import static com.tian.sakura.cdd.srv.GlobalConstants.PARAM_KEY_TASK_ORD_PAYTIME;
import static com.tian.sakura.cdd.srv.builder.PrdOrderBuilder.toQueryOrderStatus;

/**
 * 说明。
 *
 * @author lvzonggang
 */

@Service
@Transactional
public class PrdOrderQueryService {

	@Autowired
	private OrderDetailManage orderDetailManage;

	@Autowired
	private OrderProductManage orderProductManage;
	@Autowired
	private ShopManage shopManage;

	@Autowired
	private ParamsService paramsService;

	@Autowired
	private ShopUserManage shopUserManage;
	@Autowired
	private SUserManage userManage;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public List<PrdOrderUnPayQueryRspBody> selectUnPayOrderList(SUser user) {
		OrderDetailQueryVo queryVo = new OrderDetailQueryVo();
		queryVo.setUserId(user.getId());
		queryVo.setOrderStatus(EOrderStatus.TO_BE_PAY.getCode());

		List<OrderDetail> orderDetailList = orderDetailManage.selectUnPayByQueryVo(queryVo);

		// 遍历数据
		Map<String, List<PrdOrderQueryRspBody>> orderMap = new HashMap<>();
		List<PrdOrderQueryRspBody> orderDetailBody = null;
		for (OrderDetail detail : orderDetailList) {
			String orderId = detail.getOrderId();

			if (orderMap.containsKey(orderId)) {
				orderDetailBody = orderMap.get(orderId);
			} else {
				orderDetailBody = new ArrayList<>();
			}

			PrdOrderQueryRspBody body = toOrderDetailBody(detail);
			orderDetailBody.add(body);

			orderMap.put(orderId, orderDetailBody);
		}

		List<PrdOrderUnPayQueryRspBody> result = new ArrayList<>();
		for (Map.Entry<String, List<PrdOrderQueryRspBody>> entry : orderMap.entrySet()) {
			PrdOrderUnPayQueryRspBody unPayQueryRspBody = new PrdOrderUnPayQueryRspBody();
			unPayQueryRspBody.setOrderId(entry.getKey());
			// 开始时间
			long startTime = entry.getValue().get(0).getCreateTimeValue();
			long nowTime = System.currentTimeMillis();
			long diffTime = (nowTime - startTime) / 1000;
			Object value = paramsService.getValue(PARAM_KEY_TASK_ORD_PAYTIME);
			long payEndTime = Long.valueOf(value.toString()) * 60 -  diffTime;
			unPayQueryRspBody.setPayEndTime(payEndTime < 0 ? 0 : payEndTime);
			unPayQueryRspBody.setShopOrderList(entry.getValue());
			result.add(unPayQueryRspBody);
		}

		return result;
	}

	private PrdOrderQueryRspBody toOrderDetailBody(OrderDetail detail) {
		PrdOrderQueryRspBody body = new PrdOrderQueryRspBody();

		body.setId(detail.getId());
		body.setShopName(detail.getShopName());
		body.setOrderStatus(detail.getOrderStatus());
		body.setOrderStatusName(EOrderStatus.getNameByCode(detail.getOrderStatus()));
		body.setCreateTimeValue(detail.getCreateTime().getTime());
		List<OrderProduct> productList = detail.getOrderProductList();
		List<OrderPrdBody> orderProductList = new ArrayList<>();
		// 讲商品信息 复制到响应商品中
		productList.forEach(item -> {
			OrderPrdBody orderPrdBody = OrderPrdBody.builder().productId(item.getProductId())
					.productName(item.getProductName()).productSpecValueName(item.getProductSpecValueName())
					.productImg(item.getProductImg()).productPrice(item.getProductPrice())
					.productNumber(item.getProductNumber()).build();
			orderProductList.add(orderPrdBody);
			body.setOrderProductList(orderProductList);
		});

		return body;
	}

	public PageInfo<PrdOrderQueryRspBody> selectOrderByPage(SUser user, PrdOrderQueryReq req) {
		OrderDetailQueryVo queryVo = new OrderDetailQueryVo();
		queryVo.setUserId(user.getId());
		Integer orderStatus = req.getBody().getOrderStatus();
		if (orderStatus != null) {
			transformOrderStatus(orderStatus, queryVo);
		}
		queryVo.setPageNum(req.getBody().getPageNum());
		queryVo.setPageSize(req.getBody().getPageSize());
		PageHelper.startPage(queryVo.getPageNum(), queryVo.getPageSize());

		List<OrderDetail> orderDetailList = orderDetailManage.selectByQueryVo(queryVo);
		PageInfo<OrderDetail> pageInfo = PageInfo.of(orderDetailList);

		PageInfo<PrdOrderQueryRspBody> resultPage = new PageInfo<>();
		BeanUtils.copyProperties(pageInfo, resultPage);

		List<PrdOrderQueryRspBody> rspBodyList = new ArrayList<>();
		// 转化 api 响应报文
		for (OrderDetail detail : orderDetailList) {
			PrdOrderQueryRspBody body = new PrdOrderQueryRspBody();

			body.setId(detail.getId());
			body.setShopName(detail.getShopName());
			body.setOrderStatus(detail.getOrderStatus());
			body.setOrderStatusName(EOrderStatus.getNameByCode(detail.getOrderStatus()));
			if (detail.getPaymentState() == EPayStatus.TO_BE_PAY.getCode()) {
				// 开始时间
				long startTime = detail.getCreateTime().getTime();
				long nowTime = System.currentTimeMillis();
				long diffTime = (nowTime - startTime) / 1000;
				Object value = paramsService.getValue(PARAM_KEY_TASK_ORD_PAYTIME);
				long payEndTime = Long.valueOf(value.toString()) * 60 -  diffTime;
				body.setPayEndTime(payEndTime < 0 ? 0 : payEndTime);
			}
			List<OrderProduct> productList = detail.getOrderProductList();
			List<OrderPrdBody> orderProductList = new ArrayList<>();
			// 讲商品信息 复制到响应商品中
			productList.forEach(item -> {
				OrderPrdBody orderPrdBody = OrderPrdBody.builder().productId(item.getProductId())
						.productName(item.getProductName()).productSpecValueName(item.getProductSpecValueName())
						.productImg(item.getProductImg()).productPrice(item.getProductPrice())
						.productNumber(item.getProductNumber()).build();
				orderProductList.add(orderPrdBody);
				body.setOrderProductList(orderProductList);
			});

			rspBodyList.add(body);
		}

		resultPage.setList(rspBodyList);

		return resultPage;
	}

	private void transformOrderStatus(Integer orderStatus, OrderDetailQueryVo queryVo) {
		// 0-待付款;1-待发货;2-待收货;3-退还货;4-已完成, 5-待取货
		if (orderStatus == EOrderQueryStatus.TO_BE_PAY.getCode()
				|| orderStatus == EOrderQueryStatus.TO_BE_SEND.getCode()
				|| orderStatus == EOrderQueryStatus.TO_BE_RECEIVE.getCode()) {
			queryVo.setOrderStatus(orderStatus);
		} else if (orderStatus == 3) {
			StringBuilder sb = new StringBuilder("(").append(EOrderStatus.APPLY_BACK.getCode())
					.append(EOrderStatus.BACKED.getCode()).append(")");
			queryVo.setOrderStatusList(sb.toString());
		} else if (orderStatus == 4) {
			StringBuilder sb = new StringBuilder("(").append(EOrderStatus.CLOSED.getCode())
					.append(EOrderStatus.CANCLE.getCode()).append(EOrderStatus.CONFIRM_RECEIVED.getCode()).append(")");
			queryVo.setOrderStatusList(sb.toString());
		} else if (orderStatus == 5) {
			queryVo.setOrderStatus(EOrderStatus.SENDED.getCode());
			queryVo.setDeliveryType(EDeliveryType.OFFLINE_FETCH.getCode());

		}

	}

	public PrdOrderDetailQueryRspBody selectOrderDetailForShop(SUser user, PrdOrderDetailQueryReq req) {
		// 查询订单详情
		String orderDetailId = req.getBody().getId();
		OrderDetail orderDetail = orderDetailManage.selectByPrimary(orderDetailId);
		if (orderDetail == null) {
			throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE, "订单详情");
		}
		// 对比订单详情的shopid和当前登录着所拥有的门店id是否一致
		ShopUser shopUser = shopUserManage.findByUserId(user.getId());
		Integer shipId = Integer.valueOf(orderDetail.getShopId());
		if (shipId != shopUser.getShopId()) {
			throw new BizRuntimeException(RespCodeEnum.NOT_QUERY_RECORED);
		}
		return doSelectOrderDetail(orderDetail);
	}

	public PrdOrderDetailQueryRspBody selectOrderDetailForCust(SUser user, PrdOrderDetailQueryReq req) {
		// 查询订单详情
		String orderDetailId = req.getBody().getId();
		OrderDetail orderDetail = orderDetailManage.selectByPrimary(orderDetailId);
		if (orderDetail == null) {
			throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE, "订单详情");
		}
		// 对比订单详情的shopid和当前登录着所拥有的门店id是否一致
		if (!StringUtils.equals(orderDetail.getUserId(), user.getId())) {
			throw new BizRuntimeException(RespCodeEnum.NOT_QUERY_RECORED);
		}
		return doSelectOrderDetail(orderDetail);
	}

	private PrdOrderDetailQueryRspBody doSelectOrderDetail(OrderDetail orderDetail) {
		PrdOrderDetailQueryRspBody.PrdOrderDetailQueryRspBodyBuilder builder = PrdOrderDetailQueryRspBody.builder();
		String orderDetailId = orderDetail.getId();

		String orderBean = paramsService.getValueNoCache(PARAM_KEY_PRODUCT_BEAN_CNT);
		// 购买返现
		String rtnRate = paramsService.getValueNoCache(BUYER_RATE);
		logger.info("买家立返比例{}", rtnRate);
		Shop shop = shopManage.selectByPrimary(Integer.valueOf(orderDetail.getShopId()));
		SUser dbUser = userManage.selectByPrimary(orderDetail.getUserId());
		builder.id(orderDetailId).shopName(shop.getShopName()).orderStatus(orderDetail.getOrderStatus())
				// 地址信息
				.address(orderDetail.getAddressDetail()).addressName(orderDetail.getAddressName())
				.addressPhone(orderDetail.getAddressPhone())
				//
				.orderAmount(orderDetail.getOrderAmount())
				// 返现金额
				.rtnAmount(orderDetail.getOrderAmount().multiply(new BigDecimal(rtnRate)))
				// 赠送豆
				.orderBean(Integer.valueOf(orderBean)).orderSn(orderDetail.getOrderSn())
				.createTime(orderDetail.getCreateTime()).paymentTime(orderDetail.getPaymentTime())
				.nickName(dbUser.getNickName()).payAmount(orderDetail.getPayAmount())
				.shippingFee(orderDetail.getShippingFee()).couponAmount(orderDetail.getCouponAmount())
				.orderId(orderDetail.getOrderId())
				.payEndTime((new Date().getTime() - orderDetail.getCreateTime().getTime()) / 1000)
				.orderStatusName(EOrderStatus.getNameByCode(orderDetail.getOrderStatus()));

		// 订单产品
		List<OrderProduct> productList = orderProductManage.selectByOrderDetailIdAndUserId(orderDetailId, null);

		List<OrderPrdBody> orderProductList = new ArrayList<>();

		productList.forEach(item -> {
			OrderPrdBody body = OrderPrdBody.builder().productId(item.getProductId()).productName(item.getProductName())
					.productImg(item.getProductImg()).productNumber(item.getProductNumber())
					.productPrice(item.getProductPrice()).productSpecValueName(item.getProductSpecValueName())
					.productDesc(item.getProductDes()).build();
			orderProductList.add(body);
		});

		builder.orderProductList(orderProductList);

		return builder.build();
	}

	// 商铺查询订单
	public PageInfo<ShopOrderRspBody> selectShopOrderByPage(SUser user, ShopOrderReqBody reqBody) {
		// 根据用户查商铺
		ShopUser ss = shopUserManage.findByUserId(user.getId());
		if (ss == null) {
			throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE, "该用户没有商铺");
		}
		// 查商铺
		Integer shopId = ss.getShopId();

		Shop shop = shopManage.selectByPrimary(shopId);

		OrderDetailQueryVo queryVo = new OrderDetailQueryVo();
		queryVo.setShopId(shopId);
		queryVo.setPageNum(reqBody.getPageNum());
		queryVo.setPageSize(reqBody.getPageSize());
		queryVo.setOrderSn(reqBody.getOrderSn());
		queryVo.setAddressName(reqBody.getAddressName());
		queryVo.setAddressPhone(reqBody.getAddressPhone());
		if(StringUtils.isNoneBlank(reqBody.getOrderSn())) {
			queryVo.setKeyWord(reqBody.getOrderSn());
		}else if(StringUtils.isNoneBlank(reqBody.getAddressName())) {
			queryVo.setKeyWord(reqBody.getAddressName());
		}else if(StringUtils.isNoneBlank(reqBody.getAddressPhone())) {
			queryVo.setKeyWord(reqBody.getAddressPhone());
		}
		String orderStatus = reqBody.getOrderStatus();
		if (StringUtils.isNotEmpty(orderStatus)) {
			switch (Integer.valueOf(orderStatus)) {
			case 0:// 未付款
			case 1:// 已付款未发货
			case 2:// 已发货
				queryVo.setOrderStatus(Integer.valueOf(orderStatus));
				break;
			case 3:// 售后中，4 申请退货退款 ,5 已退货退款
				queryVo.setOrderStatusList("(4,5)");
				break;
			case 4: // 已完成
				queryVo.setOrderStatusList("(3,6,-1)");
				break;
			case 5:// 待取货,delivery_type=1，而且是已发货状态2
				queryVo.setOrderStatus(2);
				queryVo.setDeliveryType(1); // 线下自提
				break;
			}
		}
		Integer qryDurationType = reqBody.getQryDurationType();
		String startDate = getToday(), endDate = getToday();
		// 0-今天，1-昨天，2-近7天，3-近30天
		if (qryDurationType != null) {
			switch (qryDurationType) {
			case 0:
				startDate = DateUtils.formatDateWithyyyy_MM_dd(new Date());
				queryVo.setStartDate(DateTime.parse(startDate.concat("T00:00:01")).toDate());
				queryVo.setEndDate(DateTime.parse(startDate.concat("T23:59:59")).toDate());
				break;
			case 1:
				startDate = getDate(-1);
				queryVo.setStartDate(DateTime.parse(startDate.concat("T00:00:01")).toDate());
				queryVo.setEndDate(DateTime.parse(startDate.concat("T23:59:59")).toDate());
				break;
			case 2:
				startDate = getDate(-7);
				endDate = getToday();
				queryVo.setStartDate(DateTime.parse(startDate.concat("T00:00:01")).toDate());
				queryVo.setEndDate(DateTime.parse(endDate.concat("T23:59:59")).toDate());

				break;
			case 3:
				startDate = getDate(-30);
				endDate = getToday();
				queryVo.setStartDate(DateTime.parse(startDate.concat("T00:00:01")).toDate());
				queryVo.setEndDate(DateTime.parse(endDate.concat("T23:59:59")).toDate());
				break;
			}
		}
		PageHelper.startPage(reqBody.getPageNum(), reqBody.getPageSize());

		List<OrderDetail> orderDetailList = orderDetailManage.selectByQueryVo(queryVo);
		PageInfo<OrderDetail> pageInfo = PageInfo.of(orderDetailList);

		PageInfo<ShopOrderRspBody> resultPage = new PageInfo<>();
		BeanUtils.copyProperties(pageInfo, resultPage);

		List<ShopOrderRspBody> rspBodyList = new ArrayList<>();
		// 转化 api 响应报文
		for (OrderDetail detail : orderDetailList) {
			ShopOrderRspBody body = new ShopOrderRspBody();

			body.setId(detail.getId());
			body.setShopName(detail.getShopName());
			body.setNickName(detail.getNickName());
			body.setOrderStatus(detail.getOrderStatus());
			body.setOrderSn(detail.getOrderSn());
			body.setCreateTime(detail.getCreateTime().getTime());
			body.setPayAmount(detail.getPayAmount());
			body.setAddresName(detail.getAddressName());
			body.setAddressPhone(detail.getAddressPhone());
			body.setAddressDetail(detail.getAddressDetail());

			List<OrderProduct> productList = detail.getOrderProductList();
			List<OrderPrdBody> orderProductList = new ArrayList<>();

			// 讲商品信息 复制到响应商品中
			productList.forEach(item -> {
				OrderPrdBody orderPrdBody = OrderPrdBody.builder().productId(item.getProductId())
						.productName(item.getProductName()).productSpecValueName(item.getProductSpecValueName())
						.productImg(item.getProductImg()).productPrice(item.getProductPrice())
						.productNumber(item.getProductNumber()).deliveryType(item.getDeliveryType()).build();
				orderProductList.add(orderPrdBody);
				Integer tmp = item.getProductNumber();
				if (tmp == null) {
					tmp = 0;
				}
				body.setTaotalProductNumber(body.getTaotalProductNumber() + 0);
			});
			body.setOrderProductList(orderProductList);
			body.setOrderStatusName(EOrderStatus.getNameByCode(detail.getOrderStatus()));
			body.setUserImg(detail.getUserImg());
			body.setShopLogo(shop.getShopLogo());
			rspBodyList.add(body);
		}

		resultPage.setList(rspBodyList);

		return resultPage;
	}

	/**
	 * 
	 * 获取今天
	 * 
	 * @return String
	 * 
	 */
	public static String getToday() {
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	}

	/**
	 * * 获取前后几天 * @return String *
	 */
	public static String getDate(int days) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, days);
		Date time = cal.getTime();
		return new SimpleDateFormat("yyyy-MM-dd").format(time);
	}

}
