package com.tian.sakura.cdd.srv.service.user;

import com.tian.sakura.cdd.order.acct.UserAcctParameter;
import com.tian.sakura.cdd.order.acct.UserAcctService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

/**
 * 说明。
 *
 * @author lvzonggang
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserAcctServiceTest {

    @Autowired
    private UserAcctService userAcctService;

    @Test
    public void changeAmt() {
        UserAcctParameter acctParameter = new UserAcctParameter();

        acctParameter.setUserId("59fbae33b345412a92c3004a72e52649");
        acctParameter.setChangeAmt(new BigDecimal("90"));
        acctParameter.setChangeLockAmt(new BigDecimal("40"));
        acctParameter.setChagngRewardBean(10);
        userAcctService.changeUserAcct(acctParameter);


    }
}
