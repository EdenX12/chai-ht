package com.tian.sakura.cdd.common.dict;

/**
 * 账户类型- 可用账户 ， 冻结账户
 *
 * @author lvzonggang
 */
public enum  EAcctType {

    BALANCE(0),
    LOCK(1);

    EAcctType(int code) {
        this.code = code;
    }

    private int code;

    public int getCode() {
        return code;
    }
}
