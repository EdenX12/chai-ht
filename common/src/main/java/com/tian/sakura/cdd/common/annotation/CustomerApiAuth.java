package com.tian.sakura.cdd.common.annotation;

import com.tian.sakura.cdd.common.dict.EUserType;

import java.lang.annotation.*;

/**
 * web层拦截日志参数
 *
 * @author lvzonggang
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE,ElementType.METHOD })
public @interface CustomerApiAuth {

    EUserType[] value() default {};
}
