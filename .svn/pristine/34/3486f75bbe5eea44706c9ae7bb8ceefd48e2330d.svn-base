package com.tian.sakura.cdd.srv.service.shop;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.tian.sakura.cdd.common.dict.EOrderStatus;
import com.tian.sakura.cdd.common.exception.BizRuntimeException;
import com.tian.sakura.cdd.common.resp.RespCodeEnum;
import com.tian.sakura.cdd.common.util.DateUtils;
import com.tian.sakura.cdd.db.domain.shopUser.ShopUser;
import com.tian.sakura.cdd.db.domain.user.SUser;
import com.tian.sakura.cdd.db.manage.order.OrderDetailManage;
import com.tian.sakura.cdd.db.manage.product.ProductManage;
import com.tian.sakura.cdd.db.manage.shop.ShopUserManage;
import com.tian.sakura.cdd.srv.web.shop.dto.data.ShopDataReq;
import com.tian.sakura.cdd.srv.web.shop.dto.data.ShopDataRspBody;
import com.tian.sakura.cdd.srv.web.shop.dto.data.ShopTodayDataReq;
import com.tian.sakura.cdd.srv.web.shop.dto.data.ShopTodayDataRspBody;

/**
 * 店铺数据
 * 
 * @author liuhg
 *
 */
@Service
@Transactional
public class ShopDataService {
	@Autowired
	private ShopUserManage shopUserManage;
	@Autowired
	private ProductManage productManage;
	@Autowired
	private OrderDetailManage orderDetailManage;

	// 获得店铺今日/历史数据
	public ShopTodayDataRspBody getShopTodayData(SUser user, ShopTodayDataReq req) {
		// 根据用户查商铺
		String userId = user.getId();
		ShopUser ss = shopUserManage.findByUserId(userId);
		if (ss == null) {
			throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE, "该用户没有商铺");
		}
		Integer shopId = ss.getShopId();
		Date startDate = new Date(), endDate = new Date();
		String strStartDate, strEndDate;

		Integer qryDurationType = req.getBody().getQryDurationType();
		switch (qryDurationType) {
		case 0:
			strStartDate = DateUtils.formatDateWithyyyy_MM_dd(new Date());
			startDate = DateTime.parse(strStartDate.concat("T00:00:01")).toDate();
			endDate = DateTime.parse(strStartDate.concat("T23:59:59")).toDate();
			break;
		case 1:
			strStartDate = getDate(-1);
			startDate = DateTime.parse(strStartDate.concat("T00:00:01")).toDate();
			endDate = DateTime.parse(strStartDate.concat("T23:59:59")).toDate();
			break;
		case 2:
			strStartDate = getDate(-7);
			strEndDate = getToday();
			startDate = DateTime.parse(strStartDate.concat("T00:00:01")).toDate();
			endDate = DateTime.parse(strEndDate.concat("T23:59:59")).toDate();

			break;
		case 3:
			strStartDate = getDate(-30);
			strEndDate = getToday();
			startDate = DateTime.parse(strStartDate.concat("T00:00:01")).toDate();
			endDate = DateTime.parse(strEndDate.concat("T23:59:59")).toDate();
			break;
		case 4:
			startDate = req.getBody().getStartDate();
			endDate = req.getBody().getEndDate();
			break;
		}

		// 订单数量与订单销售金额
		Map map = orderDetailManage.hasPaidOrderCnt(shopId.toString(), startDate, endDate);
		Long cnt = (Long) map.get("cnt");
		BigDecimal orderAmount = BigDecimal.ZERO,payAmount = BigDecimal.ZERO;
		if (cnt != 0) {
			orderAmount = (BigDecimal) map.get("orderAmount");
			payAmount = (BigDecimal) map.get("payAmount");
		}
		

		// 访客量
		Integer productFollowCnt = productManage.getShopProductFollwCnt(shopId);
		
		// 代发货数量
		Integer orderStatus = EOrderStatus.TO_BE_SEND.getCode();
		Map m = orderDetailManage.statOrderCnt(shopId.toString(), startDate, endDate, orderStatus);
		Long toBeSendCnt =(Long) m.get("cnt");

		// 退款中数量
		orderStatus = EOrderStatus.APPLY_BACK.getCode();
		m = orderDetailManage.statOrderCnt(shopId.toString(), startDate, endDate, orderStatus);
		Long applyBackCnt =(Long) m.get("cnt");

		// 拆单数量
		m = orderDetailManage.taskOrderCnt(shopId.toString(), startDate, endDate);
		Long taskCnt = (Long)  m.get("cnt");
		ShopTodayDataRspBody todayDataRspBody = new ShopTodayDataRspBody();
		todayDataRspBody.setOrderTotalCnt(cnt.intValue());
		todayDataRspBody.setOrderTotalAmount(orderAmount);
		todayDataRspBody.setOrderPayAmount(payAmount);
		todayDataRspBody.setFocusTotalCnt(productFollowCnt);
		todayDataRspBody.setToBeDeliveryTotalCnt(toBeSendCnt.intValue());
		todayDataRspBody.setReturnMoneyTotalCnt(applyBackCnt.intValue());
		todayDataRspBody.setTaskTotalCnt(taskCnt.intValue());

		return todayDataRspBody;
	}

	//获取店铺数据
	public ShopDataRspBody getShopData(SUser user, ShopDataReq req) {
		// 根据用户查商铺
		String userId = user.getId();
		ShopUser ss = shopUserManage.findByUserId(userId);
		if (ss == null) {
			throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE, "该用户没有商铺");
		}
		Integer shopId = ss.getShopId();

		//上架商品数量
		Integer productOnCnt = productManage.getShopProductOnCnt(shopId.toString());
		
		//关注人数
		Integer productFollowCnt = productManage.getShopProductFollwCnt(shopId);
		
		//被关注商品数量
		Integer productFocusCnt = productManage.getShopProductBeFollowedCnt(shopId);
		
		//新增客户
		String strStartDate = DateUtils.formatDateWithyyyy_MM_dd(new Date());
		Date startDate = DateTime.parse(strStartDate.concat("T00:00:01")).toDate();
		Date endDate = DateTime.parse(strStartDate.concat("T23:59:59")).toDate();

		Map map = orderDetailManage.statCustCnt(shopId.toString(), startDate, endDate);
		
		Long newCustCnt = (Long) map.get("cnt");
		
		//复购率
		map = orderDetailManage.statCustCnt(shopId.toString(), null, null);
		Long custCnt =(Long) map.get("cnt");

		//重付购买客户数
		map = orderDetailManage.repeatCustCnt(shopId.toString());
		Long repeatCustCnt = (Long) map.get("cnt");
		
		//客户满意度 ---需要补充
		
		ShopDataRspBody body = new ShopDataRspBody();
		body.setProductOnCnt(productOnCnt);
		body.setFocusCnt(productFollowCnt);
		body.setProductFocusCnt(productFocusCnt);
		body.setNewCustCnt(newCustCnt.intValue());
		if(custCnt == 0) {
			body.setRepeatBuyRate(BigDecimal.ZERO);
		}else {
			body.setRepeatBuyRate(new BigDecimal(repeatCustCnt/custCnt).setScale(2, BigDecimal.ROUND_HALF_UP));
		}
		body.setSatisfactionRate(100);
		
		
		
		return body;
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
