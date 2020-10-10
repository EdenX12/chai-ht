package com.tian.sakura.cdd.srv.validator.annotation;

import com.tian.sakura.cdd.srv.validator.CustEnumValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 枚举值范围校验
 *
 * @author lvzonggang
 */

@Documented
@Constraint(validatedBy = CustEnumValidator.class)
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CustEnumVal {
    String value();

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String message() default "{jcom.tian.sakura.cdd.srv.validator.annotation.CustPattern.message}";
}
