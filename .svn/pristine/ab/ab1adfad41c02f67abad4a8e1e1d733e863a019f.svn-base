package com.tian.sakura.cdd.db.manage.cust;

import com.tian.sakura.cdd.common.entity.AbstractSingleManage;
import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.dao.cust.CustomerMapper;
import com.tian.sakura.cdd.db.domain.cust.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 客户db服务类
 *
 * @author lvzonggang
 */

@Service
@Transactional
public class CustManange extends AbstractSingleManage<Customer, Long> {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    protected AbstractSingleMapper<Customer, Long> getSingleMapper() {
        return customerMapper;
    }

    public Customer findByMobile(String mobile) {
        return customerMapper.findByMobile(mobile);
    }

    public Customer findByCustNo(String custNo) {
        return customerMapper.findByCustNo(custNo);
    }
}
