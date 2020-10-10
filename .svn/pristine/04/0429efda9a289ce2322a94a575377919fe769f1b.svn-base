package com.tian.sakura.cdd.srv.service.user;

import com.tian.sakura.cdd.common.dict.EFollowStatus;
import com.tian.sakura.cdd.common.dict.EFollowType;
import com.tian.sakura.cdd.common.util.IdGenUtil;
import com.tian.sakura.cdd.db.domain.productReviewStat.ProductReviewStat;
import com.tian.sakura.cdd.db.domain.user.UserFollow;
import com.tian.sakura.cdd.db.manage.product.ProductReviewStatManage;
import com.tian.sakura.cdd.db.manage.user.UserFollowManage;
import com.tian.sakura.cdd.srv.builder.ProductBuilder;
import com.tian.sakura.cdd.srv.web.user.UserFollowController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户感兴趣产品或任务服务类
 *
 * @author lvzonggang
 */
@Service
@Transactional
public class UserInterestService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserFollowManage userFollowManage;
    @Autowired
    private ProductReviewStatManage productReviewStatManage;

    public Map<String,Object> interestInProduct(String userId, String productId) {
        boolean focus = false;
        //根据userId和productId查询关注的产品记录
        UserFollow dbUserFollow = userFollowManage.selectByUserIdAndProductId(userId, productId);
        Integer nextStatus = EFollowStatus.Y.getCode();
        if (dbUserFollow == null || dbUserFollow.getFollowType() == EFollowType.TASK.getCode()) {
            logger.info("用户[id={}]首次关注商品[productId={}]", userId, productId);
            focus = true;
            //插入数据
            dbUserFollow = new UserFollow();
            dbUserFollow.setId(IdGenUtil.uuid());
            dbUserFollow.setProductId(productId);
            dbUserFollow.setUserId(userId);
            dbUserFollow.setFollowStatus(EFollowStatus.Y.getCode());
            dbUserFollow.setFollowType(EFollowType.PRODUCT.getCode());
            userFollowManage.insert(dbUserFollow);
            logger.info("持久化用户[id={}]关注商品[productId={}]记录", userId, productId);
        } else {
            Integer followStatus = dbUserFollow.getFollowStatus();
            logger.info("用户[id={}]当前关注商品[productId={}]的状态", userId, productId, followStatus);
            //取反
            nextStatus = Math.abs(followStatus - 1);
            focus = nextStatus != 0;
            //更新当前记录
            UserFollow updateRecord = new UserFollow();
            updateRecord.setId(dbUserFollow.getId());
            updateRecord.setFollowStatus(nextStatus);
            userFollowManage.updateByPrimaryKeySelective(updateRecord);
        }

        //如果nextStatus=0, 用户取消关注，总数-1， 如果nextStatus=1,用户关注商品，总数+1
        ProductReviewStat productReviewStat = productReviewStatManage.selectByProductId(productId);
        if (productReviewStat != null) {
            if (nextStatus == 0) {
                productReviewStatManage.decrease(productId);
            } else {
                productReviewStatManage.increase(productId);
            }
        } else {
            productReviewStat = ProductBuilder.buildNewReviewStat(productId);
            productReviewStatManage.insert(productReviewStat);
        }


        int cnt = productReviewStatManage.totalFocus(productId);
        Map<String,Object> map = new HashMap<>();
        map.put("cnt", cnt);
        map.put("focus", focus);
        return map;
    }
}
