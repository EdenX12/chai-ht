package com.tian.sakura.cdd.srv.service.task;

import com.tian.sakura.cdd.db.domain.user.SUser;
import com.tian.sakura.cdd.srv.service.task.search.TasklineSearchContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 说明。
 *
 * @author lvzonggang
 */

@Service
public class TaskLineDetermination {

    @Autowired
    @Qualifier("parentTaskLineSearch")
    private TaskLineSearchRule parentTaskLineSearch;
    @Autowired
    @Qualifier("defaultTaskLineSearch")
    private TaskLineSearchRule defaultTaskLineSearch;



    /**
     * 查找规则-
     *
     * 则按照先进先出，取同一个商品的最早任务线。
     *
     * @param user
     * @param productId
     * @param taskCount
     * @return
     */
    List<String> determinTaskLines(SUser user, String productId, int taskCount) {
        List<TaskLineSearchRule> rules = new ArrayList<>();
        //rules.add(parentTaskLineSearch);
        rules.add(defaultTaskLineSearch);

        TasklineSearchContext context = new TasklineSearchContext(user, productId, taskCount);

        List<String> taskLineIds = new ArrayList<>();

        for (TaskLineSearchRule searchRule : rules) {
            if (searchRule.support(context)) {
                taskLineIds.addAll(searchRule.determinTaskLines(context));
            }
        }
        return taskLineIds;
    }
}
