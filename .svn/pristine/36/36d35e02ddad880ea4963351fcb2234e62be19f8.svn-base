package com.tian.sakura.cdd.db.manage.base;

import com.tian.sakura.cdd.common.entity.AbstractSingleManage;
import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.dao.base.SmsVerifyMapper;
import com.tian.sakura.cdd.db.dao.sms.SmsCodeMapper;
import com.tian.sakura.cdd.db.domain.base.SmsVerify;
import com.tian.sakura.cdd.db.domain.sms.SmsCode;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 短信验证码
 *
 * @author lvzonggang
 */
@Service
@Transactional
public class SmsCodeManage extends AbstractSingleManage<SmsCode, String> {

    @Autowired
    private SmsCodeMapper smsCodeMapper;

    @Override
    protected AbstractSingleMapper<SmsCode, String> getSingleMapper() {
        return smsCodeMapper;
    }


    public SmsCode selectByMobileAndSmsCode(String mobile, String smsCode) {
        return smsCodeMapper.selectByMobileAndSmsCode(mobile, smsCode);
    }
}
