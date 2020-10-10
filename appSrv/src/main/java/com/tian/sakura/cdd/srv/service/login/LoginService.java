package com.tian.sakura.cdd.srv.service.login;

import com.tian.sakura.cdd.common.dict.ERecordStatus;
import com.tian.sakura.cdd.common.dict.ERelationType;
import com.tian.sakura.cdd.common.dict.EUserType;
import com.tian.sakura.cdd.common.dto.LoginDeviceDto;
import com.tian.sakura.cdd.common.exception.BizRuntimeException;
import com.tian.sakura.cdd.common.resp.RespCodeEnum;
import com.tian.sakura.cdd.common.util.BizUtils;
import com.tian.sakura.cdd.common.util.IdGenUtil;
import com.tian.sakura.cdd.common.util.MD5Util;
import com.tian.sakura.cdd.db.domain.user.SUser;
import com.tian.sakura.cdd.db.domain.user.UserLoginLog;
import com.tian.sakura.cdd.db.domain.user.UserRelation;
import com.tian.sakura.cdd.db.domain.user.UserWechat;
import com.tian.sakura.cdd.db.manage.user.SUserManage;
import com.tian.sakura.cdd.db.manage.user.UserLoginLogManage;
import com.tian.sakura.cdd.db.manage.user.UserRelationManage;
import com.tian.sakura.cdd.db.manage.user.UserWechatManage;
import com.tian.sakura.cdd.srv.builder.LoginBuilder;
import com.tian.sakura.cdd.srv.builder.WechatUserBuilder;
import com.tian.sakura.cdd.srv.filter.LoginUserThreadLocal;
import com.tian.sakura.cdd.srv.service.base.SmsCodeService;
import com.tian.sakura.cdd.srv.service.user.InviteCodeGenerator;
import com.tian.sakura.cdd.srv.service.user.UserInfoDecorator;
import com.tian.sakura.cdd.srv.web.login.dto.*;
import com.tian.sakura.cdd.wx.message.WxUser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.swing.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.tian.sakura.cdd.common.constants.SrvConstants.MD5_SALTY_LOGIN;

/**
 * 登录服务类
 *
 * @author lvzonggang
 */
@Service
@Transactional
public class LoginService {

    private transient Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SUserManage userManage;
    @Autowired
    private UserWechatManage wechatManage;
    @Autowired
    private UserRelationManage userRelationManage;
    @Autowired
    private UserLoginLogManage userLoginLogManage;

    @Resource(name="inviteCodeDBGenerator")
    private InviteCodeGenerator inviteCodeGenerator;
    @Autowired
    private UserInfoDecorator userInfoDecorator;
    @Autowired
    private SmsCodeService smsCodeService;
    //@Autowired
    //private CustRedisService custRedisService;

    /**
     * 手机号登录
     *
     * @param loginReq
     * @return
     */
    public LoginRsp loginByPhone(PhoneLoginReq loginReq) {
        String phone = loginReq.getBody().getPhone();
        String smsCode = loginReq.getBody().getSmsCode();
        String digest = loginReq.getBody().getDigest();
        //校验摘要信息 md5("phone + smsCode+ login")
        StringBuilder sb = new StringBuilder().append(phone).append(smsCode).append(MD5_SALTY_LOGIN);
        logger.info("待加密摘要字符：{}", sb.toString());
        String validateDigest = MD5Util.md5Hex(sb.toString());
        logger.info("md5加密后的摘要：{}", validateDigest);
        if (!StringUtils.equals(digest, validateDigest)) {
            throw new BizRuntimeException(RespCodeEnum.DIGEST_EQUAL);
        }
        // 校验短信验证码
        //smsCodeService.validateSmsCode(loginReq.getBody());

        //校验手机号是否注册
        SUser dbAccount = userManage.getUserByPhone(phone);
        if (dbAccount == null) {
            // 校验邀请码  根据邀请码查找分享人
            String parentId = checkInviteCode(loginReq.getBody().getInviteCode());
            //doSaveLoginiIntoRedis(loginReq);
            doSavenLoginIntoDb(loginReq, parentId);
            dbAccount = userManage.getUserByPhone(phone);
        } else {
            logger.info("登录流程-手机号[{}]为注册用户，执行登录流程", phone);
        }

        LoginDeviceDto loginDeviceDto = LoginDeviceDto.builder()
                .deviceId(loginReq.getBody().getDeviceId())
                .ip(LoginUserThreadLocal.getIp())
                .deviceInfo("")
                .build();

        UserLoginLog userLoginLog = LoginBuilder.instance().buildLoginLog(dbAccount, loginDeviceDto);
        userLoginLogManage.insert(userLoginLog);
        logger.info("用户[]在设备[]上登录成功", dbAccount.getId(), loginReq.getBody().getDeviceId());

        LoginRsp loginRsp = LoginBuilder.instance().buildLoginRsp(dbAccount);
        userInfoDecorator.decorate(dbAccount, loginRsp);

        return loginRsp;
    }

