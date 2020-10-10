package com.tian.sakura.cdd.srv.service.product.param;

import com.tian.sakura.cdd.srv.service.product.PrdCommissionParameter;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 默认计算参数
 *
 * @author lvzonggang
 */
@Setter
@Getter
public class DefaultPrdCommissionParameter implements PrdCommissionParameter {

    private String productId;
    private int taskNumber;
    private BigDecimal totalReward;
    private String taskId;

    public DefaultPrdCommissionParameter() {

    }

    public DefaultPrdCommissionParameter(String productId, int taskNumber, BigDecimal totalReward) {
        this.productId = productId;
        this.taskNumber = taskNumber;
        this.totalReward = totalReward;
    }

    public DefaultPrdCommissionParameter(String productId, int taskNumber,
                                         BigDecimal totalReward, String taskId) {
        this.productId = productId;
        this.taskNumber = taskNumber;
        this.totalReward = totalReward;
        this.taskId = taskId;
    }
}
