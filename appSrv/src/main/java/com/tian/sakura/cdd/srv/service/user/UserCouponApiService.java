package com.tian.sakura.cdd.srv.service.user;

import com.tian.sakura.cdd.db.domain.user.UserCoupon;
import com.tian.sakura.cdd.db.manage.user.UserCouponManage;
import com.tian.sakura.cdd.db.manage.user.vo.UserCouponQueryVo;
import com.tian.sakura.cdd.srv.web.user.dto.UserCouponQueryReq;
import com.tian.sakura.cdd.srv.web.user.dto.UserCouponRsqBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户券
 *
 * @author lvzonggang
 */
@Service
@Transactional
public class UserCouponApiService {

    private transient Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserCouponManage userCouponManage;

    public List<UserCouponRsqBody> list(String userId, UserCouponQueryReq queryReq) {

        UserCouponQueryVo queryVo = UserCouponQueryVo.builder()
                .userId(userId)
                .couponStatus(queryReq.getBody().getCouponStatus())
                .couponType(queryReq.getBody().getCouponType())
                .productId(queryReq.getBody().getProductId())
                .build();
        List<UserCoupon> userCouponList = userCouponManage.selectByQueryVo(queryVo);
        List<UserCouponRsqBody> bodyList = new ArrayList<>();
        //转化为前端开放的响应体，只暴露可见的属性
        userCouponList.forEach(item -> {
            UserCouponRsqBody body = new UserCouponRsqBody();
            BeanUtils.copyProperties(item.getShopCoupon(), body);
            BeanUtils.copyProperties(item, body);
            body.setId(item.getId());
            bodyList.add(body);
        });
        return bodyList;
    }
}
