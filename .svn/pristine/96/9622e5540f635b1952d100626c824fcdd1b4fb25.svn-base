package com.tian.sakura.cdd.common.web;

import com.tian.sakura.cdd.common.resp.RespCodeEnum;
import lombok.Data;

/**
 * 统一返回包装类
 *
 * @author lvzonggang
 */
@Data
public class CommonResult<T> {
    /** 0-正常；-1：系统异常； 1:应用异常 */
    private int status = 0;
    private String respCode = "000";
    private String respMsg = "";
    private T data;
    public CommonResult() {
    }

    public static CommonResult ok() {
        CommonResult commonResult = new CommonResult(null);
        return commonResult;
    }

    public static CommonResult fail() {
        CommonResult commonResult = new CommonResult(null);
        commonResult.setStatus(-1);
        commonResult.setRespCode("-1");
        commonResult.setRespMsg("系统异常，请稍后尝试");
        return commonResult;
    }

    public static CommonResult fail(RespCodeEnum respCodeEnum) {
        CommonResult commonResult = new CommonResult(null);
        commonResult.setStatus(1);
        commonResult.setRespCode(respCodeEnum.getRespCode());
        commonResult.setRespMsg(respCodeEnum.getRespMsg());
        return commonResult;
    }

    public static CommonResult fail(String appCode, String appMsg) {
        CommonResult commonResult = new CommonResult(null);
        commonResult.setStatus(1);
        commonResult.setRespCode(appCode);
        commonResult.setRespMsg(appMsg);
        return commonResult;
    }

    public CommonResult(T body) {
        this.data = body;
    }
}
