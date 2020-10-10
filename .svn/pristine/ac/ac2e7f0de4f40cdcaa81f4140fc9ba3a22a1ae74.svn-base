package com.tian.sakura.cdd.srv.service.task.search;

import com.tian.sakura.cdd.db.domain.user.SUser;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 上下文
 *
 * @author lvzonggang
 */

@Setter
@Getter
public class TasklineSearchContext {

    private SUser user;
    private String productId;
    /** 总购买数量 */
    private int totalTaskCount;
    /** 可插入的数量 */
    private int taskCountUsable;
    /** 最后一条任务线的插入时间 */
    private Integer lineOrder;

    public TasklineSearchContext() {

    }

    public TasklineSearchContext(SUser user, String productId, int totalTaskCount) {
        this.user = user;
        this.productId = productId;
        this.totalTaskCount = totalTaskCount;
        this.taskCountUsable = totalTaskCount;
    }
}
