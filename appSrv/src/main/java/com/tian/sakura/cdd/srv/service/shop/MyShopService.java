package com.tian.sakura.cdd.srv.service.shop;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tian.sakura.cdd.common.exception.BizRuntimeException;
import com.tian.sakura.cdd.common.resp.RespCodeEnum;
import com.tian.sakura.cdd.db.domain.shop.Shop;
import com.tian.sakura.cdd.db.domain.shopUser.ShopUser;
import com.tian.sakura.cdd.db.domain.user.SUser;
import com.tian.sakura.cdd.db.manage.order.OrderDetailManage;
import com.tian.sakura.cdd.db.manage.params.ParamsManage;
import com.tian.sakura.cdd.db.manage.product.ProductManage;
import com.tian.sakura.cdd.db.manage.shop.ShopManage;
import com.tian.sakura.cdd.db.manage.shop.ShopUserManage;
import com.tian.sakura.cdd.db.manage.user.SUserManage;
import com.tian.sakura.cdd.srv.GlobalConstants;
import com.tian.sakura.cdd.srv.filter.LoginUserThreadLocal;
import com.tian.sakura.cdd.srv.web.shop.dto.MyShopReq;
import com.tian.sakura.cdd.srv.web.shop.dto.MyShopRspBody;
import com.tian.sakura.cdd.srv.web.shop.dto.ShopHeaderRspBody;
import com.tian.sakura.video.service.core.QRCodeCore;

@Service
public class MyShopService {
	@Autowired
	private ShopUserManage shopUserManage;
	@Autowired
	private ShopManage shopManage;
	@Autowired
	private ProductManage productManage;
	@Autowired
	private QRCodeCore qrCodeCore;
    @Autowired
    private ParamsManage params;
    @Autowired
    private SUserManage userManage;
    @Autowired
    private OrderDetailManage orderDetailManage;
    
	//分享二维码
	public ShopHeaderRspBody getQrCode(String userId) {
		// 根据用户查商铺
		ShopUser ss = shopUserManage.findByUserId(userId);
		if (ss == null) {
			throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE, "该用户没有商铺");
		}
		Integer   shopId = ss.getShopId();
		Shop shop = shopManage.selectByPrimary(shopId);
		// 商铺商品库存数量
		Integer productCnt = productManage.getShopProductCnt(shopId);
		// 关注人数
		Integer productFollowCnt = productManage.getShopProductFollwCnt(shopId);
		//二维码
		String qrCodeUrl = qrCodeCore.encode(params.getValueByKey(GlobalConstants.CONTEXT));

		
		//订单销量
		BigDecimal saleQuantity = BigDecimal.ZERO;
		Map map = orderDetailManage.getSellQuantityByShopId(shopId.toString());
		if(map == null) {
			saleQuantity = BigDecimal.ZERO;
		}else {
			saleQuantity = (BigDecimal) map.get("cnt");
		}
		
		ShopHeaderRspBody rspBody = new ShopHeaderRspBody();
		rspBody.setFollowCnt(productFollowCnt);
		rspBody.setProductCnt(productCnt);
		rspBody.setQrCodeUrl(qrCodeUrl);
		rspBody.setSaleQuantity(saleQuantity);
		rspBody.setShopId(shopId);
		rspBody.setShopLogo(shop.getShopLogo());
		rspBody.setShopName(shop.getShopName());
		rspBody.setQrCodeUrl(shop.getQrCode());

		return rspBody;
	}
	//账户及订单
	public MyShopRspBody getShopAccountOrder(String userId,MyShopReq req) {
		// 根据用户查商铺
		ShopUser ss = shopUserManage.findByUserId(userId);
		if (ss == null) {
			throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE, "该用户没有商铺");
		}
		Integer shopId = ss.getShopId();
		SUser user = userManage.selectByPrimary(userId);
		BigDecimal balance = user.getTotalAmount();//余额
		BigDecimal frozenBalance = user.getLockAmount();//冻结金额
		
		String strDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		Date startDate = DateTime.parse(strDate.concat("T00:00:01")).toDate();
		Date endDate = DateTime.parse(strDate.concat("T23:59:59")).toDate();
		
		List<Map> lists = orderDetailManage.statOrderCntByStatus(shopId.toString(), startDate, endDate);
		Long hasSentCnt = 0L,applyBackCnt = 0L,toBePayCnt= 0L,toBeSendCnt =0L ;
		
		for(Map map :lists) {
			Long orderCnt = (Long) map.get("cnt");
			Integer orderStatus = (Integer) map.get("orderStatus");
			Long cnt =(Long) map.get("cnt");
			if(cnt == null) {
				cnt = 0L;
			}
			
			if(orderStatus != null) {
				if(orderStatus == 1) {//待发货
					toBeSendCnt = cnt; 
				}else if(orderStatus == 0) {// 待付款
					toBePayCnt = cnt;
				}else if(orderStatus == 2 || orderStatus == 3) {// 已发货
					hasSentCnt = hasSentCnt + cnt;
				} else if(orderStatus == 4) {// 售后中
					applyBackCnt = cnt;
				} 
			}
			
		}
		
		MyShopRspBody rspBody =  new MyShopRspBody();
		rspBody.setApplyBackCnt(applyBackCnt.intValue());
		rspBody.setBalance(balance);
		rspBody.setFrozenBalance(frozenBalance);
		rspBody.setHasSentCnt(hasSentCnt.intValue());
		rspBody.setToBePayCnt(toBePayCnt.intValue());
		rspBody.setToBeSendCnt(toBeSendCnt.intValue());
		
		return rspBody;
	}

}
