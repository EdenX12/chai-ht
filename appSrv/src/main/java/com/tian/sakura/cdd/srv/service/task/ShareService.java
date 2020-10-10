package com.tian.sakura.cdd.srv.service.task;

import com.tian.sakura.cdd.common.dict.ERelationType;
import com.tian.sakura.cdd.common.dict.EShareStatus;
import com.tian.sakura.cdd.common.entity.User;
import com.tian.sakura.cdd.common.util.IdGenUtil;
import com.tian.sakura.cdd.db.domain.product.Product;
import com.tian.sakura.cdd.db.domain.task.UserShare;
import com.tian.sakura.cdd.db.domain.user.SUser;
import com.tian.sakura.cdd.db.domain.user.UserRelation;
import com.tian.sakura.cdd.db.manage.product.ProductManage;
import com.tian.sakura.cdd.db.manage.task.UserShareManage;
import com.tian.sakura.cdd.db.manage.user.SUserManage;
import com.tian.sakura.cdd.db.manage.user.UserRelationManage;
import com.tian.sakura.cdd.srv.GlobalConstants;
import com.tian.sakura.cdd.srv.builder.ShareBuilder;
import com.tian.sakura.cdd.srv.filter.LoginUserThreadLocal;
import com.tian.sakura.cdd.srv.service.params.ParamsService;
import com.tian.sakura.cdd.srv.service.product.PrdCommissionCalculateResult;
import com.tian.sakura.cdd.srv.service.product.ProductCommissionService;
import com.tian.sakura.cdd.srv.service.product.param.DefaultPrdCommissionParameter;
import com.tian.sakura.cdd.srv.web.product.dto.PrdShareRspBody;
import com.tian.sakura.cdd.srv.web.task.dto.ShareNoticeReq;
import com.tian.sakura.cdd.srv.web.task.dto.SharePrdReq;
import com.tian.sakura.cdd.wx.message.WxUser;
import com.tian.sakura.video.service.core.QRCodeCore;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Transient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

/**
 * 分享业务逻辑
 *
 * @author lvzonggang
 */

