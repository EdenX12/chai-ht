package com.tian.sakura.cdd.db.dao.user;

import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.domain.user.UserBank;

import java.util.List;

public interface UserBankMapper extends AbstractSingleMapper<UserBank, String> {

    List<UserBank> selectByUserId(String userId);
}