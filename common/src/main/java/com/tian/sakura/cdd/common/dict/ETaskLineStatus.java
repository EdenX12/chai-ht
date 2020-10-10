package com.tian.sakura.cdd.common.dict;

/**
 * 0 已接任务  1 转让中 2 转让成功 3 佣金计算中 4 佣金结算完成待入账  5 佣金已入账
 *
 * @author lvzonggang
 */
public enum ETaskLineStatus {

    RECEIVE_TASK(0),
    TRADING(1),
    TRADED(2),
    FEE_CALCULATING(3),
    FEE_CALCULATED(4),
    FEE_TO_ACCT(5);


    ETaskLineStatus(int code) {
        this.code = code;
    }

    private int code;

    public int getCode() {
        return code;
    }
}