    private void doSaveLoginiIntoRedis(PhoneLoginReq loginReq) {
        String key = new StringBuilder("login").append(loginReq.getBody().getPhone())
                .append(loginReq.getBody().getSmsCode()).toString();
        //custRedisService.set(key,"1",3600);
    }

    private String checkInviteCode(String inviteCode) {
        String parentId = "";
        if (StringUtils.isNotEmpty(inviteCode)) {
            SUser shareUser = userManage.getUserByInviteCode(inviteCode);
            if (shareUser == null) {
                throw new BizRuntimeException(RespCodeEnum.CHECK_INVITE_CODE_FAIL);
            }
            parentId = shareUser.getId();
            logger.info("邀请码[]{}对应的分享者[userId={}]",inviteCode, parentId);

        }
        return parentId;
    }

    private void doSavenLoginIntoDb(PhoneLoginReq loginReq, String parentId) {
        logger.info("手机号[{}]为新用户，执行注册流程", loginReq.getBody().getPhone());
        // 转化成user对象, 插入数据
        SUser loginAccount = LoginBuilder.instance().build(loginReq.getBody());
        loginAccount.setParentId(parentId);
        loginAccount.setId(IdGenUtil.uuid());
        //生成邀请码
        loginAccount.setInviteCode(inviteCodeGenerator.next());
        //持久化数据
        userManage.insert(loginAccount);
        logger.info("持久化手机号[{}]的注册用户", loginReq.getBody().getPhone());

        //更新该用户的上级 user_id parent_id 查询
        if (StringUtils.isNotEmpty(parentId)) {
            UserRelation userRelation = new UserRelation();
            userRelation.setId(IdGenUtil.uuid());
            userRelation.setUserId(loginAccount.getId());
            userRelation.setParentId(parentId);
            userRelation.setRelationType(ERelationType.GUARD.getCode());
            userRelationManage.insert(userRelation);
            logger.info("持久化手机号[{}]的注册用户的上级关系数据", loginReq.getBody().getPhone());
        }
    }

    public LoginRsp loginByWx(WxLoginReq loginReq, WxUser wxUser) {
        String openid = wxUser.getOpenid();
        String unionid = wxUser.getUnionid();
        logger.info("微信登录获取的openid[{}],unionid[{}]", openid, unionid);

        String parentId = "";
        String inviteCode = "";
        List<UserWechat> userWechatList = wechatManage.selectByUnionid(unionid);

        //是否被邀请过，比如微信分享邀请等
        boolean invited = false;
        for (UserWechat userWechat : userWechatList) {
            if (StringUtils.isNotEmpty(userWechat.getParentId())) {
                parentId = userWechat.getParentId();
                inviteCode = userWechat.getInviteCode();
                invited = true;
                break;
            }
        }
        if (invited) {
            logger.info("微信用户opnid=[{}]之前被用户[userid-{}]邀请过", openid, parentId);
        } else {
            try {
                inviteCode = loginReq.getBody().getInviteCode();
                parentId = checkInviteCode(inviteCode);
            } catch (BizRuntimeException e) {
                // 由于是微信登录，过滤掉无效邀请码异常，正常登录
            }
        }

        // 判断 wechat 表中是否存在记录 如果不不存在，插入
        UserWechat dbWechat = wechatManage.selectByOpenidAndUnionid(openid, unionid);
        if (dbWechat == null) {
            dbWechat = WechatUserBuilder.toWechat(wxUser);
            dbWechat.setChannel(loginReq.getHead().getChannel());
            dbWechat.setParentId(parentId);
            dbWechat.setInviteCode(inviteCode);
            dbWechat.setId(IdGenUtil.uuid());
            wechatManage.insert(dbWechat);
        }
        // 根据邀请码 插入 s_user_relation
        if (!invited) {
            UserRelation userRelation = new UserRelation();
            userRelation.setId(IdGenUtil.uuid());
            userRelation.setParentId(parentId);
            userRelation.setRelationType(ERelationType.RESERVE_TEAM.getCode());
            userRelationManage.insert(userRelation);
        }


        //如果该微信已经注册过, 进一步判断手机号是否绑定
        SUser dbAccount = userManage.getUserByUnionId(unionid);
        if (dbAccount == null) {
            return LoginBuilder.instance().buildLoginRsp(dbWechat);
        } else {
            LoginRsp loginRsp = LoginBuilder.instance().buildLoginRsp(dbAccount);
            userInfoDecorator.decorate(dbAccount, loginRsp);
            return loginRsp;
        }
    }

