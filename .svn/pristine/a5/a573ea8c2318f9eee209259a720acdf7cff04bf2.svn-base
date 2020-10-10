package com.tian.sakura.cdd.srv.service.user;

import com.tian.sakura.cdd.common.util.BizUtils;
import com.tian.sakura.cdd.db.manage.user.SUserManage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 *
 * @author lvzonggang
 */

@Service("inviteCodeDBGenerator")
public class InviteCodeDBGenerator implements InviteCodeGenerator{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SUserManage userManage;

    public String next() {
        Integer length = 6;
        String inviteCode = BizUtils.generatorRandomCodeWithoutLowercase(length);
        int i = 1;
        while (userManage.getUserByInviteCode(inviteCode) != null) {
            inviteCode = BizUtils.generatorRandomCodeWithoutLowercase(length);
            i ++;
            if (i == 100) {
                return "";
            }
        }

        return inviteCode;
    }
}
