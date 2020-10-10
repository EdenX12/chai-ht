package com.tian.sakura.cdd.common.exception;

import com.tian.sakura.cdd.common.resp.RespCodeEnum;

/**
 * 业务异常
 *
 * @author lvzonggang
 */
public class BizRuntimeException extends RuntimeException{

    private String errorCode;

    /**
     * 针对属性为空异常
     *
     * @param errorCode
     * @param msg
     * @param attribute
     */
    public BizRuntimeException(String errorCode, String msg, String attribute) {
        super(msg);
        this.errorCode = errorCode;

    }

    public BizRuntimeException(String errorCode, String msg, Throwable cause) {
        super(msg, cause);
        this.errorCode = errorCode;

    }

    public BizRuntimeException(String errorCode, String msg) {
        super(msg);
        this.errorCode = errorCode;
    }

    public BizRuntimeException(RespCodeEnum error) {
        super(error.getRespMsg());
        this.errorCode = error.getRespCode();
    }

    public BizRuntimeException(RespCodeEnum error, Throwable cause) {
        super(error.getRespMsg(),cause);
        this.errorCode = error.getRespCode();

    }

    public BizRuntimeException(RespCodeEnum error, String... args) {
        super(String.format(error.getRespMsg(),args));
        this.errorCode = error.getRespCode();
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

}
