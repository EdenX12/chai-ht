package com.tian.sakura.video.service.auth;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tian.sakura.cdd.common.dto.ResultDto;
import com.tian.sakura.cdd.common.req.login.PhoneLoginReq;
import com.tian.sakura.cdd.common.req.user.AdminUserLevel;
import com.tian.sakura.cdd.common.req.user.AdminUserReq;
import com.tian.sakura.cdd.common.req.user.AdminUserWithdraw;
import com.tian.sakura.cdd.common.resp.coupon.AdminCouponResp;
import com.tian.sakura.cdd.db.dao.sms.SmsCodeMapper;
import com.tian.sakura.cdd.db.dao.user.SUserMapper;
import com.tian.sakura.cdd.db.domain.shop.ShopCoupon;
import com.tian.sakura.cdd.db.domain.task.TaskCoupon;
import com.tian.sakura.cdd.db.domain.user.SUser;
import com.tian.sakura.cdd.db.domain.user.UserCoupon;
import com.tian.sakura.cdd.db.domain.user.UserLevel;
import com.tian.sakura.cdd.db.domain.user.UserWithdraw;
import com.tian.sakura.cdd.db.manage.shop.ShopCouponManage;
import com.tian.sakura.cdd.db.manage.task.TaskCouponManage;
import com.tian.sakura.cdd.db.manage.user.SUserManage;
import com.tian.sakura.cdd.db.manage.user.UserCouponManage;
import com.tian.sakura.cdd.db.manage.user.UserLevelManage;
import com.tian.sakura.cdd.db.manage.user.UserWithdrawManage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private SUserMapper sUserMapper;
    @Autowired
    private SmsCodeMapper smsCodeMapper;
    @Autowired
    private SUserManage sUserManage;
    @Autowired
    private UserLevelManage userLevelManage;
    @Autowired
    private UserCouponManage userCouponManage;
    @Autowired
    private ShopCouponManage shopCouponManage;
    @Autowired
    private TaskCouponManage taskCouponManage;
    @Autowired
    private UserWithdrawManage userWithdrawManage;

    public Object phoneVerify(PhoneLoginReq phoneLoginReq) {
        Map<String, Object> map = new HashMap<>();
        map.put("mobile", phoneLoginReq.getBody().getMobile());
        SUser user = sUserMapper.getUserByPhone(phoneLoginReq.getBody().getMobile());
        if (user == null) {
            map.put("boolean", false);
        } else {
            map.put("boolean", true);
        }
        return map;
    }


    public PageInfo<SUser> listUser(AdminUserReq adminUserReq) {
        PageHelper.startPage(adminUserReq.getPageNum(), adminUserReq.getPageSize());
        return new PageInfo<>(sUserManage.listUser(adminUserReq));
    }

    public List<UserLevel> listUserLevel(UserLevel userLevel) {
        return userLevelManage.getUserLevelList(userLevel);
    }

    public void updateUser(SUser user) {
        sUserManage.updateByPrimaryKeySelective(user);
    }

    public void insertUser(SUser user) {
        sUserManage.insertSelective(user);
    }

    public PageInfo<UserLevel> listUserLevelPage(AdminUserLevel userLevel) {
        PageHelper.startPage(userLevel.getPageNum(), userLevel.getPageSize());
        UserLevel userLevel1 = new UserLevel();
        BeanUtils.copyProperties(userLevel, userLevel1);
        return new PageInfo<>(userLevelManage.getUserLevelList(userLevel1));
    }

    public void insertUserLevel(UserLevel userLevel) {
        userLevelManage.insertSelective(userLevel);
    }

    public void updateUserLevel(UserLevel userLevel) {
        userLevelManage.updateByPrimaryKeySelective(userLevel);
    }

    public void deleteUserLevel(UserLevel userLevel) {
        userLevelManage.deleteByPrimaryKey(userLevel.getId());
    }

    public List<UserCoupon> listUserCoupon(UserCoupon userCoupon) {
        return userCouponManage.getUserCouponList(userCoupon.getUserId());
    }

    public ResultDto getUserCouponDetail(UserCoupon userCoupon) {
        AdminCouponResp adminCouponResp = new AdminCouponResp();
        if (userCoupon.getCouponType() == 0) {
            TaskCoupon taskCoupon = taskCouponManage.selectByPrimary(userCoupon.getCouponId());
            if (taskCoupon == null) {
                return ResultDto.error("对象不存在");
            }
            BeanUtils.copyProperties(taskCoupon, adminCouponResp);
        } else if (userCoupon.getCouponType() == 1) {
            ShopCoupon shopCoupon = shopCouponManage.selectByPrimary(userCoupon.getCouponId());
            if (shopCoupon == null) {
                return ResultDto.error("对象不存在");
            }
            BeanUtils.copyProperties(shopCoupon, adminCouponResp);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        adminCouponResp.setStartDateFormat(simpleDateFormat.format(adminCouponResp.getStartDate()));
        adminCouponResp.setEndDateFormat(simpleDateFormat.format(adminCouponResp.getEndDate()));
        return ResultDto.success().setObj(adminCouponResp);
    }

    public Map<String, Object> listUserWithdraw(AdminUserWithdraw adminUserWithdraw) {
        PageHelper.startPage(adminUserWithdraw.getPageNum(), adminUserWithdraw.getPageSize());
        List<UserWithdraw> list = userWithdrawManage.queryUserWithdraw(adminUserWithdraw);
        BigDecimal sumAmount = userWithdrawManage.sumUserWithdraw(adminUserWithdraw);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("data", new PageInfo<>(list));
        resultMap.put("sumAmount", sumAmount);
        return resultMap;
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public ResultDto updateUserWithdrawStatus(UserWithdraw userWithdraw) {
        if (StringUtils.isEmpty(userWithdraw.getId()) || StringUtils.isEmpty(userWithdraw.getStatus())) {
            return ResultDto.error("必填参数不能为空");
        }
        UserWithdraw uw = userWithdrawManage.selectByPrimary(userWithdraw.getId());
        switch (userWithdraw.getStatus()) {
            case "1":
                //TODO 审核通过
                userWithdraw.setStatus("3");
                break;
            case "2":
                //TODO 审核拒绝
                sUserManage.updateTotalAmountById(uw.getUserId(), uw.getAmount());
                break;
            case "4":
                //TODO 打款成功
                break;
            case "5":
                //TODO 打款失败
                sUserManage.updateTotalAmountById(uw.getUserId(), uw.getAmount());
                break;
        }
        userWithdrawManage.updateUserWithdrawStatus(userWithdraw);
        return ResultDto.success();
    }
}
