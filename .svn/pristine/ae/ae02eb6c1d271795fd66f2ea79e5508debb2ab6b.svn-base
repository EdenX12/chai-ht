package com.tian.sakura.cdd.srv.service.user;

import com.tian.sakura.cdd.common.exception.BizRuntimeException;
import com.tian.sakura.cdd.common.resp.RespCodeEnum;
import com.tian.sakura.cdd.common.util.IdGenUtil;
import com.tian.sakura.cdd.db.domain.user.SUser;
import com.tian.sakura.cdd.db.domain.user.UserBank;
import com.tian.sakura.cdd.db.manage.user.UserBankManage;
import com.tian.sakura.cdd.srv.web.user.dto.UserBankBody;
import com.tian.sakura.cdd.srv.web.user.dto.UserBankQueryRspBody;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户银行api
 *
 * @author lvzonggang
 */
@Service
@Transactional
public class UserBankApiService {
    @Autowired
    private UserBankManage userBankManage;

    public UserBankQueryRspBody selectByUserId(SUser user) {
        List<UserBank> userBankList = userBankManage.selectByUserId(user.getId());
        List<UserBankBody> bankBodyList = new ArrayList<>();
        for (UserBank userBank : userBankList) {
            UserBankBody bankBody = new UserBankBody();
            BeanUtils.copyProperties(userBank, bankBody);
            bankBody.setUserBankId(userBank.getId());
            bankBodyList.add(bankBody);
        }
        UserBankQueryRspBody rspBody = new UserBankQueryRspBody();
        rspBody.setUserBankList(bankBodyList);
        return rspBody;
    }

    public void addBank(SUser user, UserBankBody userBankBody) {
        UserBank userBank = new UserBank();
        BeanUtils.copyProperties(userBankBody, userBank);

        userBank.setUserId(user.getId());

        userBank.setId(IdGenUtil.uuid());
        userBank.setCardStatus(0);

        userBankManage.insert(userBank);
    }


    public void logicDelete(SUser user, String id) {
        //数据校验
        UserBank dbUserBank = userBankManage.selectByPrimary(id);
        if (dbUserBank == null) {
            throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE, "用户银行卡");
        }
        if (!StringUtils.equals(user.getId(), dbUserBank.getUserId())) {
            throw new BizRuntimeException(RespCodeEnum.NOT_QUERY_RECORED);
        }

        UserBank userBank = new UserBank();
        userBank.setId(id);
        userBank.setCardStatus(1);
        userBankManage.logicDeleteByPrimaryKey(userBank);
    }
}
