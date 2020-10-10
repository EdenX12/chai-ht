package com.tian.sakura.cdd.srv.service.product;

import java.math.BigDecimal;

/**
 * 任务计算结果
 *
 * @author lvzonggang
 */
public interface PrdCommissionCalculateResult {

    String getProductId();

    BigDecimal getSuccessReward();

    BigDecimal getEveryReward();

    Integer getTotalNumber();

    Integer getReceivedTask();

    Integer getUserCount();

    Integer getTotalFocus();
    
    Integer getCurrentReceivedTask();
    
    Integer getSellQuantity();
    
    Integer getHasWinCnt();
}
