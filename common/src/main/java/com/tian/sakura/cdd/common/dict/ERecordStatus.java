package com.tian.sakura.cdd.common.dict;

/**
 * 记录状态
 *
 * @author lvzonggang
 * @Date 2018/8/21
 */
public enum ERecordStatus {
    /** 有效 */
    VALID("1"),
    /** 无效 */
    INVALID("0");

    ERecordStatus(String code) {
        this.code = code;
    }

    private String code;

    public String getCode() {
        return code;
    }

}
