package com.tian.sakura.cdd.db.manage.user;

import com.tian.sakura.cdd.common.entity.AbstractSingleManage;
import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.dao.user.UserAddressMapper;
import com.tian.sakura.cdd.db.domain.user.UserAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户地址数据访问
 *
 * @author lvzonggang
 */

@Service
@Transactional
public class UserAddressManage extends AbstractSingleManage<UserAddress, String> {
    public static final String DEFALUT_STATUS = "1";

    @Autowired
    private UserAddressMapper userAddressMapper;

    @Override
    protected AbstractSingleMapper<UserAddress, String> getSingleMapper() {
        return userAddressMapper;
    }

    public List<UserAddress> selectByUserId(String userId) {
        return userAddressMapper.selectByUserId(userId);
    }

    public int updateAddressNoDefaultByUserId(String userId) {
        return userAddressMapper.updateAddressNoDefaultByUserId(userId);
    }
}
