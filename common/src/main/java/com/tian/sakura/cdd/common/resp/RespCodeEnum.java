package com.tian.sakura.cdd.common.resp;

/**
 * 响应编码及对应的信息
 * EV-开头的，参数校验异常
 */
public enum RespCodeEnum {
    SUCCESS("200", "成功"),
    SYSTEM_EXCEPTION("-1", "系统异常"),
    UPLOAD_FAIL("T005", "文件上传失败"),
    UPLOAD_PIC_GS("T006", "目前仅支持上传[jpg,jpeg,png]格式的图片"),
    FILE_DOWNLOAD_FAIL("T007", "Excel下载失败"),

    PASSWD_NOT_EQUAL("EV1000", "两次输入的密码不一致"),
    DIGEST_EQUAL("EV1001", "摘要信息不一致"),
    REGISTER_MOBILE("EV1002", "手机号已注册"),
    BIND_WX_EXIST("EV10021", "该微信已绑定"),

    SMS_SNED_FAIL("EV1003", "验证码发送失败-%s"),
    SMS_VALID_FAIL("EV1004", "验证码校验失败，请重新输入"),
    SMS_VALID_TIMEOUT("EV1004", "验证码已超时，请重新发送"),
    APP_LOGIN_FAIL("EV1005", "登录失败"),
    REGISTER_MOBILE_NOT_EXIST("EV1006", "手机号未注册"),
    PASSWD_MATCH_FAIL("EV1007", "旧密码输入错误，请重新输入"),
    PASSWD_MODIFY_FAIL("EV1008", "新密码和旧密码一样"),
    PIC_BASE64_CHECK_PREFIX("EV1010", "图片格式有误"),
    CHECK_INVITE_CODE_FAIL("EV1011", "无效邀请码，请重新输入"),

    FAIL_TOKEN_REQUIRED("EV1020", "参数异常-缺少令牌参数"),
    FAIL_TOKEN_CHECK("EV1020", "令牌无效"),
    FAIL_TOKEN_EXPIRED("EV1021", "参数异常-令牌过期"),
    USERID_NOT_EQUAL("EV1022", "参数异常-账号不一致"),
    MARGIN_ACCT_NOT_ENOUGH("EV1023", "保证金不足"),
    BLANCE_ACCT_NOT_ENOUGH("EV1024", "余额不足"),
    SCORE_ACCT_NOT_ENOUGH("EV1025", "积分不足"),
    REGISTER_MOBILE_ERROR("EV1026", "手机号不存在"),
    AMOUNT_MORE_THAN_ZERO("EV1027", "%s金额应大于零"),
    TODAY_SIGNED("EV1028", "今天已签过到！"),

    CHECK_PRD_STATUS("EV1500", "商品状态不支持%s操作"),

    TASK_CNT_NOT_MATCH("EV2001", "可分配的任务数和购买的数据不匹配"),
    CHECK_PAYING("EV2002", "订单正在支付中，请勿重复支付！"),
    TASK_LIMIT_PRODUCT("EV2003", "任务数超过当前阶段最大限制"),
    ORDR_ITEM_NOT_EQUAL("EV2004", "订单%s不一致"),
    COUPON_TYPE_NO_MATCH("EV2005", "券类型不匹配，请使用%s优惠券"),
    COUPON_STATUS_NO_MATCH("EV2006", "券状态不匹配"),
    NOT_OPERATE_RECORED("EV2007", "%s数据不属于当前操作者"),

    NOT_QUERY_RECORED("EV1105", "对不起，你没有权限查询该数据"),


    USERNAME_PASSWOED_ERRO("A001", "用户名密码错误"),
    NO_ACCESS_RIGHTS("A002", "你没有访问权限"),
    NO_LOGIN("A003", "请先登录系统"),

    NO_BIZ_ID("B001", "缺少业务主键，请核实参数是否正确"),
    NAME_EXIST("B002", "名称[%s]已存在"),
    USER_NO_EXIST("B003", "账号[%s]已存在"),
    DELETE_RECORD_FAIL("B004", "删除记录[%s]失败"),
    STATUS_NOT_MATCH_OPERATE("B005", "当前状态不支持%s操作"),
    NO_BIZ_ID_TEMPLATE("B006", "[%s]缺少业务主键"),

    CHECK_ERROR("C001", "必填字段[%s]不能为空"),
    CHECK_OBJECT_ERROR("C002", "对象不存在"),
    CHECK_OBJECT_ERROR_TEMPLATE("C003", "%s记录不存在，请核实请求参数"),
    CALL_METHOD_FAIL("C004", "该方法已移除"),

    PARAM_DEFECT("9999", "必填参数缺失"),
    PRODUCT_NOT_EXIT("9999", "商品不存在"),
    USER_NOT_EXIST("9999", "用户不存在"),
    USER_NOT_BIND_PHONE("9999", "用户还未绑定手机号"),
    PHONE_FORMAT_ERROR("9999", "手机号格式不正确"),
    QRCODE_EXCEPTION("9999", "二维码生成异常"),

    OTHER("", "");

    RespCodeEnum(String respCode, String respMsg) {
        this.respCode = respCode;
        this.respMsg = respMsg;
    }

    private String respCode;
    private String respMsg;

    public String getRespCode() {
        return respCode;
    }

    public String getRespMsg() {
        return respMsg;
    }


}
