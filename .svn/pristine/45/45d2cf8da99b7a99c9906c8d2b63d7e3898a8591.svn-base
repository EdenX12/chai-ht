package com.tian.sakura.cdd.common.dict;

/**
 * 1、普通客户；2-商家；；3-拆家；4-即是拆家也是商家
 *
 * @author lvzonggang
 */
public enum  EUserType {
    /** 游客，未注册用户 */
    NONE(99),
    /** 普通客户 */
    NORMAL_CUST(1),
    /** 商家 */
    BUSINESS(2);


    EUserType(Integer code){
        this.code = code;
    }

    private Integer code;

    public Integer getCode() {
        return code;
    }

    public static EUserType getUserType(Integer code) {
        EUserType[] userTypes = EUserType.values();
        for (EUserType userType : userTypes) {
            if (userType.getCode() == code) {
                return userType;
            }
        }

        return null;
    }
}
