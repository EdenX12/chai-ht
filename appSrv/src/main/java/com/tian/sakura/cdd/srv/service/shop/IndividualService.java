package com.tian.sakura.cdd.srv.service.shop;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.util.ByteUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import org.springframework.web.multipart.MultipartFile;

import com.tian.sakura.cdd.common.exception.BizRuntimeException;
import com.tian.sakura.cdd.common.resp.RespCodeEnum;
import com.tian.sakura.cdd.common.util.IdGenUtil;
import com.tian.sakura.cdd.db.domain.area.Area;
import com.tian.sakura.cdd.db.domain.shop.Shop;
import com.tian.sakura.cdd.db.domain.shopCatlog.ShopCatlog;
import com.tian.sakura.cdd.db.domain.shopUser.ShopUser;
import com.tian.sakura.cdd.db.manage.area.AreaManage;
import com.tian.sakura.cdd.db.manage.params.ParamsManage;
import com.tian.sakura.cdd.db.manage.shop.ShopCatlogManage;
import com.tian.sakura.cdd.db.manage.shop.ShopManage;
import com.tian.sakura.cdd.db.manage.shop.ShopUserManage;
import com.tian.sakura.cdd.srv.GlobalConstants;
import com.tian.sakura.cdd.srv.web.shop.dto.IndividualBasicReq;
import com.tian.sakura.cdd.srv.web.shop.dto.IndividualBasicReqBody;
import com.tian.sakura.cdd.srv.web.shop.dto.IndividualReqBody;
import com.tian.sakura.cdd.srv.web.shop.dto.IndividualRspBody;
import com.tian.sakura.video.service.core.OSSCore;
import com.tian.sakura.video.service.core.QRCodeCore;

/**
 * 个人入驻服务
 * 
 * @author liuhg
 *
 */
@Service
@Transactional
public class IndividualService {
	@Autowired
	private ShopManage shopManage;
	@Autowired
	private ShopCatlogManage shopCatlogManage;
	@Autowired
	private ShopUserManage shopUserManage;
	@Autowired
	private OSSCore OSSCore;
	@Autowired
	private AreaManage areaManage;
	@Autowired
	private QRCodeCore qrCodeCore;
	@Autowired
	private ParamsManage params;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private static ExecutorService executorService = Executors.newFixedThreadPool(4);

	/**
	 * 个人入驻基本信息提交
	 */
	public IndividualRspBody individualBasicInfo(String userId, IndividualBasicReqBody reqBody) {
		ShopUser shopUser = shopUserManage.findByUserId(userId);
		if (shopUser != null) {
			throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE, "该用户已有商铺，不允许再申请");
		}
		Shop individualShop = new Shop();
		individualShop.setAddTime(new Date());
		individualShop.setCallCenter(reqBody.getCallCenter());
		individualShop.setCreateOper(userId);
		individualShop.setCreateTime(new Date());
		individualShop.setEmail(reqBody.getEmail());
		individualShop.setLegalPerson(reqBody.getIndividualName());
		individualShop.setMobile(reqBody.getMobile());
		Area area = areaManage.selectByPrimary(reqBody.getProvince());
		if (area != null) {
			individualShop.setShopProvince(area.getAreaName());
		}
		area = areaManage.selectByPrimary(reqBody.getCity());
		if (area != null) {
			individualShop.setShopCity(area.getAreaName());
		}
		area = areaManage.selectByPrimary(reqBody.getCountry());
		if (area != null) {
			individualShop.setShopCountry(area.getAreaName());
		}
		individualShop.setProvinceId(reqBody.getProvince());
		individualShop.setCityId(reqBody.getCity());
		individualShop.setCountryId(reqBody.getCountry());

		individualShop.setShopArea(reqBody.getAddress());
		individualShop.setShopName(reqBody.getShopName());
		individualShop.setShopNo(IdGenUtil.generateId());// 商铺编号
		individualShop.setShopType(0);// 个人商铺
		shopManage.basicInsert(individualShop);
		int shopId = individualShop.getId();
		// 二维码
		String rqCodeUrl = qrCodeCore.encode(params.getValueByKey(GlobalConstants.CONTEXT));
		Shop another = new Shop();
		another.setId(shopId);
		another.setQrCode(rqCodeUrl);
		shopManage.updateByPrimaryKeySelective(another);

		// 经营类目
		List<Integer> catlogList = reqBody.getShopCatlog();
		if (catlogList != null) {
			for (Integer catlogId : catlogList) {
				ShopCatlog shopCatlog = new ShopCatlog();
				shopCatlog.setShopId(shopId);
				shopCatlog.setProductTypeId(catlogId);
				shopCatlogManage.insert(shopCatlog);
			}
		}

