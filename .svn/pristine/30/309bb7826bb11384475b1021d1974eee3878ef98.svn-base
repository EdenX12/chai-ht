package com.tian.sakura.cdd.db.dao.order;

import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.domain.order.UserShopCar;
import com.tian.sakura.cdd.db.manage.order.vo.UserShopCarQueryVo;

import java.util.List;

public interface UserShopCarMapper extends AbstractSingleMapper<UserShopCar, String> {
    Integer selectCntByUserId(String userId);

    List<UserShopCar> selectByUserId(String userId);

    List<UserShopCar> selectByQueryVo(UserShopCarQueryVo queryVo);
}