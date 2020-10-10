package com.tian.sakura.cdd.common.dict;

/**
  *
  *  变动类型  11-购买返现， 12-拆家返佣， 13-任务金   20-提现，21-支付
  *
  * @author lvzonggang
 */
public enum EAmtChangeQueryType {
    PAY(21, "支付"),
    WITHDRAW(20,"提现"),
    RTN_TASK(13, "退任务金"),
    REWARD(12, "拆家返佣"),
    BUY_RTN_CASH(11, "购买返现");

    EAmtChangeQueryType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    private int code;
    private String name;
    public int getCode() {
        return code;
    }
    public String getName() {
        return name;
    }
}