@Service
@Transient
public class ShareService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserShareManage userShareManage;
	@Autowired
	private UserRelationManage userRelationManage;
	@Autowired
	private ProductManage productManage;
	@Autowired
	private SUserManage userManage;
	@Autowired
	private ProductCommissionService productCommissionService;
	@Autowired
	private ParamsService paramsService;
	@Autowired
	private QRCodeCore qrCode;


	public String shareProduct(SharePrdReq sharePrdReq) {
		// 检查是登录客户分享，还是游客分享
		SUser loginUser = LoginUserThreadLocal.getLoginUser();
		if (loginUser == null) {
			logger.warn("本次分享[productid={}]为游客分享，暂不持久化分享数据！", sharePrdReq.getBody().getProductId());
			return "";
		}
		String userId = loginUser.getId();
		String taskDetailId = "";
		if ("2".equals(sharePrdReq.getBody().getShareType())) {
			// 查找当前登录者，该产品下的任务明细id

		}
		// 查询是否分享过该产品
        String productId = sharePrdReq.getBody().getProductId();
        UserShare dbShare = userShareManage.selectByUserIdAndProductId(userId, productId);
        if (dbShare != null) {
            return dbShare.getId();
        } else {
            String shareId = IdGenUtil.uuid();
            UserShare userShare = new ShareBuilder().userShareBuilder()
                    .id(shareId).userId(userId)
                    .parentId(sharePrdReq.getBody().getParentShareId())
                    .productId(sharePrdReq.getBody().getProductId())
                    .taskId(taskDetailId)
                    .createTime(new Date())
                    .remark(sharePrdReq.getBody().getShareType())
                    .build();
            userShare.setShareStatus(EShareStatus.TO_BE_SHARE.getCode());
            userShareManage.insert(userShare);
            return shareId;
        }

	}

	public void notice(ShareNoticeReq shareNoticeReq) {
		// 检查是登录客户分享，还是游客分享
		SUser loginUser = LoginUserThreadLocal.getLoginUser();
		if (loginUser == null) {
			logger.warn("本次分享通知[productid={}]为游客分享，暂不持久化分享数据！", shareNoticeReq.getBody().getProductId());
			return;
		}

		String shareId = shareNoticeReq.getBody().getShareId();
		Integer shareStatus = shareNoticeReq.getBody().getShareStatus();
		UserShare dbShare = userShareManage.selectByPrimary(shareId);
		if (dbShare == null) {
			logger.warn("未查询到本次分享记录[id={}]", shareId);
			return;
		}
		if (EShareStatus.TO_BE_SHARE.getCode() == dbShare.getShareStatus()) {
			UserShare userShare = new UserShare();
			userShare.setId(shareId);
			userShare.setShareStatus(shareStatus);
			userShareManage.updateByPrimaryKeySelective(userShare);
		} else {
			logger.warn("分享记录[id={}]当前状态[shareStatus={}]重复通知！！", shareId, dbShare.getShareStatus());
		}

	}

	// 页面点击“立刻分享”按钮
	public PrdShareRspBody shareBtnClick(@RequestBody @Valid SharePrdReq sharePrdReq,String unionId) {
    	PrdShareRspBody rsp = new PrdShareRspBody();

    	Product prd = productManage.selectByPrimary(sharePrdReq.getBody().getProductId());//查询商品
    	SUser user = userManage.getUserByUnionId(unionId);

    	rsp.setNickName(user.getNickName());//昵称
    	rsp.setUserImg(user.getUserImg());//用户头像

		Object obj = paramsService.getValue(GlobalConstants.SHARE_TITLE);
		if (obj != null) {
			rsp.setShareTitle(obj.toString());//分享标题
		} else {
			rsp.setShareTitle("");
		}
    	
    	rsp.setProductPrice(prd.getProductPrice());//商品价格
    	rsp.setProductImg(prd.getProductImg());//商品图片
    	rsp.setProductId(prd.getId());//产品id
    	rsp.setProductDesc(prd.getProductDes());//产品简介
    	
		DefaultPrdCommissionParameter commissionParameter = new DefaultPrdCommissionParameter(prd.getId(),
				prd.getTaskNumber(), prd.getTotalReward());

		PrdCommissionCalculateResult calculateResult = productCommissionService.getProductCommission(commissionParameter);
    	rsp.setSameGroupCommission(calculateResult.getEveryReward());//同组分享的奖金
    	rsp.setBuyerCommission(calculateResult.getSuccessReward());//买家立返
    	rsp.setUserCount(calculateResult.getUserCount());//拆家人数
    	
    	//生成二维码
    	String url = qrCode.encode("https://www.baidu.com");
    	rsp.setBrCodeUrl(url);
    	
    	return rsp;
    }

	/**
	 * 微信用户点击了分享后，记录点击者和分享者的关系-预备队
	 *
	 * @param wxUser
	 * @param shareId
	 */
	public void trackShareRaletion(WxUser wxUser, String shareId) {
		// 根据分享shareId 查询分享记录
		if (StringUtils.isEmpty(shareId)) {
			return;
		}

		UserShare dbUserShare = userShareManage.selectByPrimary(shareId);
		if (dbUserShare == null) {
			logger.warn("分享id[{}]未查询到分享记录，不记录点击者[{}]的上级关系", shareId, wxUser.getOpenid());
			return ;
		}


		// 当前用户是否有上级 - 近卫军
		UserRelation userRelation = userRelationManage.selectGuardParentIdByUnionId(wxUser.getUnionid());
		if (userRelation != null) {
			logger.info("微信用户[{}]已经有上级了，不处理分享者的上下级", wxUser.getUnionid());
			return ;
		}
		userRelation = new UserRelation();
		// 查找点击的是否有上级-近卫军
		List<UserRelation> userRelationList =
				userRelationManage.selectByUnionidAndParentId(wxUser.getUnionid(), dbUserShare.getUserId());
		if (!userRelationList.isEmpty()) {
			userRelation.setId(userRelationList.get(0).getId());
			userRelation.setParentId(dbUserShare.getUserId());
			userRelation.setRelationType(ERelationType.RESERVE_TEAM.getCode());
			userRelation.setUnionId(wxUser.getUnionid());
			userRelationManage.updateByPrimaryKeySelective(userRelation);
		} else {
			userRelation = new UserRelation();
			userRelation.setId(IdGenUtil.uuid());
			userRelation.setParentId(dbUserShare.getUserId());
			userRelation.setRelationType(ERelationType.RESERVE_TEAM.getCode());
			userRelation.setUnionId(wxUser.getUnionid());
			userRelationManage.insert(userRelation);
		}

	}

	public void upgradeRelation(String userId) {
		// 查询当前支付者是否有上级-近卫军
		UserRelation parentRelation = userRelationManage.selectGuardParentIdByUserId(userId);
		if (parentRelation == null) {
			// 查找预备队上级
			parentRelation = userRelationManage.selectOneTeamParentLatelyByUserId(userId);

			if (parentRelation != null) {
				UserRelation updRelation = new UserRelation();
				updRelation.setId(parentRelation.getId());
				updRelation.setRelationType(ERelationType.GUARD.getCode());
				userRelationManage.updateByPrimaryKeySelective(updRelation);
			} else {
				logger.warn("用户[{}]没有上级，同时也不是别人的预备队！", userId);
			}
		} else {
			logger.warn("用户[{}]已经有上级了！", userId);
		}
	}

}
