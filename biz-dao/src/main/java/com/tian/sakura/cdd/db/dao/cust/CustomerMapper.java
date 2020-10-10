package com.tian.sakura.cdd.db.dao.cust;

import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.domain.cust.Customer;

public interface CustomerMapper extends AbstractSingleMapper<Customer, Long> {

    Customer findByMobile(String mobile);

    Customer findByCustNo(String custNo);
}