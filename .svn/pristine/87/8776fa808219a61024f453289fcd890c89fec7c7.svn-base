package com.tian.sakura.cdd.db.manage.params;

import com.tian.sakura.cdd.common.entity.AbstractSingleManage;
import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.dao.params.ParamsMapper;
import com.tian.sakura.cdd.db.domain.params.Params;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParamsManage extends AbstractSingleManage<Params, String> {

    @Autowired
    private ParamsMapper paramsMapper;

    @Override
    protected AbstractSingleMapper<Params, String> getSingleMapper() {
        return paramsMapper;
    }

    public String getValueByKey(String key) {
        return paramsMapper.getValueByKey(key);
    }

    public Integer updateValueByKey(String key, String value) {
        return paramsMapper.updateValueByKey(key, value);
    }
}
