package com.tian.sakura.cdd.db.dao.base;

import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.domain.base.Bank;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BankMapper extends AbstractSingleMapper<Bank, Integer> {

    List<Bank> findLikeBankName(@Param("bankName") String bankName);
}