package com.tian.sakura.cdd.db.manage.order;

import com.tian.sakura.cdd.common.entity.AbstractSingleManage;
import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.dao.order.UserShopCarMapper;
import com.tian.sakura.cdd.db.domain.order.UserShopCar;
import com.tian.sakura.cdd.db.manage.order.vo.UserShopCarQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 购物车数据访问服务
 *
 * @author lvzonggang
 */

@Service
@Transactional
public class UserShopCarManage extends AbstractSingleManage<UserShopCar, String> {

    @Autowired
    private UserShopCarMapper userShopCarMapper;
    @Override
    protected AbstractSingleMapper<UserShopCar, String> getSingleMapper() {
        return userShopCarMapper;
    }

    public UserShopCar selectByLogicId(String userId, String productId, String productSpecId) {
        UserShopCarQueryVo queryVo = UserShopCarQueryVo.builder()
                .productId(productId)
                .productSpecId(productSpecId)
                .userId(userId)
                .build();

        List<UserShopCar> shopCarList = userShopCarMapper.selectByQueryVo(queryVo);

        return shopCarList.isEmpty() ? null : shopCarList.get(0);
    }

    public List<UserShopCar> selectByUserId(String userId) {
        return userShopCarMapper.selectByUserId(userId);
    }

    public Integer selectCntByUserId(String userId) {
        Integer count = userShopCarMapper.selectCntByUserId(userId);
        return count != null ? count : 0;
    }
}
