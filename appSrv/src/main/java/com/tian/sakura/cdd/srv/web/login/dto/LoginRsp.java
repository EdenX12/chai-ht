package com.tian.sakura.cdd.srv.web.login.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 登录返回信息
 *
 * @author lvzonggang
 */
@Setter
@Getter
@ApiModel
public class LoginRsp {
    @ApiModelProperty("手机")
    private String phone;
    @ApiModelProperty("头像")
    private String userImg;
    private String userNo;
    @ApiModelProperty("账号，目前使用手机号作为登录账号")
    private String userName;
    @ApiModelProperty("昵称")
    private String nickName;
    @ApiModelProperty("用户类型-普通客户，商家")
    private Integer userType;
    @ApiModelProperty("访问令牌")
    private String token;
    @ApiModelProperty("是否绑定了手机")
    private Boolean checkPhone;
    @ApiModelProperty("app应用的微信openid")
    private String appOpenId;
    @ApiModelProperty("微信unionid")
    private String unionId;
    @ApiModelProperty("小程序openid")
    private String openId;
    @ApiModelProperty("本人邀请码")
    private String inviteCode;
    @ApiModelProperty("上级的邀请码")
    private String parentInviteCode;
    @ApiModelProperty("猎豆")
    private Integer rewardBean;
    @ApiModelProperty("任务限额-上限")
    private Integer taskLimit;
    @ApiModelProperty("任务限额- 已领取的任务数")
    private Integer receiveTask;
    @ApiModelProperty("可用余额")
    private BigDecimal totalAmount;
    @ApiModelProperty("结算中")
    private BigDecimal lockAmount;
    @ApiModelProperty("可用券数量")
    private Integer couponCount;

    /************** 订单统计   ****/
    @ApiModelProperty("待付款总数")
    private Integer cntOfToBePay;
    @ApiModelProperty("待发货总数")
    private Integer cntOfToBeSend;
    @ApiModelProperty("待收货总数")
    private Integer cntOfToBeReceive;
    @ApiModelProperty("退换货")
    private Integer cntOfBack;

    @ApiModelProperty("会员级别")
    private String levelName;

    /////////// 战队模块
    @ApiModelProperty("我的战队-累计人数")
    private Integer teamPersonCnt;
    @ApiModelProperty("我的战队-今日新增")
    private Integer teamPersonCntOfToday;
    @ApiModelProperty("我的战队-累计收益")
    private BigDecimal teamTotalReward;
    @ApiModelProperty("我的战队-今日收益")
    private BigDecimal teamRewardOfToday;

}
