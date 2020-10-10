package com.tian.sakura.cdd.db.manage.base;

import com.tian.sakura.cdd.db.dao.base.JpushRegMapper;
import com.tian.sakura.cdd.db.domain.base.JpushReg;
import com.tian.sakura.cdd.db.domain.base.JpushRegKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 极光推送注册表的数据访问服务类
 *
 * @author lvzonggang
 */
@Service
@Transactional
public class JpushRegManage {

    @Autowired
    private JpushRegMapper jpushRegMapper;

    public void saveOrUpdate(String deviceId, String registrationId) {
        JpushReg jpushReg = jpushRegMapper.selectByPrimaryKey(new JpushRegKey(deviceId, registrationId));

        if (jpushReg == null) {
            jpushReg = new JpushReg();
            jpushReg.setDeviceId(deviceId);
            jpushReg.setRegistrationId(registrationId);
            jpushReg.setCreateTime(new Date());

            jpushRegMapper.insert(jpushReg);
        }
    }

    public JpushReg selectByDeviceId(String deviceId) {
        List<JpushReg> jpushRegList = jpushRegMapper.selectByDeviceId(deviceId);
        if (jpushRegList != null && jpushRegList.size() > 0) {
            return jpushRegList.get(0);
        }
        return null;
    }
}
