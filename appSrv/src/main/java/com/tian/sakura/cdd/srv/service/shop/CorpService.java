package com.tian.sakura.cdd.srv.service.shop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.tian.sakura.cdd.db.domain.shopReturn.ShopReturn;
import com.tian.sakura.cdd.db.domain.shopUser.ShopUser;
import com.tian.sakura.cdd.db.domain.user.SUser;
import com.tian.sakura.cdd.db.manage.area.AreaManage;
import com.tian.sakura.cdd.db.manage.params.ParamsManage;
import com.tian.sakura.cdd.db.manage.shop.ShopCatlogManage;
import com.tian.sakura.cdd.db.manage.shop.ShopManage;
import com.tian.sakura.cdd.db.manage.shop.ShopReturnManage;
import com.tian.sakura.cdd.db.manage.shop.ShopUserManage;
import com.tian.sakura.cdd.srv.GlobalConstants;
import com.tian.sakura.cdd.srv.web.shop.dto.CorpBasicReqBody;
import com.tian.sakura.cdd.srv.web.shop.dto.CorpEditReqBody;
import com.tian.sakura.cdd.srv.web.shop.dto.CorpReqBody;
import com.tian.sakura.cdd.srv.web.shop.dto.CorpRspBody;
import com.tian.sakura.cdd.srv.web.shop.dto.IndividualReqBody;
import com.tian.sakura.cdd.srv.web.shop.dto.IsShopCompleteRspBody;
import com.tian.sakura.cdd.srv.web.shop.dto.order.AddressBody;
import com.tian.sakura.video.service.core.OSSCore;
import com.tian.sakura.video.service.core.QRCodeCore;

/**
 * 企业入驻
 * 
 * @author liuhg
 *
 */
@Service
@Transactional
public class CorpService {
	@Autowired
	private ShopManage shopManage;
	@Autowired
	private ShopCatlogManage shopCatlogManage;
	@Autowired
	private ShopUserManage shopUserManage;
	@Autowired
	private OSSCore OSSCore;
	@Autowired
	private ShopReturnManage shopReturnManage;
	@Autowired
	private AreaManage areaManage;

	@Autowired
	private QRCodeCore qrCodeCore;
	@Autowired
	private ParamsManage params;

	private static ExecutorService executorService = Executors.newFixedThreadPool(4);
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public CorpRspBody corpBasicInfo(String userId, CorpBasicReqBody reqBody) {
		ShopUser shopUser = shopUserManage.findByUserId(userId);
		if (shopUser != null) {
			throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE, "该用户已有商铺，不允许再申请");
		}
		Shop corpShop = new Shop();
		corpShop.setAddTime(new Date());
		corpShop.setCallCenter(reqBody.getCallCenter());
		corpShop.setCreateOper(userId);
		corpShop.setCreateTime(new Date());
		corpShop.setEmail(reqBody.getEmail());
		corpShop.setLegalPerson(reqBody.getIndividualName());
		corpShop.setMobile(reqBody.getMobile());
		Area area = areaManage.selectByPrimary(reqBody.getProvince());
		if (area != null) {
			corpShop.setShopProvince(area.getAreaName());
		}
		area = areaManage.selectByPrimary(reqBody.getCity());
		if (area != null) {
			corpShop.setShopCity(area.getAreaName());
		}

		corpShop.setShopArea(reqBody.getAddress());
		area = areaManage.selectByPrimary(reqBody.getCountry());
		if (area != null) {
			corpShop.setShopCountry(area.getAreaName());
		}

		corpShop.setShopName(reqBody.getShopName());
		corpShop.setShopNo(IdGenUtil.generateId());// 商铺编号
		corpShop.setShopType(1);// 企业商铺
		corpShop.setProvinceId(reqBody.getProvince());
		corpShop.setCityId(reqBody.getCity());
		corpShop.setCountryId(reqBody.getCountry());
		shopManage.basicInsert(corpShop);
		int shopId = corpShop.getId();
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
		;
		shopUserManage.insert(shopUser);

		CorpRspBody rspBody = new CorpRspBody();
		rspBody.setShopId(shopId);