    /**
     * 绑定手机
     * <p> 如果手机号已注册，更新该条记录即可</p>
     * <p> 如果手机号未注册，插入用户表s_user;  </p>
     * 如果存在邀请码， 根据邀请码获取上级userId， 更新或插入 s_user_relation
     * @param req
     * @return
     */
    public LoginRsp bindPhone(PhoneBindWeChatReq req) {
        PhoneBindWeChatReqBody body = req.getBody();
        String openid = body.getOpenId();
        String unionid = body.getUnionId();
        String digest = body.getDigest();
        //校验摘要信息 md5("phone + smsCode+ login")
        StringBuilder sb = new StringBuilder().append(body.getPhone()).append(body.getSmsCode()).append(MD5_SALTY_LOGIN);
        String validateDigest = MD5Util.md5Hex(sb.toString());
        if (!StringUtils.equals(digest, validateDigest)) {
            throw new BizRuntimeException(RespCodeEnum.DIGEST_EQUAL);
        }
        //校验微信用户是否存在
        UserWechat dbWechat = wechatManage.selectByOpenidAndUnionid(openid, unionid);
        if (dbWechat == null) {
            throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE,"用户微信");
        }

        // 校验短信验证码
        //smsCodeService.validateSmsCode(body);
        //微信绑定手机号
        //校验手机号是否注册
        SUser dbUser = userManage.getUserByPhone(body.getPhone());
        if (dbUser != null) {
            //   更新用户 unioid
            SUser user = new SUser();
            user.setUnionId(unionid);
            user.setId(dbUser.getId());
            userManage.updateByPrimaryKeySelective(user);
        } else {
            dbUser = new SUser();
            dbUser.setUserName(body.getPhone());
            dbUser.setUserPhone(body.getPhone());
            dbUser.setId(IdGenUtil.uuid());
            dbUser.setUnionId(unionid);
            dbUser.setChannel(req.getHead().getChannel());
            dbUser.setUserStatus(Integer.valueOf(ERecordStatus.VALID.getCode()));
            dbUser.setUserType(EUserType.NORMAL_CUST.getCode());
            dbUser.setInviteCode(inviteCodeGenerator.next());
            dbUser.setNickName(dbWechat.getNickName());
            dbUser.setUserImg(dbWechat.getUserImg());
            dbUser.setAppOpenId(dbWechat.getOpenId());
            dbUser.setLastLogin(new Date());
            //初始化账号
            dbUser.setRewardBean(0);
            dbUser.setLockAmount(BigDecimal.ZERO);
            dbUser.setTotalAmount(BigDecimal.ZERO);
            dbUser.setCanuseBean(0);
            dbUser.setTaskCount(0);
            userManage.insert(dbUser);
        }
        dbUser.setUserPhone(body.getPhone());
        LoginRsp loginRsp = LoginBuilder.instance().buildLoginRsp(dbUser);
        userInfoDecorator.decorate(dbUser, loginRsp);
        return loginRsp;
    }

    @Deprecated
    public LoginRsp bindWeChat(BindWeChatReq req, WxUser wxUser) {
        String key = new StringBuilder("login").append(req.getBody().getPhone())
                .append(req.getBody().getSmsCode()).toString();
//        if (!custRedisService.exists(key)) {
//            throw new BizRuntimeException("1001", "请先使用手机号登录系统");
//        }

        //校验手机号是否注册
        SUser dbUser = userManage.getUserByPhone(req.getBody().getPhone());
        if (dbUser != null) {
            //检查微信unionid是否存在
            if (StringUtils.isEmpty(dbUser.getUnionId())) {
                logger.warn("该手机号[{}]已经绑定过微信[unionid-{}]", req.getBody().getPhone(), wxUser.getUnionid());
            } else {
                SUser user = new SUser();
                user.setAppOpenId(wxUser.getOpenid());
                user.setUnionId(wxUser.getUnionid());
                user.setId(dbUser.getId());
                userManage.updateByPrimaryKeySelective(user);
            }
        } else {
            SUser user = LoginBuilder.instance().build(req.getBody());
            user.setAppOpenId(wxUser.getOpenid());
            user.setUnionId(wxUser.getUnionid());
            user.setId(IdGenUtil.uuid());

            userManage.insert(user);
        }


        dbUser.setUnionId(wxUser.getUnionid());
        dbUser.setAppOpenId(wxUser.getOpenid());
        LoginRsp loginRsp = LoginBuilder.instance().buildLoginRsp(dbUser);
        loginRsp.setCheckPhone(true);
        return loginRsp;
    }

    public Object phoneVerify(PhoneVerifyReq phoneVerifyReq) {
        Map<String, Object> map = new HashMap<>();
        map.put("mobile", phoneVerifyReq.getBody().getPhone());
        SUser user = userManage.getUserByPhone(phoneVerifyReq.getBody().getPhone());
        if (user == null) {
            map.put("boolean", false);
        } else {
            map.put("boolean", true);
        }
        return map;
    }
}
