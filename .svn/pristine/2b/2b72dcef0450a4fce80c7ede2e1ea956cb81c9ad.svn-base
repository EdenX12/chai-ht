package com.tian.sakura.cdd.srv.service.task.search;

import com.tian.sakura.cdd.db.domain.userTaskLine.UserTaskLine;
import com.tian.sakura.cdd.db.manage.user.UserTaskLineManage;
import com.tian.sakura.cdd.srv.service.task.TaskLineSearchRule;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 则取其上级所在的同一个商品的任务线；
 *
 * @author lvzonggang
 */

@Service("parentTaskLineSearch")
public class ParentTaskLineSearch implements TaskLineSearchRule {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserTaskLineManage userTaskLineManage;

    @Override
    public boolean support(TasklineSearchContext context) {
        if (context.getTaskCountUsable() <= 0) {
            return false;
        }
        //查找该用户的上级
        String parentId = context.getUser().getParentId();
        logger.info("用户[{}]的上级用户id是[{}]", context.getUser().getUserPhone(), parentId);
        if (StringUtils.isEmpty(parentId)) {
            logger.info("用户[{}]没有上级，从默认规则查找产品线", context.getUser().getUserPhone());
            return false;
        }
        return true;
    }

    @Override
    public List<String> determinTaskLines(TasklineSearchContext context) {
        List<String> taskLineIds = new ArrayList<>();

        String userId = context.getUser().getId();
        String productId = context.getProductId();
        //该用户的上级
        String parentId = context.getUser().getParentId();
        //查找该用户上级是否购买了该商品的任务金
        List<UserTaskLine> userTaskLineList = userTaskLineManage.selectForDispatch(userId, productId);
        if (userTaskLineList == null || userTaskLineList.isEmpty()) {
            logger.info("用户[{}]的上级用户购买的任务线");
            return taskLineIds;
        }
        //对比本次购买的任务数和上级购买的人数
        int parentTaskCnt = userTaskLineList.size();
        int diffCnt = context.getTaskCountUsable() - parentTaskCnt;
        if (diffCnt > 0) {
            logger.info("本次用户购买的数量大于上级购买的数量");
            //本次用户购买的数量大于上级购买的数量， 全部分配到该客户下，并且进入下一轮搜索规则
            userTaskLineList.forEach(item -> {
                logger.info("用户[{}]本次购买的任务线，其中和上级用户分配到相同有{}", userId, item.getTaskLineId());
                taskLineIds.add(item.getTaskLineId());
            });
            //下轮规则可用的任务线数量
            context.setTaskCountUsable(diffCnt);
            UserTaskLine lastItem = userTaskLineList.get(userTaskLineList.size() - 1);
            //最后一个任务线的插入时间
            context.setLineOrder(lastItem.getLineOrder());
        } else {
            logger.info("本次用户购买的数量小于上级购买的数量");
            int index = 1;
            for (UserTaskLine item : userTaskLineList) {
                logger.info("用户[{}]本次购买的任务线，其中和上级用户分配到相同有{}", userId, item.getTaskLineId());
                taskLineIds.add(item.getTaskLineId());
                index ++;
                if (index > context.getTaskCountUsable()) {
                    context.setLineOrder(item.getLineOrder());
                    break;
                }
            }
            //设置可用数量为零，不在执行后续规则
            context.setTaskCountUsable(0);

        }
        return taskLineIds;
    }
}