		return rspBody;
	}

	/**
	 * 企业入驻上传身份证信息
	 */
	public CorpRspBody corpCardInfo(CorpReqBody req) {
		Integer shopId = req.getShopId();
		MultipartFile idCardFront = req.getIdCardFront();
		MultipartFile idCardBack = req.getIdCardBack();
		String idCardExpire = req.getIdCardExpire();
		String idCardNo = req.getIdCardNo();
		MultipartFile license = req.getLicense();
		String licenseNo = req.getLicenseNo();
		String corpName = req.getCorpName();

//		String idFrontUrl = null;
//		if (idCardFront != null) {
//			idFrontUrl = OSSCore.upload(idCardFront);
//		}
//		String idBackUrl = null;
//		if (idCardBack != null) {
//			idBackUrl = OSSCore.upload(idCardBack);
//		}
//		String licenseUrl = null;
//		if (license != null) {
//			licenseUrl = OSSCore.upload(license);
//		}
		try {
			byte[] idFronBytes = idCardFront.getBytes();
			byte[] idFronFrontBytes = new byte[idFronBytes.length];
			System.arraycopy(idFronBytes, 0, idFronFrontBytes, 0, idFronBytes.length);
			MultipartFile idFrontFile = new MockMultipartFile(idCardFront.getName(),idFronFrontBytes);

			byte[] idBackBytes = idCardBack.getBytes();
			byte[] idBackBacktBytes = new byte[idBackBytes.length];
			System.arraycopy(idBackBytes, 0, idBackBacktBytes, 0, idBackBytes.length);
			MultipartFile idBackFile = new MockMultipartFile(idCardBack.getName(),idBackBacktBytes);

			byte[] licenseBytes = license.getBytes();
			byte[] licenseLicenseBytes = new byte[licenseBytes.length];
			System.arraycopy(licenseBytes, 0, licenseLicenseBytes, 0, licenseBytes.length);
			MultipartFile licenseFile = new MockMultipartFile(license.getName(),licenseLicenseBytes);
			
			asynUploadCard(shopId, null,idFrontFile,idBackFile,licenseFile);
			
		} catch (IOException ex) {
			logger.error(ex.getMessage());
		}

		
		Shop shop = new Shop();
		shop.setId(shopId);
		// shop.setFrontId(idFrontUrl);
		// shop.setBackId(idBackUrl);
		shop.setIdCardExpire(idCardExpire);
		shop.setIdCard(idCardNo);
		// shop.setLicense(licenseUrl);
		shop.setLicenseNo(licenseNo);
		shop.setCorpName(corpName);

		shopManage.updateByPrimaryKeySelective(shop);
		CorpRspBody rspBody = new CorpRspBody();
		rspBody.setShopId(shopId);
		return rspBody;
	}

	private void asynUploadCard(Integer shopId, MultipartFile idFront, MultipartFile idBack, MultipartFile license) {

	}

	/**
	 * 个人/企业商铺完善资料
	 * 
	 * @param req
	 * @return
	 */
	public CorpRspBody editInfo(String userId, CorpEditReqBody req) {
		Integer shopId = req.getShopId();
		if (shopId == null) {
			throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE, "商铺Id不存在");
		}
		ShopUser shopUser = shopUserManage.findByUserId(userId);
		if (shopUser == null) {
			throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE, "该用户没有商铺");
		}
		if (shopId != shopUser.getShopId()) {
			throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE, "该用户没有商铺");
		}

		MultipartFile shopLogo = req.getShopLogo();
		String shopLogoUrl = null;
