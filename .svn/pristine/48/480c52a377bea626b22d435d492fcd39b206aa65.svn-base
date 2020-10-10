package com.tian.sakura.cdd.db.dao.user;

import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.domain.user.UserCoupon;
import com.tian.sakura.cdd.db.manage.user.vo.UserCouponQueryVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserCouponMapper extends AbstractSingleMapper<UserCoupon, String> {

    Integer selectCntByUserId(String userId);

    List<UserCoupon> selectShopCouponByQueryVo(UserCouponQueryVo queryVo);

    List<UserCoupon> selectTaskCouponByQueryVo(UserCouponQueryVo queryVo);

    List<UserCoupon> getUserCouponList(@Param("userId") String userId);
}