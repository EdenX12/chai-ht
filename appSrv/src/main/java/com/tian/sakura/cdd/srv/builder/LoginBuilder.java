package com.tian.sakura.cdd.srv.builder;

import com.tian.sakura.cdd.common.dict.EDataChannel;
import com.tian.sakura.cdd.common.dict.EUserLevelType;
import com.tian.sakura.cdd.common.dict.EUserType;
import com.tian.sakura.cdd.common.dto.LoginDeviceDto;
import com.tian.sakura.cdd.common.util.IdGenUtil;
import com.tian.sakura.cdd.common.util.MD5Util;
import com.tian.sakura.cdd.db.domain.user.SUser;
import com.tian.sakura.cdd.db.domain.user.UserLoginLog;
import com.tian.sakura.cdd.db.domain.user.UserWechat;
import com.tian.sakura.cdd.srv.web.login.dto.LoginReq;
import com.tian.sakura.cdd.srv.web.login.dto.LoginRsp;
import com.tian.sakura.cdd.srv.web.login.dto.PhoneReqBody;
import com.tian.sakura.cdd.srv.web.login.dto.SmsRegisterReq;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 登录模块， 注册模块构建类
 *
 * @author lvzonggang
 */
public class LoginBuilder {
    private static LoginBuilder instance;

    private LoginBuilder() {

    }

    public static LoginBuilder instance() {
        if (instance == null) {
            instance = new LoginBuilder();
        }
        return instance;
    }

    public SUser build(SmsRegisterReq registerReq) {
        SUser loginAccount = new SUser();
        loginAccount.setExternalRef(IdGenUtil.generateId());
        //初始化为手机号
        loginAccount.setUserName(registerReq.getBody().getPhone());
        //手机号
        loginAccount.setUserPhone(registerReq.getBody().getPhone());
        //新用户默认为普通用户
        loginAccount.setUserType(EUserType.NORMAL_CUST.getCode());
        return loginAccount;
    }

    public SUser build(PhoneReqBody loginReq) {
        SUser loginAccount = new SUser();
        //初始化为手机号
        loginAccount.setUserName(loginReq.getPhone());
        //手机号
        loginAccount.setUserPhone(loginReq.getPhone());
        //新用户默认为普通用户
        loginAccount.setUserType(EUserType.NORMAL_CUST.getCode());
        //可用-0
        loginAccount.setUserStatus(0);

        //初始化账号
        loginAccount.setRewardBean(0);
        loginAccount.setLockAmount(BigDecimal.ZERO);
        loginAccount.setTotalAmount(BigDecimal.ZERO);
        loginAccount.setCanuseBean(0);
        loginAccount.setTaskCount(0);
        loginAccount.setChannel(EDataChannel.APP.getCode());
        loginAccount.setUserLevelType(EUserLevelType.NEW_USER.getId());
        return loginAccount;
    }

    public String buildPwd(String loginNo, String pwd) {
        return MD5Util.md5Hex(loginNo.concat(pwd));
    }

    public LoginRsp buildLoginRsp(SUser user) {
        LoginRsp loginRsp = new LoginRsp();
        loginRsp.setPhone(user.getUserPhone());
        loginRsp.setUserImg(user.getUserImg());
        loginRsp.setUserNo(user.getId());
        loginRsp.setUserName(user.getUserName());
        loginRsp.setNickName(user.getNickName());
        loginRsp.setUserType(user.getUserType());
        loginRsp.setOpenId(user.getOpenId());
        loginRsp.setUnionId(user.getUnionId());
        loginRsp.setAppOpenId(user.getAppOpenId());
        loginRsp.setInviteCode(user.getInviteCode());
        //猎豆
        loginRsp.setRewardBean(user.getRewardBean() != null ? user.getRewardBean() : 0);
        //可用余额
        loginRsp.setTotalAmount(user.getTotalAmount() != null ? user.getTotalAmount() : BigDecimal.ZERO);
        //冻结中
        loginRsp.setLockAmount(user.getLockAmount() != null ? user.getLockAmount() : BigDecimal.ZERO);
        loginRsp.setCheckPhone(StringUtils.isNotEmpty(user.getUserPhone()));
        return loginRsp;
    }

