package com.tian.sakura.cdd.wx.message.pay;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Getter;
import lombok.Setter;

/**
 * 说明。
 *
 * @author lvzonggang
 */

@Setter
@Getter
@XStreamAlias("xml")
public class UnifiedOrderRsp {

    private String return_code;
    private String return_msg;

    //以下字段在return_code为SUCCESS的时候有返回
    private String appid;
    private String mch_id;
    private String nonce_str;
    private String sign;
    private String result_code;
    private String err_code;
    private String err_code_des;

    //以下字段在return_code 和result_code都为SUCCESS的时候有返回
    private String trade_type;
    private String prepay_id;
}
