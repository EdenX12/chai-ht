package com.tian.sakura.video.service.auth;

import com.tian.sakura.cdd.common.exception.BizRuntimeException;
import com.tian.sakura.cdd.common.req.log.UserAmountLogReq;
import com.tian.sakura.cdd.common.resp.RespCodeEnum;
import com.tian.sakura.cdd.common.resp.log.UserAmountLogResp;
import com.tian.sakura.cdd.db.domain.log.UserAmountLog;
import com.tian.sakura.cdd.db.manage.log.UserAmountLogManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserAmountLogService {

    @Autowired
    private UserAmountLogManage userAmountLogManage;

    public List<UserAmountLogResp> getUserAmountLogByNum(UserAmountLogReq userAmountLogReq) {
        if (userAmountLogReq == null || userAmountLogReq.getBody() == null || userAmountLogReq.getBody().getQryNumber() == null) {
            throw new BizRuntimeException(RespCodeEnum.PARAM_DEFECT);
        }
        List<UserAmountLog> userAmountLogs = userAmountLogManage.getUserAmountLogByNum(userAmountLogReq.getBody().getQryNumber());
        List<UserAmountLogResp> respList = new ArrayList<>();
        userAmountLogs.forEach(a -> {
            UserAmountLogResp resp = new UserAmountLogResp();
            if (a.getChangeType() == 3) {
                //独赢
                resp.setAmountType(1);
            } else if (a.getChangeType() == 4) {
                //躺赢
                resp.setAmountType(2);
            }
            resp.setAmount(a.getChangeAmount());
            resp.setUserName(a.getNickName());
            respList.add(resp);
        });
        return respList;
    }
}
