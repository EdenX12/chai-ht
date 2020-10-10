package com.tian.sakura.cdd.common.dict;

/**
 * 支付状态 状态 0 锁定（支付中） 1 已支付； 2-待支付； 3 不支付（取消或过期）
 *
 * @author lvzonggang
 * @Date 2018/8/21
 */
public enum EPayStatus {
    CLOSED(4, "关闭"),
    CANSEL(3, "取消"),
    TO_BE_PAY(2, "待支付"),
    PAYED(1, "已支付"),
    LOCK(0, "支付中");

    EPayStatus(int code, String name) {
        this.name = name;
        this.code = code;
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
