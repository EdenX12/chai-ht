package com.tian.sakura.cdd.db.manage.base;

import com.tian.sakura.cdd.common.entity.AbstractSingleManage;
import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.dao.base.BankMapper;
import com.tian.sakura.cdd.db.domain.base.Bank;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 说明。
 *
 * @author lvzonggang
 */
@Service
@Transactional
public class BankManage extends AbstractSingleManage<Bank, Integer> {

    @Autowired
    private BankMapper bankMapper;

    @Override
    protected AbstractSingleMapper<Bank, Integer> getSingleMapper() {
        return bankMapper;
    }

    public List<Bank> findLikeBankName(String bankName) {
        if (StringUtils.isNotEmpty(bankName)) {
            StringBuilder sb = new StringBuilder("%").append(bankName).append("%");
        }

        return bankMapper.findLikeBankName(bankName);
    }
}
