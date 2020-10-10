package com.tian.sakura.cdd.common.req.user;

import com.tian.sakura.cdd.common.entity.BasePage;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class AdminUserReq extends BasePage {

    private String userName;

    private String nickName;

    private String userPassword;

    private Date createTime;

    private String userImg;

    private String openId;

    private String userPhone;

    private BigDecimal totalAmount;

    private BigDecimal lockAmount;

    private Integer taskCount;

    private Integer rewardBean;

    private Integer userStatus;

    private Integer userLevelId;

    private Integer canuseBean;

    private String parentId;

    private Integer userType;

    private String externalRef;

    private String unionId;

    private String appOpenId;

    private Date lastLogin;

    private String inviteCode;

    private Integer channel;

    private Integer userLevelType;

    private String userLevelTypeName;
}