//		if (shopLogo != null) {
//			shopLogoUrl = OSSCore.upload(shopLogo);
//		}
		try {
			byte[] shopLogoBytes = shopLogo.getBytes();
			byte[] shopLogoBytes2 = new byte[shopLogoBytes.length];
			System.arraycopy(shopLogoBytes, 0, shopLogoBytes2, 0, shopLogoBytes.length);
			MultipartFile shopLogofile = new MockMultipartFile(shopLogo.getName(), shopLogoBytes2);
			asynUploadCard(shopId, shopLogofile,null,null,null);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}

		String weixin = req.getWeixin();
		String qq = req.getQq();

		Shop shop = new Shop();
		shop.setId(shopId);
		shop.setShopLogo(shopLogoUrl);
		shop.setWeixin(weixin);
		shop.setQq(qq);
		if (StringUtils.isNotBlank(shopLogoUrl) || StringUtils.isNotBlank(shopLogoUrl) || StringUtils.isNotBlank(weixin)
				|| StringUtils.isNotBlank(qq)) {
			shopManage.updateByPrimaryKeySelective(shop);
		}
		// 退货地址
		List<AddressBody> l = req.getShopReturnList();
		if (l != null && l.size() > 0) {
			shopReturnManage.deleteByShopId(shopId, 1);

			for (AddressBody returnAddress : l) {
				ShopReturn s = new ShopReturn();
				s.setShopId(shopId);
				s.setProvince(areaManage.selectByPrimary(returnAddress.getProvinceId()).getAreaName());
				s.setCity(areaManage.selectByPrimary(returnAddress.getCityId()).getAreaName());
				s.setCountry(areaManage.selectByPrimary(returnAddress.getCountryId()).getAreaName());
				s.setAddress(returnAddress.getAddress());
				s.setCreateTime(new Date());
				s.setAddressType(1);
				s.setProvinceId(returnAddress.getProvinceId());
				s.setCityId(returnAddress.getCityId());
				s.setCountryId(returnAddress.getCountryId());
				shopReturnManage.insert(s);
			}
		}

		// 自提货地址
		List<AddressBody> l2 = req.getSelfAddressList();
		if (l2 != null && l2.size() > 0) {
			shopReturnManage.deleteByShopId(shopId, 2);

			for (AddressBody selfAddress : l2) {
				ShopReturn s = new ShopReturn();
				s.setShopId(shopId);
				s.setProvince(areaManage.selectByPrimary(selfAddress.getProvinceId()).getAreaName());
				s.setCity(areaManage.selectByPrimary(selfAddress.getCityId()).getAreaName());
				s.setCountry(areaManage.selectByPrimary(selfAddress.getCountryId()).getAreaName());
				s.setAddress(selfAddress.getAddress());
				s.setCreateTime(new Date());
				s.setAddressType(2);
				s.setProvinceId(selfAddress.getProvinceId());
				s.setCityId(selfAddress.getCityId());
				s.setCountryId(selfAddress.getCountryId());
				shopReturnManage.insert(s);
			}

		}

		CorpRspBody rspBody = new CorpRspBody();

		BeanUtils.copyProperties(req, rspBody);
		Shop another = shopManage.selectByPrimary(shopId);
		rspBody.setAddress(another.getShopArea());
		rspBody.setCallCenter(another.getCallCenter());
		rspBody.setCity(another.getShopCity());
		rspBody.setCityId(another.getCityId());
		rspBody.setCorpName(another.getCorpName());
		rspBody.setCountry(another.getShopCountry());
		rspBody.setCountryId(another.getCountryId());
		rspBody.setEmail(another.getEmail());
		rspBody.setIdCardBack(another.getBackId());
		rspBody.setIdCardExpire(another.getIdCardExpire());
		rspBody.setIdCardFront(another.getFrontId());
		rspBody.setIdCardNo(another.getIdCard());
		rspBody.setIndividualName(another.getLegalPerson());
		rspBody.setLicense(another.getLicense());
		rspBody.setLicenseNo(another.getLicenseNo());
		rspBody.setMobile(another.getMobile());
		rspBody.setProvince(another.getShopProvince());
		rspBody.setProvinceId(another.getProvinceId());
		rspBody.setQq(another.getQq());
		// 自提货地址
		List<ShopReturn> shopSelfs = shopReturnManage.qryShopReturnByShopId(shopId, 2);
		List<AddressBody> ls = new ArrayList<>();
		for (ShopReturn s : shopSelfs) {
			AddressBody addressBody = new AddressBody();
			addressBody.setAddress(s.getAddress());
			addressBody.setCityId(s.getCityId());
			addressBody.setCityName(s.getCity());
			addressBody.setCountryId(s.getCountryId());
			addressBody.setCountryName(s.getCountry());
			addressBody.setProvinceId(s.getProvinceId());
			addressBody.setProvinceName(s.getProvince());
			ls.add(addressBody);
		}
		rspBody.setSelfAddressList(ls);

		// 店铺经营类目
		List<ShopCatlog> shopCatlogs = shopCatlogManage.qryShopCatlog(shopId);
		List<HashMap> h = new ArrayList<>();
		for (ShopCatlog sc : shopCatlogs) {
			HashMap m = new HashMap();
			m.put("catalogId", sc.getId());
			m.put("catalogName", sc.getProductTypeName());
			h.add(m);
		}
		rspBody.setShopCatlog(h);

		rspBody.setShopId(another.getId());
		rspBody.setShopLogo(another.getShopLogo());
		rspBody.setShopName(another.getShopName());
		rspBody.setShopNo(another.getShopNo());
		// 退货地址
		List<ShopReturn> shopReturns = shopReturnManage.qryShopReturnByShopId(shopId, 1);
		List<AddressBody> lr = new ArrayList<>();
		for (ShopReturn s : shopReturns) {
			AddressBody addressBody = new AddressBody();
			addressBody.setAddress(s.getAddress());
			addressBody.setCityId(s.getCityId());
			addressBody.setCityName(s.getCity());
			addressBody.setCountryId(s.getCountryId());
			addressBody.setCountryName(s.getCountry());
			addressBody.setProvinceId(s.getProvinceId());
			addressBody.setProvinceName(s.getProvince());
			lr.add(addressBody);
		}
		rspBody.setShopReturnList(lr);
		rspBody.setShopType(another.getShopType());
		rspBody.setWeixin(another.getWeixin());

		return rspBody;
	}

	/**
	 * 查询店铺信息
	 * 
	 * @param userId
	 * @return
	 */
	public CorpRspBody qryShopInfo(String userId) {
		ShopUser ss = shopUserManage.findByUserId(userId);
		if (ss == null) {
			throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE, "该用户没有商铺");
		}
		// 店铺信息
		Integer shopId = ss.getShopId();
		Shop shop = shopManage.selectByPrimary(shopId);
		// 退货地址
		List<ShopReturn> shopReturns = shopReturnManage.qryShopReturnByShopId(shopId, 1);
		List<AddressBody> rl = new ArrayList<>();
		for (ShopReturn r : shopReturns) {
			AddressBody a = new AddressBody();
			a.setProvinceId(r.getProvinceId());
			a.setProvinceName(r.getProvince());
			a.setCityId(r.getCityId());
			a.setCityName(r.getCity());
			a.setCountryId(r.getCountryId());
			a.setCountryName(r.getCountry());
			rl.add(a);
		}

		// 自提货地址
		List<ShopReturn> selfAddress = shopReturnManage.qryShopReturnByShopId(shopId, 2);
		List<AddressBody> self = new ArrayList<>();
		for (ShopReturn r : selfAddress) {
			AddressBody a = new AddressBody();
			a.setProvinceId(r.getProvinceId());
			a.setProvinceName(r.getProvince());
			a.setCityId(r.getCityId());
			a.setCityName(r.getCity());
			a.setCountryId(r.getCountryId());
			a.setCountryName(r.getCountry());
			self.add(a);
		}

		// 店铺经营类目
		List<ShopCatlog> shopCatlogs = shopCatlogManage.qryShopCatlog(shopId);
		List<HashMap> l = new ArrayList<>();
		for (ShopCatlog sc : shopCatlogs) {
			HashMap m = new HashMap();
			m.put("catalogId", sc.getId());
			m.put("catalogName", sc.getProductTypeName());
			l.add(m);
		}

		CorpRspBody rspBody = new CorpRspBody();

		rspBody.setShopId(shopId);
		rspBody.setAddress(shop.getShopArea());
		rspBody.setCallCenter(shop.getCallCenter());

		rspBody.setCity(shop.getShopCity());
		rspBody.setCityId(shop.getCityId());

		rspBody.setCorpName(shop.getCorpName());

		rspBody.setCountry(shop.getShopCountry());
		rspBody.setCountryId(shop.getCountryId());

		rspBody.setEmail(shop.getEmail());
		rspBody.setIdCardBack(shop.getBackId());
		rspBody.setIdCardExpire(shop.getIdCardExpire());
		rspBody.setIdCardFront(shop.getFrontId());
		rspBody.setIdCardNo(shop.getIdCard());
		rspBody.setIndividualName(shop.getLegalPerson());
		rspBody.setLicense(shop.getLicense());
		rspBody.setLicenseNo(shop.getLicenseNo());
		rspBody.setMobile(shop.getMobile());

		rspBody.setProvince(shop.getShopProvince());
		rspBody.setProvinceId(shop.getProvinceId());

		rspBody.setQq(shop.getQq());
		rspBody.setShopCatlog(l);
		rspBody.setShopLogo(shop.getShopLogo());
		rspBody.setShopName(shop.getShopName());
		rspBody.setShopReturnList(rl);
		rspBody.setWeixin(shop.getWeixin());
		rspBody.setShopNo(shop.getShopNo());
		rspBody.setSelfAddressList(self);
		rspBody.setShopType(shop.getShopType());
		return rspBody;
	}

	public IsShopCompleteRspBody isShopComplete(SUser user) {
		IsShopCompleteRspBody rspBody = new IsShopCompleteRspBody();
		ShopUser shopUser = shopUserManage.findByUserId(user.getId());
		if (shopUser == null) {
			rspBody.setFlag("0");
			return rspBody;
		}
		Integer shopId = shopUser.getShopId();
		Shop shop = shopManage.selectByPrimary(shopId);

		if (shop == null) {
			rspBody.setFlag("0");
		} else if (shop.getShopNo() == null || StringUtils.isEmpty(shop.getIdCard())
				|| StringUtils.isEmpty(shop.getShopLogo())) {
			rspBody.setFlag("1");
		} else {
			rspBody.setFlag("2");
		}

		return rspBody;
	}

	private void asynUploadCard(Integer shopId, MultipartFile shopLogofile,
			MultipartFile idFrontFile,MultipartFile idBackFile,MultipartFile licenseFile) {
		executorService.execute(() -> {
			
			String shopLogoUrl = null;
			if (shopLogofile != null) {
				try {
					shopLogoUrl = OSSCore.upload(shopLogofile);
					logger.info(">>>>>>>>>>>>>>shopLogo asynUploadCard");
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}

			}
			String idFrontUrl = null;
			if(idFrontFile != null) {
				try {
					idFrontUrl = OSSCore.upload(idFrontFile);
					logger.info(">>>>>>>>>>>>>>idFront asynUploadCard");
				}catch(Exception ex) {
					logger.error(ex.getMessage());
				}
			}
			
			String idBackUrl = null;
			if(idBackFile != null) {
				try {
					idBackUrl = OSSCore.upload(idBackFile);
					logger.info(">>>>>>>>>>>>>>idBack asynUploadCard");
				}catch(Exception ex) {
					logger.error(ex.getMessage());
				}
			}

			String licenseUrl = null;
			if(licenseFile != null) {
				try {
					licenseUrl = OSSCore.upload(licenseFile);
					logger.info(">>>>>>>>>>>>>>license asynUploadCard");
				}catch(Exception ex) {
					logger.error(ex.getMessage());
				}
			}

			Shop shop = new Shop();
			shop.setId(shopId);
			shop.setShopLogo(shopLogoUrl);
			shop.setFrontId(idFrontUrl);
			shop.setBackId(idBackUrl);
			shop.setLicense(licenseUrl);
			
			if (shopLogoUrl != null || idFrontUrl != null
					|| idBackUrl != null || licenseUrl != null) {
				shopManage.updateByPrimaryKeySelective(shop);
			}
			logger.info(">>>>>>>>>>>>>>commplete asynUploadCard");
		});
	}

}
