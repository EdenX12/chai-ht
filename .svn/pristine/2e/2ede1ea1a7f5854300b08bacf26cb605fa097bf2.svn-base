package com.tian.sakura.cdd.db.dao.user;

import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.domain.user.UserAddress;

import java.util.List;


public interface UserAddressMapper extends AbstractSingleMapper<UserAddress, String> {

    List<UserAddress> selectByUserId(String userId);

    int updateAddressNoDefaultByUserId(String userid);
}