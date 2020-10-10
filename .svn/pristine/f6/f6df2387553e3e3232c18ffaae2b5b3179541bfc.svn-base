package com.tian.sakura.cdd.common.dict;

/**
 * 状态 0 已申请 1 已打款 2 已驳回
 *
 * @author lvzonggang
 */
public enum  EWithdrawStatus {


    REFUSE(2, "已驳回"),
    AGREE(1, "已打款"),
    APPLY(0, "已申请");

    EWithdrawStatus(Integer code, String name){
        this.code = code;
        this.name = name;
    }

    private Integer code;
    private String name;

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static String getNameByCode(Integer code) {
        EWithdrawStatus[] values = EWithdrawStatus.values();
        for (EWithdrawStatus item : values) {
            if (item.getCode() == code) {
                return item.getName();
            }
        }
        return "未知";
    }
}
