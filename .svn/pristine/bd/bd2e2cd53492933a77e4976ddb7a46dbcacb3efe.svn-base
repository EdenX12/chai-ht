package com.tian.sakura.cdd.srv.config;

import com.tian.sakura.cdd.common.exception.BizRuntimeException;
import com.tian.sakura.cdd.common.exception.SystemRuntimeException;
import com.tian.sakura.cdd.common.web.ResponseMessage;
import com.tian.sakura.cdd.common.web.ResponseMessageBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Set;

/**
 * controller类异常统一拦截处理
 *
 * @author lvzg
 * @Date
 */
@RestControllerAdvice
public class CustomerExceptionControllerAdvice {

    private transient Logger logger = LoggerFactory.getLogger(this.getClass());

    private final static String CHECK_01002 = "01002";

    /**
     * 全局异常捕捉处理
     * @param ex
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseMessage errorHandler(Exception ex) {
        logger.error(ex.getMessage(),ex);
        return ResponseMessageBuilder.instance().fail();
    }

    /**
     * 拦截捕捉自定义异常 GzhBizRuntimeException.class
     * @param ex
     * @return
     */
    @ExceptionHandler(value = BizRuntimeException.class)
    public ResponseMessage myErrorHandler(BizRuntimeException ex) {
        logger.error(ex.getMessage(),ex);
        return ResponseMessageBuilder.instance().fail(ex.getErrorCode(),ex.getMessage());
    }

    /**
     * 拦截捕捉自定义异常 GzhSysRuntimeException.class
     * @param ex
     * @return
     */
    @ExceptionHandler(value = SystemRuntimeException.class)
    public ResponseMessage myErrorHandler(SystemRuntimeException ex) {
        logger.error(ex.getMessage(),ex);
        return ResponseMessageBuilder.instance().fail(ex.getErrorCode(),ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseMessage handleBindException(MethodArgumentNotValidException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        logger.info("参数校验异常:{}({})", fieldError.getDefaultMessage(),fieldError.getField());

        return ResponseMessageBuilder.instance().fail(CHECK_01002,fieldError.getDefaultMessage());
    }


    @ExceptionHandler(BindException.class)
    public ResponseMessage handleBindException(BindException ex) {
        //校验 除了 requestbody 注解方式的参数校验 对应的 bindingresult 为 BeanPropertyBindingResult
        FieldError fieldError = ex.getBindingResult().getFieldError();
        logger.info("必填校验异常:{}({})", fieldError.getDefaultMessage(),fieldError.getField());

        return ResponseMessageBuilder.instance().fail(CHECK_01002,fieldError.getDefaultMessage());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseMessage handleBindException(ValidationException ex) {
        StringBuilder message = new StringBuilder();
        if (ex instanceof ConstraintViolationException) {
            ConstraintViolationException exs = (ConstraintViolationException) ex;

            Set<ConstraintViolation<?>> violations = exs.getConstraintViolations();

            for (ConstraintViolation<?> item : violations) {
                message.append(item.getMessage());
            }
            return ResponseMessageBuilder.instance().fail(CHECK_01002,message.toString());
        } else {
            return ResponseMessageBuilder.instance().fail(CHECK_01002,"参数校验失败，请检查数据。");
        }
    }
}
