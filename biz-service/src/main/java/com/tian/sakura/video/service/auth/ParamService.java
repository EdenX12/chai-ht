package com.tian.sakura.video.service.auth;

import com.tian.sakura.cdd.db.manage.params.ParamsManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParamService {

    @Autowired
    private ParamsManage paramsManage;

    public String getValueByKey(String key) {
        return paramsManage.getValueByKey(key);
    }

    public Integer updateValueByKey(String key, String value) {
        return paramsManage.updateValueByKey(key, value);
    }
}
