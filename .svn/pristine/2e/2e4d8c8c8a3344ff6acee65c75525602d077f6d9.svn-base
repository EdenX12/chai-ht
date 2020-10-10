package com.tian.sakura.cdd.srv.service.shop;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.tian.sakura.cdd.srv.web.shop.dto.ProductDetailReqBody;
import com.tian.sakura.cdd.srv.web.shop.dto.ProductImgReqBody;
import com.tian.sakura.cdd.srv.web.shop.dto.ShopProductDeleteReqBody;
import com.tian.sakura.cdd.srv.web.shop.dto.ShopProductQryReq;
import com.tian.sakura.cdd.srv.web.shop.dto.ShopProductQryReqBody;
import com.tian.sakura.cdd.srv.web.shop.dto.ShopProductReq;
import com.tian.sakura.cdd.srv.web.shop.dto.ShopProductReqBody;

@SpringBootTest
@RunWith(SpringRunner.class)

public class ShopProductServiceTest {
	@Autowired
	private ShopProductService shopPrdService;
	
//	@Test
//	public void createProduct() {
//		
//		String userId="555";
//		ShopProductReq req =new ShopProductReq();
//		ShopProductReqBody body = new ShopProductReqBody();
//		req.setBody(body);
//		body.setDeliveryType(1);
//		body.setDiscountPrice(new BigDecimal(20));
//		body.setDiscountRate(new BigDecimal(0.8));
//		body.setFreight(new BigDecimal(50));
//		body.setGroupId(1);
//		body.setPrice(new BigDecimal(30));
//		body.setProductName("test测试");
//		body.setProductType1Id("17");
//		body.setProductType2Id("1");
//		body.setQuantity(100);
//		body.setStartDate(new Date());
//		shopPrdService.createProduct(userId, req);
//	}
	
	@Test
	public void uploadPrdImg() {
		String userId = "3dc4db16e90848e18bcb91553f35eff7";
		ProductImgReqBody body = new ProductImgReqBody();
		body.setImgType(1);
		//body.setProductId("a496d3ca1d59411c8b7198b17cb9015e");
		body.setProductImg("ddsdds");
		shopPrdService.uploadPrdImg(userId, body);
		
	}

//	@Test
//	public void uploadPrdDetail() {
//		String userId = "555";
//		ProductDetailReqBody body = new ProductDetailReqBody();
//		body.setProductId("b264a3be3ac640b7afc8448dbc172726");
//		body.setProductDetail("aaaaaaaaddsdds");
//		shopPrdService.productDetail(userId, body);
//		
//	}

//	@Test
//	public void updateProduct() {
//		
//		String userId="555";
//		ShopProductReq req =new ShopProductReq();
//		ShopProductReqBody body = new ShopProductReqBody();
//		req.setBody(body);
//		body.setProductId("b264a3be3ac640b7afc8448dbc172726");
//		body.setDeliveryType(1);
//		body.setDiscountPrice(new BigDecimal(20));
//		body.setDiscountRate(new BigDecimal(0.8));
//		body.setFreight(new BigDecimal(50));
//		body.setGroupId(1);
//		body.setPrice(new BigDecimal(30));
//		body.setProductName("test测试");
//		body.setProductType1Id("17");
//		body.setProductType2Id("1");
//		body.setQuantity(100);
//		body.setStartDate(new Date());
//		shopPrdService.editProduct(userId, body);
//	}

//	@Test
//	public void deleteProduct() {
//		String userId = "3dc4db16e90848e18bcb91553f35eff7";
//		ShopProductDeleteReqBody body = new ShopProductDeleteReqBody();
//		body.setProductId("e5d3e1d7eeb9458687edb1a53cc292e5");
//		shopPrdService.deleteProduct(userId, body);
//	}
//	
//	@Test
//	public void qryPrd() {
//		String userId = "3dc4db16e90848e18bcb91553f35eff7";
//		ShopProductQryReq req =new ShopProductQryReq();
//		ShopProductQryReqBody body = new ShopProductQryReqBody();
//		req.setBody(body);
//		body.setProductId("2897b240613d4e43a9559411fb50fb11");
//		Object obj = shopPrdService.qryProduct(userId, req);
//		System.out.println(JSON.toJSONString(obj));
//	}
//	@Test
//	public void productOnOff() {
//		String userId = "3dc4db16e90848e18bcb91553f35eff7";
//		ShopProductQryReq req =new ShopProductQryReq();
//		ShopProductQryReqBody body = new ShopProductQryReqBody();
//		req.setBody(body);
//		body.setProductStatus(1);
//		body.setProductId("2897b240613d4e43a9559411fb50fb11");
//		shopPrdService.productOnOff(userId, req);
//		
//	}
	@Test
	public void shopRecommendPrd() {
		Object obj = shopPrdService.getShopRecommendPrd();
		System.out.println(JSON.toJSONString(obj));
	}
}
