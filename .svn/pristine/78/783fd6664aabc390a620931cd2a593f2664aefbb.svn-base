package com.tian.sakura.cdd.srv.service.task.search;

import com.tian.sakura.cdd.common.exception.BizRuntimeException;
import com.tian.sakura.cdd.common.resp.RespCodeEnum;
import com.tian.sakura.cdd.db.domain.taskLine.TaskLine;
import com.tian.sakura.cdd.db.manage.task.TaskLineManage;
import com.tian.sakura.cdd.srv.service.task.TaskLineSearchRule;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 则按照先进先出，取同一个商品的最早的未满的任务线。
 *
 * @author lvzonggang
 */
@Service("defaultTaskLineSearch")
public class DefaultTaskLineSearch implements TaskLineSearchRule {
    private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TaskLineManage taskLineManage;

    @Override
    public boolean support(TasklineSearchContext context) {
        if (context.getTaskCountUsable() <= 0) {
            return false;
        }
        return true;
    }

    @Override
    public List<String> determinTaskLines(TasklineSearchContext context) {
        List<String> taskLineIds = new ArrayList<>();

        //如果前面的规则查询到产品线
        Integer lineOrder = context.getLineOrder();
        if (lineOrder == null) {

        }
        logger.info("按照插入时间先后顺序的默认规则-上轮规则最后的任务线序号：{}", lineOrder);
        //查找可用的，未满的任务线
        int taskCnt = context.getTaskCountUsable();
        List<TaskLine> taskLineList = taskLineManage
                .selectByProductIdForDispatch(context.getProductId(), lineOrder, taskCnt);

        if (taskLineList.size() != taskCnt) {
            throw new BizRuntimeException(RespCodeEnum.TASK_CNT_NOT_MATCH);
        }

        for (TaskLine taskLine : taskLineList) {
            logger.info("按照插入时间先后顺序的默认规则-用户[{}]分配到的任务线[id={}]",
                    context.getUser().getId(), taskLine.getId());
            taskLineIds.add(taskLine.getId());
        }

        return taskLineIds;
    }
}
