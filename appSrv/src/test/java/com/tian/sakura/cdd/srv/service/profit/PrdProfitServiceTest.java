package com.tian.sakura.cdd.srv.service.profit;

import com.tian.sakura.cdd.order.profit.ProfitCalculateService;
import com.tian.sakura.cdd.order.profit.RewardSettelParam;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 说明。
 *
 * @author lvzonggang
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class PrdProfitServiceTest {

    @Autowired
    private ProfitCalculateService profitCalculateService;

    @Test
    public void frozeReward() {
        RewardSettelParam settelParam = new RewardSettelParam();
        settelParam.setBuyerId("83437210ed71491b855d4e64968cb6f0");
        settelParam.setOrderDetailId("9e6335bb47f242e499d30c2c99cb074e");
        settelParam.setPrdCount(1);
        settelParam.setProductId("3ecf9e7b3c8046e7921e86d4a02b1c4a");
        profitCalculateService.frozeReward(settelParam);
    }
}
