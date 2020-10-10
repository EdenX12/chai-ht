package com.tian.sakura.cdd.db.manage.log;

import com.tian.sakura.cdd.common.entity.AbstractSingleManage;
import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.dao.userBonusLog.UserBonusLogMapper;
import com.tian.sakura.cdd.db.domain.userBonusLog.UserBonusLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 说明。
 *
 * @author lvzonggang
 */

@Service
@Transactional
public class UserBonusLogManage extends AbstractSingleManage<UserBonusLog, String> {

    @Autowired
    private UserBonusLogMapper userBonusLogMapper;

    @Override
    protected AbstractSingleMapper<UserBonusLog, String> getSingleMapper() {
        return userBonusLogMapper;
    }
    
	//躺赢人次
    public Integer selectCntByProductId(String productId) {
    	return userBonusLogMapper.selectCntByProductId(productId);
    }

    public List<UserBonusLog> selectByOrderDetailId(String orderDetailId) {
        return userBonusLogMapper.selectByOrderDetailId(orderDetailId);
    }

    public int updateStatusByDetailIdForSettle(String orderDetailId, int status) {
        return userBonusLogMapper.updateStatusByDetailIdForSettle(orderDetailId, status);
    }
}
