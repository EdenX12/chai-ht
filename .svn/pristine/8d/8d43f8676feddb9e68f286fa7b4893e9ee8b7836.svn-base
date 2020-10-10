package com.tian.sakura.cdd.common.dict;

/**
 * 0未付款  1已付款待发货  2已发货  3已确认收货 4 申请退货退款 5 已退货退款 6 超时关闭； -1 已取消
 *
 * @author lvzonggang
 */
public enum  EOrderStatus {
    CANCLE(-1, "已取消"),
    CLOSED(6, "超时关闭"),
    BACKED(5, "已退货/退款"),
    APPLY_BACK(4, "申请退货/退款"),
    CONFIRM_RECEIVED(3, "已收货"),
    SENDED(2 , "已发货"),
    TO_BE_SEND(1, "待发货"),
    TO_BE_PAY(0, "待付款");

    EOrderStatus(int code, String name) {
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

    public static String getNameByCode(Integer code) {
        EOrderStatus[] values = EOrderStatus.values();
        for (EOrderStatus orderStatus : values) {
            if (orderStatus.getCode() == code) {
                return orderStatus.getName();
            }
        }
        return "未知";
    }
}
