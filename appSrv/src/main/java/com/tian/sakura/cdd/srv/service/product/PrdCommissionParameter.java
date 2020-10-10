package com.tian.sakura.cdd.srv.service.product;

import java.math.BigDecimal;

/**
 * 计算商品任务金的相关参数
 *
 * @author lvzonggang
 */
public interface PrdCommissionParameter {

    /**
     * 任务佣金
     *
     * @return
     */
    BigDecimal getTotalReward();

    /**
     * 商品每条任务线上的最大任务数
     *
     * @return
     */
    int getTaskNumber();

    /**
     * 产品标识
     *
     * @return
     */
    String getProductId();
    
    /**
     * 任务id
     * @return
     */
    String getTaskId();
}
