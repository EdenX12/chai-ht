package com.tian.sakura.cdd.common.web;

/**
 * 说明。
 *
 * @author lvzonggang
 */
public class ResponseMessageBuilder {
    private static String RESP_CODE_OK = "0000";

    private int status = 0;
    private String respCode = RESP_CODE_OK;
    private String respMsg = "操作成功";

    private static ResponseMessageBuilder instance = null;

    private ResponseMessageBuilder() {

    }

    public static ResponseMessageBuilder instance() {
        if (instance == null) {
            instance = new ResponseMessageBuilder();
        }
        return instance;
    }

    public  ResponseMessage ok() {
        ResponseHead head = new ResponseHead();
        head.setRespCode(respCode);
        head.setRespMsg(respMsg);
        head.setStatus(status);

        return new ResponseMessage(head,null);
    }

    public  ResponseMessage fail() {
        ResponseHead head = new ResponseHead();
        head.setRespCode("-1");
        head.setRespMsg("未知异常");
        head.setStatus(1);

        return new ResponseMessage(head,null);
    }

    public  ResponseMessage fail(String respCode, String respMsg) {
        ResponseHead head = new ResponseHead();
        head.setRespCode(respCode);
        head.setRespMsg(respMsg);
        head.setStatus(1);

        return new ResponseMessage(head,null);
    }

    public ResponseMessage body(Object body) {
        ResponseHead head = new ResponseHead();
        head.setRespCode(respCode);
        head.setRespMsg(respMsg);
        head.setStatus(status);

        return new ResponseMessage(head,body);
    }
}
