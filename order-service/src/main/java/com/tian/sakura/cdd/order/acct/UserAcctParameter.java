package com.tian.sakura.cdd.order.acct;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Tolerate;

import java.math.BigDecimal;

/**
 * 说明。
 *
 * @author lvzonggang
 */

@Setter
@Getter
@Builder
public class UserAcctParameter {

    private String userId;
    // 可用金额变化值 正值标识增加， 负值标识减少
    private BigDecimal changeAmt;
    // 冻结金额变化值 正值标识增加， 负值标识减少
    private BigDecimal changeLockAmt;
    // 奖励
    private Integer chagngRewardBean;

    @Tolerate
    public UserAcctParameter() {

    }
}
