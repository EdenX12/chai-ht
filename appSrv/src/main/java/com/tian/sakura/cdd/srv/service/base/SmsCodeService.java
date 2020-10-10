package com.tian.sakura.cdd.srv.service.base;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.tian.sakura.cdd.common.dict.EUsedStatus;
import com.tian.sakura.cdd.common.exception.BizRuntimeException;
import com.tian.sakura.cdd.common.resp.RespCodeEnum;
import com.tian.sakura.cdd.common.util.IdGenUtil;
import com.tian.sakura.cdd.common.util.MD5Util;
import com.tian.sakura.cdd.db.domain.sms.SmsCode;
import com.tian.sakura.cdd.db.manage.base.SmsCodeManage;
import com.tian.sakura.cdd.srv.web.base.dto.SmsCodeApplyReq;
import com.tian.sakura.cdd.srv.web.login.dto.PhoneReqBody;
import com.tian.sakura.video.service.core.SendSmsCore;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import static com.tian.sakura.cdd.common.constants.SrvConstants.MD5_SALTY;
import static com.tian.sakura.cdd.common.constants.SrvConstants.SMS_EXPIRY;

/**
 * 短信验证码服务
 *
 * @author lvzonggang
 */
@Service
@Transactional
public class SmsCodeService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SmsCodeManage smsCodeManage;
    @Autowired
    private SendSmsCore sendSmsCore;

    @Value("${trade.switch.sms}")
    private boolean smsSwitch;

    public String obtainSmsCode(SmsCodeApplyReq smsCodeApplyReq) {
        String mobile = smsCodeApplyReq.getBody().getPhone();
        //验证摘要
        String digest = smsCodeApplyReq.getBody().getDigest();
        String digestApp = MD5Util.md5Hex(mobile.concat(MD5_SALTY));
        if (!StringUtils.equals(digest, digestApp)) {
            throw new BizRuntimeException(RespCodeEnum.DIGEST_EQUAL);
        }
        String vertifyCode = generatorSecurityVertifyCode(6);
        //
        boolean result = false;
        if (!smsSwitch) {
            SendSmsResponse sendSmsResponse = sendSmsCore.sendSms(mobile, vertifyCode);
            if (sendSmsResponse == null) {
                throw new BizRuntimeException(RespCodeEnum.SMS_SNED_FAIL, "未知错误");
            }
            //判断发送成功-返回OK代表请求成功
            if (StringUtils.equalsIgnoreCase(sendSmsResponse.getCode(), "ok")) {
                result = true;
            } else {
                throw new BizRuntimeException(RespCodeEnum.SMS_SNED_FAIL, sendSmsResponse.getMessage());
            }
        } else {
            result = true;
        }

        if (result) {
            SmsCode smsCode = new SmsCode();
            smsCode.setId(IdGenUtil.uuid());
            smsCode.setMobile(mobile);
            smsCode.setSmsCode(vertifyCode);
            smsCode.setUseStatus(EUsedStatus.UNUSE.getCode());
            smsCode.setExpiryDate(SMS_EXPIRY);
            smsCodeManage.insert(smsCode);
        }
        return vertifyCode;
    }

    public void validateSmsCode(PhoneReqBody body) {
        String mobile = body.getPhone();
        String smsCode = body.getSmsCode();

        //业务规则校验
        SmsCode dbSmsVerify = smsCodeManage.selectByMobileAndSmsCode(mobile, smsCode);
        if (dbSmsVerify == null) {
            throw new BizRuntimeException(RespCodeEnum.SMS_VALID_FAIL);
        }
        if (!StringUtils.equals(EUsedStatus.UNUSE.getCode(), dbSmsVerify.getUseStatus())) {
            throw new BizRuntimeException(RespCodeEnum.SMS_VALID_FAIL);
        }
        //业务规则，超过5分钟为无效短信码
        Integer expiryDate = dbSmsVerify.getExpiryDate();
        logger.info("手机号[{}]设置的时间码的时间差-{}秒", mobile, expiryDate);
        Long now = System.currentTimeMillis() / 1000;
        Long createTime = dbSmsVerify.getCreateTime().getTime() / 1000;
        logger.info("手机号[{}]验证时间码的时间差-{}", mobile, now - createTime);
        if (now - createTime > expiryDate) {
            throw new BizRuntimeException(RespCodeEnum.SMS_VALID_TIMEOUT);
        }

        SmsCode smsCodeObj = new SmsCode();
        smsCodeObj.setId(dbSmsVerify.getId());
        smsCodeObj.setUseStatus(EUsedStatus.USED.getCode());
        smsCodeManage.updateByPrimaryKeySelective(smsCodeObj);
    }

    private static String generatorSecurityVertifyCode(int length) {
        StringBuilder sb = new StringBuilder();
        SecureRandom random = null;
        try {
            random = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
        }
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}
