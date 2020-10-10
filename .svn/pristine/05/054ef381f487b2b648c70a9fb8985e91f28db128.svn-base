package com.tian.sakura.cdd.srv.web.base;

import com.tian.sakura.cdd.db.manage.base.JpushRegManage;
import com.tian.sakura.cdd.srv.web.base.dto.JpushRegReq;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 极光推送注册数据API
 *
 * @author lvzonggang
 */

@Api(value="极光推送注册ID接口", tags = {"极光注册"})
@RestController
@RequestMapping("jpush")
public class JpushRegController {

    @Autowired
    private JpushRegManage jpushRegManage;

    @PostMapping("reg")
    public void reg(@RequestBody @Valid JpushRegReq jpushRegReq) {
        String deviceId = jpushRegReq.getBody().getDeviceId();
        String regId = jpushRegReq.getBody().getRegistrationId();
        jpushRegManage.saveOrUpdate(deviceId, regId);
    }
}
