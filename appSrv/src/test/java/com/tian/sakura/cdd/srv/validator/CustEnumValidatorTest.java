package com.tian.sakura.cdd.srv.validator;

import com.tian.sakura.cdd.common.dict.EOrderQueryStatus;
import com.tian.sakura.cdd.srv.validator.annotation.CustEnumVal;
import com.tian.sakura.cdd.srv.web.order.dto.PrdOrderQueryReqBody;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

/**
 * 说明。
 *
 * @author lvzonggang
 */
public class CustEnumValidatorTest {

    public static void main(String[] args) {
        PrdOrderQueryReqBody body = new PrdOrderQueryReqBody();

        body.setOrderStatus(11);

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        Set<ConstraintViolation<PrdOrderQueryReqBody>> set = validator.validate(body, Default.class);

        set.stream().forEach(item -> System.out.println(item.getMessage()));

        //CustEnumVal
        Class<EOrderQueryStatus> clz = EOrderQueryStatus.class;

    }
}
