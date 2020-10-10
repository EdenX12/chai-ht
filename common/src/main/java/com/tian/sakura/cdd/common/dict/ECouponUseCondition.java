package com.tian.sakura.cdd.common.dict;

/**
 * 使用条件 1-固定金额券 2-超级抵扣券 3-购买任务返任务金优惠券 （所有商品通用的情况下 商品ID不设定）
 *
 * @author lvzonggang
 */
public enum ECouponUseCondition {

    SUPER_COUPON(2),
    FIX_AMT(1);

    ECouponUseCondition(int code) {
        this.code = code;
    }

    private int code;

    public int getCode() {
        return code;
    }
}
