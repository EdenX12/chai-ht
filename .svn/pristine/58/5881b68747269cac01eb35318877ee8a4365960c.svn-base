package com.tian.sakura.cdd.common.dict;

/**
 * 类型 1-买家独赢;2-任务躺赢;3-横向躺赢;4-纵向躺赢;5-平台返回任务金;
 *
 * @author lvzonggang
 */
public enum EBonusType {

    TASK_OUT(5,"平台返回任务金"),
    HOR_REWARD(3, "横向躺赢"),
    TASK_LINE_REWARD(2, "任务躺赢"),
    VER_REWARD(4, "纵向躺赢"),
    BUY_RTN_CASH(1, "购买返现");


    EBonusType(int code, String desc) {
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