		shopUser = new ShopUser();
		shopUser.setUserId(userId);
		shopUser.setShopId(shopId);
		shopUser.setStatus(0);

		shopUserManage.insert(shopUser);

		IndividualRspBody rspBody = new IndividualRspBody();
		rspBody.setShopId(shopId);
		return rspBody;
	}

	/**
	 * 个人入驻上传身份证信息
	 */
	public IndividualRspBody individualIdCardInfo(IndividualReqBody req) {
		// 校验门店id和userId的关系

		logger.info(">>>>>>>>>>>>>>1 individualIdCardInfo");
		Integer shopId = req.getShopId();
		MultipartFile idCardFront = req.getIdCardFront();
		logger.info(">>>>>>>>>>>>>>1.1 individualIdCardInfo" + idCardFront.getSize());
		MultipartFile idCardBack = req.getIdCardBack();
		logger.info(">>>>>>>>>>>>>>1.2 individualIdCardInfo" + idCardBack.getSize());
//		String idCardExpire = req.getIdCardExpire();
//		String idCardNo = req.getIdCardNo();

//		String idFrontUrl = null;
//		if (idCardFront != null) {
//			idFrontUrl = OSSCore.upload(idCardFront);
//		}
//
//		String idBackUrl = null;
//		if (idCardBack != null) {
//			idBackUrl = OSSCore.upload(idCardBack);
//		}
//
//		Shop shop = new Shop();
//		shop.setId(shopId);
//		shop.setFrontId(idFrontUrl);
//		shop.setBackId(idBackUrl);
//		shop.setIdCardExpire(idCardExpire);
//		shop.setIdCard(idCardNo);
//		if (idFrontUrl != null || idBackUrl != null || StringUtils.isNoneBlank(idCardExpire)
//				|| StringUtils.isNoneBlank(idCardNo)) {
//			shopManage.updateByPrimaryKeySelective(shop);
//		}
		// 异步处理图片，因图片大小影响处理速度
		IndividualReqBody bodyBack = null;
		try {
			bodyBack = new IndividualReqBody();
			bodyBack.setIdCardNo(req.getIdCardNo());
			bodyBack.setIdCardExpire(req.getIdCardExpire());
			byte[] frontBytes = req.getIdCardFront().getBytes();
			byte[] frontBackBytes = new byte[frontBytes.length];
			System.arraycopy(frontBytes, 0, frontBackBytes, 0,frontBytes.length);
			MultipartFile fileFront = new MockMultipartFile(req.getIdCardFront().getName(), frontBackBytes);
			bodyBack.setIdCardFront(fileFront);

			byte[] backBytes = req.getIdCardBack().getBytes();
			byte[] backBackBytes = new byte[backBytes.length];
			System.arraycopy(backBytes, 0, backBackBytes, 0,backBytes.length);
			MultipartFile fileBack = new MockMultipartFile(req.getIdCardBack().getName(), backBackBytes);
			bodyBack.setIdCardBack(fileBack);

			bodyBack.setShopId(req.getShopId());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		asynUploadCard(bodyBack);
		logger.info(">>>>>>>>>>>>>>5 individualIdCardInfo");
		IndividualRspBody rspBody = new IndividualRspBody();
		rspBody.setShopId(shopId);
		return rspBody;
	}

	private void asynUploadCard(final IndividualReqBody req) {
		executorService.execute(() -> {
			logger.info(">>>>>>>>>>>>>>1 asynUploadCard");
			String idFrontUrl = null;
			if (req.getIdCardFront() != null) {
				try {
					idFrontUrl = OSSCore.upload(req.getIdCardFront());
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}

			}
			logger.info(">>>>>>>>>>>>>>2 asynUploadCard");
			String idBackUrl = null;
			if (req.getIdCardBack() != null) {
				idBackUrl = OSSCore.upload(req.getIdCardBack());
			}
			logger.info(">>>>>>>>>>>>>>3 asynUploadCard");
			Shop shop = new Shop();
			shop.setId(req.getShopId());
			shop.setFrontId(idFrontUrl);
			shop.setBackId(idBackUrl);
			shop.setIdCardExpire(req.getIdCardExpire());
			shop.setIdCard(req.getIdCardNo());
			if (idFrontUrl != null || idBackUrl != null || StringUtils.isNoneBlank(req.getIdCardExpire())
					|| StringUtils.isNoneBlank(req.getIdCardNo())) {
				shopManage.updateByPrimaryKeySelective(shop);
			}
			logger.info(">>>>>>>>>>>>>>4 asynUploadCard");
		});
	}

}
