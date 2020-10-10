package com.tian.sakura.cdd.common.dict;

/**
 * 0-APP,1-微信公众号,2-小程序
 *
 * @author lvzonggang
 */
public enum EDataChannel {

    APP(0),
    GZH(1),
    MINIP(2);

    EDataChannel(int code) {
        this.code = code;
    }

    private int code;

    public int getCode() {
        return code;
    }
}
