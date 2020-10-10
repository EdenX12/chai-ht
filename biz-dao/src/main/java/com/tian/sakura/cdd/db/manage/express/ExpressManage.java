package com.tian.sakura.cdd.db.manage.express;

import com.tian.sakura.cdd.common.entity.AbstractSingleManage;
import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.dao.express.ExpressMapper;
import com.tian.sakura.cdd.db.domain.express.Express;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpressManage extends AbstractSingleManage<Express, String> {

    @Autowired
    private ExpressMapper expressMapper;

    @Override
    protected AbstractSingleMapper<Express, String> getSingleMapper() {
        return expressMapper;
    }

    public List<Express> queryExpressList(Express express) {
        return expressMapper.queryExpressList(express);
    }
}
