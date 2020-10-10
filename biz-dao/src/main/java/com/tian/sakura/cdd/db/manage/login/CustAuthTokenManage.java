package com.tian.sakura.cdd.db.manage.login;

import com.tian.sakura.cdd.common.entity.AbstractSingleManage;
import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.dao.login.CustAuthTokenMapper;
import com.tian.sakura.cdd.db.domain.login.CustAuthToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 客户授权令牌db访问服务类。
 *
 * @author lvzonggang
 */

@Service
@Transactional
public class CustAuthTokenManage extends AbstractSingleManage<CustAuthToken, String> {

    @Autowired
    private CustAuthTokenMapper custAuthTokenMapper;

    @Override
    protected AbstractSingleMapper<CustAuthToken, String> getSingleMapper() {
        return custAuthTokenMapper;
    }
}
