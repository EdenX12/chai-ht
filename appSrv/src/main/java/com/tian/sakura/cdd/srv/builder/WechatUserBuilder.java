package com.tian.sakura.cdd.srv.builder;

import com.tian.sakura.cdd.db.domain.user.UserWechat;
import com.tian.sakura.cdd.wx.message.WxUser;

import java.util.Date;

/**
 * 微信用户构建器
 *
 * @author lvzonggang
 */
public class WechatUserBuilder {

    public static UserWechat toWechat(WxUser wxUser) {
        UserWechat wechat = new UserWechat();
        wechat.setOpenId(wxUser.getOpenid());
        wechat.setUnionId(wxUser.getUnionid());
        wechat.setNickName(wxUser.getNickname());
        wechat.setUserImg(wxUser.getHeadimgurl());
        wechat.setCreateTime(new Date());

        return wechat;
    }
}
