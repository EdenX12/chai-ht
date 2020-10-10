package com.tian.sakura.cdd.db.manage.msg;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tian.sakura.cdd.common.entity.AbstractSingleManage;
import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.common.req.msg.UserMsgReqBody;
import com.tian.sakura.cdd.db.dao.msg.UserMsgMapper;
import com.tian.sakura.cdd.db.domain.msg.UserMsg;
import com.tian.sakura.cdd.db.domain.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMsgManage extends AbstractSingleManage<UserMsg, String> {

    @Autowired
    private UserMsgMapper userMsgMapper;

    @Override
    protected AbstractSingleMapper<UserMsg, String> getSingleMapper() {
        return userMsgMapper;
    }

    public List<UserMsg> getUserMsgList(UserMsgReqBody userMsg) {
        return userMsgMapper.getUserMsgList(userMsg);
    }


    public PageInfo<UserMsg> selectByUserId(String userId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<UserMsg> dataList = userMsgMapper.selectByUserId(userId);
        return PageInfo.of(dataList);
    }
}
