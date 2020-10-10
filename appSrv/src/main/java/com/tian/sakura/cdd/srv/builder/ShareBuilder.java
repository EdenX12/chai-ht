package com.tian.sakura.cdd.srv.builder;

import com.tian.sakura.cdd.common.entity.User;
import com.tian.sakura.cdd.db.domain.task.UserShare;

import java.util.Date;

/**
 * 说明。
 *
 * @author lvzonggang
 */
public class ShareBuilder {

    public UserShareBuilder userShareBuilder() {
        return new ShareBuilder.UserShareBuilder();
    }

    public final class UserShareBuilder{
        private UserShare userShare;

        public UserShareBuilder() {
            userShare = new UserShare();
        }

        public UserShareBuilder id(String id) {
            userShare.setId(id);
            return this;
        }

        public UserShareBuilder userId(String userId) {
            userShare.setUserId(userId);
            return this;
        }

        public UserShareBuilder productId(String productId) {
            userShare.setProductId(productId);
            return this;
        }

        public UserShareBuilder taskId(String taskId) {
            userShare.setTaskId(taskId);
            return this;
        }

        public UserShareBuilder parentId(String parentId) {
            userShare.setParentId(parentId);
            return this;
        }

        public UserShareBuilder createTime(Date now) {
            userShare.setCreateTime(now);
            return this;
        }

        public UserShareBuilder remark(String remark) {
            userShare.setRemark(remark);
            return this;
        }

        public UserShare build() {
            return userShare;
        }
    }
}
