package com.tian.sakura.cdd.srv.validator;

import com.tian.sakura.cdd.srv.validator.annotation.CustEnumVal;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 自定义的枚举范围校验
 *
 * @author lvzonggang
 */
public class CustEnumValidator implements ConstraintValidator<CustEnumVal, Integer> {
    private List<Integer> valueList = new ArrayList<>();
    private Class enumClz;

    @Override
    public void initialize(CustEnumVal constraintAnnotation) {
        String values = constraintAnnotation.value();
        String[] valueArr = StringUtils.split(values, ",");

        for (int i=0; i<valueArr.length; i++) {
            valueList.add(Integer.valueOf(valueArr[i].trim()));
        }

    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        if (valueList.contains(value)) {
            return true;
        }

        return false;
    }
}
