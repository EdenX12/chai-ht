package com.tian.sakura.cdd.srv.service.login;

import com.tian.sakura.cdd.common.exception.BizRuntimeException;
import com.tian.sakura.cdd.common.resp.RespCodeEnum;
import com.tian.sakura.cdd.common.util.IdGenUtil;
import com.tian.sakura.cdd.db.domain.user.SUser;
import com.tian.sakura.cdd.db.manage.user.SUserManage;
import com.tian.sakura.cdd.srv.builder.LoginBuilder;
import com.tian.sakura.cdd.srv.service.base.SmsCodeService;
import com.tian.sakura.cdd.srv.web.base.dto.SmsCodeValidReq;
import com.tian.sakura.cdd.srv.web.login.dto.PhoneReqBody;
import com.tian.sakura.cdd.srv.web.login.dto.SmsRegisterReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 注册服务类
 *
 * @author lvzonggang
 */

@Service
@Transactional
public class RegisterService {
    private transient Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SUserManage userManage;
    @Autowired
    private SmsCodeService smsCodeService;

    /**
     * 注册客户-短信验证码注册
     *
     * @param registerReq
     */
    public void registerAccount(SmsRegisterReq registerReq) {
        //业务规则校验 账户唯一性
        SUser dbAccount = userManage.getUserByPhone(registerReq.getBody().getPhone());
        if (dbAccount != null) {
            throw new BizRuntimeException(RespCodeEnum.REGISTER_MOBILE);
        }

        // 验证短信验证码
        smsCodeService.validateSmsCode(registerReq.getBody());
        //转化成loginAccount对象
        SUser loginAccount = LoginBuilder.instance().build(registerReq);
        loginAccount.setId(IdGenUtil.uuid());
        //持久化数据
        userManage.insert(loginAccount);

    }
}
