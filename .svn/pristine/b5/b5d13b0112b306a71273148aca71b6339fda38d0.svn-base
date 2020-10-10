package com.tian.sakura.cdd.common.dict;

/**
 * 待付款  待发货  待收货  退还货 已完成  , 待取货
 *
 * @author lvzonggang
 */
public enum  EOrderQueryStatus {
    UNKNOWN(999),
    TO_BE_FETCH(5),
    CLOSED(4),
    BACK_OR_CHANGE(3),
    TO_BE_RECEIVE(2),
    TO_BE_SEND(1),
    TO_BE_PAY(0);


    EOrderQueryStatus(int code) {
        this.code = code;
    }

    private int code;

    public int getCode() {
        return code;
    }
}
