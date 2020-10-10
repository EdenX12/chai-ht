package com.tian.sakura.cdd.common.dict;

/**
 * 1 领取任务支付 2 购买订单支付 3 转让任务报价 4 充值
 *
 * @author lvzonggang
 */
public enum  EPayBizType {
    PAY_RECHARGE(4, "充值"),
    PAY_TASK_TRADE(3, "转让任务金"),
    PAY_ORDER(2, "商品订单支付"),
    PAY_TASK(1, "任务金支付");


    EPayBizType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private int code;
    private String desc;

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
