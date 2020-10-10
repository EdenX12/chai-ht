package com.tian.sakura.cdd.db.dao.base;

import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.domain.base.SmsVerify;
import org.apache.ibatis.annotations.Param;

public interface SmsVerifyMapper extends AbstractSingleMapper<SmsVerify, String> {

    SmsVerify selectByMobileAndSmsCode(@Param("mobile") String mobile, @Param("smsCode") String smsCode);
}