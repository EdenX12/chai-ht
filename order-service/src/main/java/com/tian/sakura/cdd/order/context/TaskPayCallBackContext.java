package com.tian.sakura.cdd.order.context;

import lombok.Getter;
import lombok.Setter;

/**
 * 订单支付回调上下文
 *
 * @author lvzonggang
 */
@Setter
@Getter
public class TaskPayCallBackContext extends BasePayCallBackContext{
    // 任务金订单id
    private String userTaskId;

}
