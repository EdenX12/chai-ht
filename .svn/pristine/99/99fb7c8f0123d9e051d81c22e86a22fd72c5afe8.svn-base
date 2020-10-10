package com.tian.sakura.cdd.common.dict;

/**
  *
  *  变动类型 1 充值 2 提现 3 买家独赢  31 纵向躺赢  4 任务躺赢 41 横向躺赢
  *  6 提现驳回 7 任务转让收入 8 转让任务竞价解冻金额
  *  9 任务解冻金额  10 支付任务金 11 支付商品 99 其他
  *
  * @author lvzonggang
 */
public enum  EAmtChangeType {
    PAY_GOODS(11, "支付商品"),
    PAY_TASK(10, "支付任务金"),
    RETURN_TASK(9, "退任务金"),
    TASK_OUT(7,"任务金转让"),
    HOR_REWARD(41, "横向躺赢"),
    TASK_LINE_REWARD(4, "任务躺赢"),
    VER_REWARD(31, "纵向躺赢"),
    BUY_RTN_CASH(3, "购买返现"),
    WITHDRAW(2, "提现"),
    RECHARE(1, "充值");

    EAmtChangeType(int code, String name) {
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

    public static String getNameFromCode(int code) {
        EAmtChangeType[] values = EAmtChangeType.values();
        for (EAmtChangeType changeType : values) {
            if (changeType.getCode() == code) {
                return changeType.getName();
            }
        }
        return "";
    }
}
