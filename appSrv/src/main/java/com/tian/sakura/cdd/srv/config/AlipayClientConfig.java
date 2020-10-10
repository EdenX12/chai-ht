package com.tian.sakura.cdd.srv.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.CertAlipayRequest;
import com.alipay.api.DefaultAlipayClient;
import com.tian.sakura.cdd.srv.service.alipay.AlipayDbParameters;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.io.File;
import java.io.IOException;

/**
 * 说明。
 *
 * @author lvzonggang
 * @Date 2019/8/27
 */

@Configuration
public class AlipayClientConfig {
    private static String ALIPAY_GATEWAY = "https://openapi.alipay.com/gateway.do";

    @Autowired
    private AlipayDbParameters alipayProperties;


    @Bean
    @Profile({"dev","local"})
    public AlipayClient alipayClientDev() {
        return new DefaultAlipayClient(ALIPAY_GATEWAY,"appid", "-");
    }
    /**
     * 初始化alipayClient-证书
     * alipayClient 只需要初始化一次，后续调用不同的 API 都可以使用同一个 alipayClient 对象。
     *
     * @return
     */
    @Bean
    @Profile("sit")
    public AlipayClient  alipayClient() {
        CertAlipayRequest certAlipayRequest = new CertAlipayRequest();
        //支付宝网关（固定）
        certAlipayRequest.setServerUrl(ALIPAY_GATEWAY);
        //APPID即创建应用后生成
        String appid = alipayProperties.getAppid();
        certAlipayRequest.setAppId(appid);
        //开发者应用私钥，由开发者自己生成
        String appPrivateKey = parseAppPrivateKey(alipayProperties.getAppPrivateKey());
        certAlipayRequest.setPrivateKey(appPrivateKey);
        //请求和签名使用的字符编码格式，支持GBK和UTF-8
        String charset = "UTF-8";
        certAlipayRequest.setCharset(charset);
        //参数返回格式，只支持json
        String format = "json";
        certAlipayRequest.setFormat(format);
        //商户生成签名字符串所使用的签名算法类型，目前支持 RSA2 和 RSA，推荐使用 RSA2
        String signType = "RSA2";
        certAlipayRequest.setSignType(signType);
        //设置应用公钥证书路径
        String appCertPath = alipayProperties.getAppPublicKey();
        //certAlipayRequest.setCertPath("E:\\project\\202003cdd\\svnDoc\\07.系统运维\\开发-支付宝证书\\appCertPublicKey_2021001155693890.crt");
        certAlipayRequest.setCertPath(appCertPath);
        //支付宝公钥，由支付宝生成
        String alipayPublicKey = alipayProperties.getAlipayPublicKey();
        //certAlipayRequest.setAlipayPublicCertPath("E:\\project\\202003cdd\\svnDoc\\07.系统运维\\开发-支付宝证书\\alipayCertPublicKey_RSA2.crt");
        certAlipayRequest.setAlipayPublicCertPath(alipayPublicKey);
        //设置支付宝根证书路径
        String alipay_root_cert_path = alipayProperties.getAlipayRootCrt();
        //certAlipayRequest.setRootCertPath("E:\\project\\202003cdd\\svnDoc\\07.系统运维\\开发-支付宝证书\\alipayRootCert.crt");
        certAlipayRequest.setRootCertPath(alipay_root_cert_path);
        try {
            //certAlipayRequest.setProxyHost("10.1.27.102");
            //certAlipayRequest.setProxyPort(8080);
            AlipayClient alipayClient = new DefaultAlipayClient(certAlipayRequest);
            return alipayClient;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    private String parseAppPrivateKey(String path) {
        return doReadFileToString(path);
    }
    private String parseAppPublicKey(String path) {
        return doReadFileToString(path);
    }
    private String parseAlipayPubKey(String path) {
        return doReadFileToString(path);
    }
    private String parseAlipayRootCer(String path) {
        return doReadFileToString(path);
    }

    private String doReadFileToString(String path) {
        try {
            File file = new File(path);
            return FileUtils.readFileToString(file,"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
