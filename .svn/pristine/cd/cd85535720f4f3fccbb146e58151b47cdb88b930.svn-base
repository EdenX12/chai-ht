package com.tian.sakura.cdd.srv.service.alipay;

import com.tian.sakura.cdd.srv.service.params.ParamsService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 说明。
 *
 * @author lvzonggang
 */
@Service
public class AlipayDbParameters implements InitializingBean {

    @Autowired
    private ParamsService paramsService;

    private String appid;
    private String pid;
    private String appPrivateKey;
    private String appPublicKey;
    private String alipayPublicKey;
    private String alipayRootCrt;
    //private String noticeUrl;

    private static final String APPID_KEY = "alipay_open_appid";
    private static final String MCN_ID_KEY = "alipay_open_mchid";
    private static final String APP_PRIVATE_KEY = "alipay_app_private_key";
    private static final String APP_PUBLIC_KEY = "alipay_app_public_key";
    private static final String ALIPAY_PUBLIC_KEY = "alipay_public_key";
    private static final String ALIPAY_ROOT_KEY = "alipay_root_cert";



    @Override
    public void afterPropertiesSet() throws Exception {
        appid = paramsService.getValueNoCache(APPID_KEY);
        pid = paramsService.getValueNoCache(MCN_ID_KEY);
        appPrivateKey = paramsService.getValueNoCache(APP_PRIVATE_KEY);
        //
        //appPrivateKey = "E:\\project\\202003cdd\\svnDoc\\07.系统运维\\开发-支付宝证书\\www.chaiduoduo.top_私钥.txt";

        appPublicKey = paramsService.getValueNoCache(APP_PUBLIC_KEY);
        //appPublicKey = "E:\\project\\202003cdd\\svnDoc\\07.系统运维\\开发-支付宝证书\\appCertPublicKey_2021001155693890.crt";
        alipayPublicKey = paramsService.getValueNoCache(ALIPAY_PUBLIC_KEY);
        //alipayPublicKey = "E:\\project\\202003cdd\\svnDoc\\07.系统运维\\开发-支付宝证书\\alipayCertPublicKey_RSA2.crt";
        alipayRootCrt = paramsService.getValueNoCache(ALIPAY_ROOT_KEY);
        //alipayRootCrt = "E:\\project\\202003cdd\\svnDoc\\07.系统运维\\开发-支付宝证书\\alipayRootCert.crt";
        //noticeUrl = paramsService.getValueNoCache(alipay_noticy_url);
    }

    public ParamsService getParamsService() {
        return paramsService;
    }

    public void setParamsService(ParamsService paramsService) {
        this.paramsService = paramsService;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getAppPrivateKey() {
        return appPrivateKey;
    }

    public void setAppPrivateKey(String appPrivateKey) {
        this.appPrivateKey = appPrivateKey;
    }

    public String getAppPublicKey() {
        return appPublicKey;
    }

    public void setAppPublicKey(String appPublicKey) {
        this.appPublicKey = appPublicKey;
    }

    public String getAlipayPublicKey() {
        return alipayPublicKey;
    }

    public void setAlipayPublicKey(String alipayPublicKey) {
        this.alipayPublicKey = alipayPublicKey;
    }

    public String getAlipayRootCrt() {
        return alipayRootCrt;
    }

    public void setAlipayRootCrt(String alipayRootCrt) {
        this.alipayRootCrt = alipayRootCrt;
    }

}
