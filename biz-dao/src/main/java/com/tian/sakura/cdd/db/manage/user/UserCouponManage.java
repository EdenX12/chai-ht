package com.tian.sakura.cdd.db.manage.user;

import com.tian.sakura.cdd.common.dict.ECouponType;
import com.tian.sakura.cdd.common.entity.AbstractSingleManage;
import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.common.exception.BizRuntimeException;
import com.tian.sakura.cdd.db.dao.user.UserCouponMapper;
import com.tian.sakura.cdd.db.domain.user.UserCoupon;
import com.tian.sakura.cdd.db.manage.user.vo.UserCouponQueryVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 说明。
 *
 * @author lvzonggang
 */
@Service
@Transactional
public class UserCouponManage extends AbstractSingleManage<UserCoupon, String> {

    @Autowired
    private UserCouponMapper userCouponMapper;

    @Override
    protected AbstractSingleMapper<UserCoupon, String> getSingleMapper() {
        return userCouponMapper;
    }

    public Integer selectCntByUserId(String userId) {
        Integer cnt = userCouponMapper.selectCntByUserId(userId);

        return cnt == null ? 0 : cnt;
    }

    public List<UserCoupon> selectByQueryVo(UserCouponQueryVo queryVo) {
        if (queryVo.getCouponType() != null) {
            if (queryVo.getCouponType() == ECouponType.TASK.getCode()) {
                return userCouponMapper.selectTaskCouponByQueryVo(queryVo);
            } else if(queryVo.getCouponType() == ECouponType.SHOP.getCode()) {
                return userCouponMapper.selectShopCouponByQueryVo(queryVo);
            } else {
                return new ArrayList<>();
            }
        } else {
            List<UserCoupon> shopCouponList = userCouponMapper.selectShopCouponByQueryVo(queryVo);
            List<UserCoupon> taskCouponList = userCouponMapper.selectTaskCouponByQueryVo(queryVo);

            shopCouponList.addAll(taskCouponList);
            return shopCouponList;
        }



    }


    public List<UserCoupon> getUserCouponList(String userId) {
        return userCouponMapper.getUserCouponList(userId);
    }
}
