package com.tian.sakura.cdd.common.dict;

/**
 * 审核状态枚举类
 *
 * @author lvzonggang
 *
 */
public enum  EAuditStatus {
    /** 待提交 */
    TO_BE_SUBMIT("0"),
    /** 待审核 */
    TO_BE_AUDIT("2"),
    /** 同意 */
    AUDIT_PASS("4"),
    /** 拒绝 */
    AUDIT_REFUSE("8");

    EAuditStatus(String code){
        this.code = code;
    }

    private String code;

    public String getCode() {
        return code;
    }
}