    public UserLoginLog buildLoginLog(SUser user, LoginDeviceDto loginDeviceDto) {
        UserLoginLog loginLog = new UserLoginLog();
        loginLog.setUserId(user.getId());
        loginLog.setId(IdGenUtil.uuid());
        loginLog.setUnionId(user.getUnionId());
        loginLog.setLoginDeviceId(loginDeviceDto.getDeviceId());
        loginLog.setLoginIp(loginDeviceDto.getIp());
        loginLog.setLoginTime(new Date());
        loginLog.setRemark(loginDeviceDto.getDeviceInfo());

        return loginLog;
    }

    public LoginRsp buildLoginRsp(UserWechat wechat) {
        LoginRsp loginRsp = new LoginRsp();
        // loginRsp.setOpenId(wechat.getOpenId());
        loginRsp.setAppOpenId(wechat.getOpenId());
        loginRsp.setUnionId(wechat.getUnionId());
        loginRsp.setInviteCode(wechat.getInviteCode());
        loginRsp.setCheckPhone(false);
        return loginRsp;
    }

    /**
     * 总的可并行任务数（产品*产品任务线）
     *
     * @param beanOfHolder
     * @param receiveTask
     * @return
     */
    public int getTaskLimit(int beanOfHolder, boolean receiveTask) {
        //新用户进入平台后，只能在新手区领任务
        if (!receiveTask) {
            return 1 * 5;
        }
        if (beanOfHolder < 50) {
            return 1 * 5;
        } else if (beanOfHolder >= 50 && beanOfHolder <= 150) {
            return 3 * 10;
        } else if (beanOfHolder >= 150 && beanOfHolder < 500) {
            return 10 * 30;
        } else if (beanOfHolder >= 500 && beanOfHolder < 1000) {
            return 20 * 50;
        } else {
            return 30 * 100;
        }
    }

    /**
     * 可以并行的产品数
     *
     * @param beanOfHolder
     * @param receiveTask
     * @return
     */
    public int getTaskLimitProduct(int beanOfHolder, boolean receiveTask) {
        //新用户进入平台后，只能在新手区领任务
        if (!receiveTask) {
            return 1;
        }
        if (beanOfHolder < 50) {
            return 1;
        } else if (beanOfHolder >= 50 && beanOfHolder <= 150) {
            return 3;
        } else if (beanOfHolder >= 150 && beanOfHolder < 500) {
            return 10;
        } else if (beanOfHolder >= 500 && beanOfHolder < 1000) {
            return 20;
        } else {
            return 30;
        }
    }

    /**
     * 每个产品可以并行的产品线数
     *
     * @param beanOfHolder
     * @param receiveTask
     * @return
     */
    public int getTaskLimitPerProduct(int beanOfHolder, boolean receiveTask) {
        //新用户进入平台后，只能在新手区领任务
        if (!receiveTask) {
            return 5;
        }
        if (beanOfHolder < 50) {
            return 5;
        } else if (beanOfHolder >= 50 && beanOfHolder <= 150) {
            return 10;
        } else if (beanOfHolder >= 150 && beanOfHolder < 500) {
            return 30;
        } else if (beanOfHolder >= 500 && beanOfHolder < 1000) {
            return 50;
        } else {
            return 100;
        }
    }

    public String getLevelName(int beanOfHolder, boolean receiveTask) {
        //新用户进入平台后，只能在新手区领任务
        if (!receiveTask) {
            return "新手";
        }
        if (beanOfHolder < 50) {
            return "新手";
        } else if (beanOfHolder >= 50 && beanOfHolder <= 150) {
            return "见习拆家";
        } else if (beanOfHolder >= 150 && beanOfHolder < 500) {
            return "初级拆家";
        } else if (beanOfHolder >= 500 && beanOfHolder < 1000) {
            return "中级拆家";
        } else {
            return "高级拆家";
        }
    }
}
