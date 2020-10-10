package com.tian.sakura.cdd.db.dao.msg;

import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.common.req.msg.UserMsgReqBody;
import com.tian.sakura.cdd.db.domain.msg.UserMsg;
import com.tian.sakura.cdd.db.domain.product.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMsgMapper extends AbstractSingleMapper<UserMsg, String> {


    List<UserMsg> getUserMsgList(UserMsgReqBody userMsg);

    List<UserMsg> selectByUserId(String userId);
}