package com.tian.sakura.cdd.common.dict;

/**
 * 物流配送方式 1线下自提，2商家送货，3快递包邮，4快递收费
 *
 * @author lvzonggang
 */
public enum EDeliveryType {
    SHIPPING(4, "快递收费"),
    FREE_SHIPPING(3, "快递包邮"),
    MCH_SEND_PRD(2, "商家送货"),
    OFFLINE_FETCH(1, "线下自提");

    EDeliveryType(int code, String name) {
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
