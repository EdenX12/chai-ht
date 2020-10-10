package com.tian.sakura.cdd.common.dict;

/**
 * 0-未使用 ；1-已使用；2-过期;3-作废
 *
 * @author lvzonggang
 */
public enum ECouponStatus {
    OVERDEUE(2),
    USED(1),
    TO_BE_USED(0),
    CANCEL(3);

    ECouponStatus(int code) {
        this.code = code;
    }

    private int code;

    public int getCode() {
        return code;
    }
}
