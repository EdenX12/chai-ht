package com.tian.sakura.cdd.db.dao.sms;

import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.domain.sms.SmsCode;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SmsCodeMapper extends AbstractSingleMapper<SmsCode, String> {

    SmsCode getSmsCodeByPhone(@Param("phone") String mobile);

    /**
     * 不建议在数据库中做计算，可以查询出来，应用层做时间差计算
     *
     * @param mobile
     * @return
     */
    SmsCode getSmsCodeByPhoneAndTime(@Param("phone") String mobile);

    SmsCode selectByMobileAndSmsCode(@Param("mobile") String mobile, @Param("smsCode") String smsCode);
}