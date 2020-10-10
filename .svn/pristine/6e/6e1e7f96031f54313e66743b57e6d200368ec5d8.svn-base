package com.tian.sakura.cdd.common.dict;

/**
 * 结算状态  0：未完成  1：结算中  2： 已分润
 *
 * @author lvzonggang
 */

public enum ESettleStatus {
    SETTLED(2,"已分润"),
    SETTLING(1,"结算中"),
    TO_BE_SETTLE(0,"未完成");

    ESettleStatus(int code, String name) {
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
