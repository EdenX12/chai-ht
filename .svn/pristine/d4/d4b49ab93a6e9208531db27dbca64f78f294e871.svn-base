package com.tian.sakura.cdd.db.manage.login;

import com.tian.sakura.cdd.common.entity.AbstractSingleManage;
import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.dao.login.LoginAccountMapper;
import com.tian.sakura.cdd.db.domain.login.LoginAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 说明。
 *
 * @author lvzonggang
 */
@Service
@Transactional
public class LoginAccountManage extends AbstractSingleManage<LoginAccount, Long> {

    @Autowired
    private LoginAccountMapper loginAccountMapper;

    @Override
    protected AbstractSingleMapper<LoginAccount, Long> getSingleMapper() {
        return loginAccountMapper;
    }

    public LoginAccount findByLoginNo(String loginNo) {
        return loginAccountMapper.findByLoginNo(loginNo);
    }
}
