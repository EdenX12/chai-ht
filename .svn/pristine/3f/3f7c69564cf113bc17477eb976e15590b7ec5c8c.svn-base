package com.tian.sakura.cdd.common.util;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 阿里发送短信工具类
 */
@Slf4j
@Component
public class SendSmsUtils {
    //产品名称:阿里云短信API产品,开发者无需替换
    private static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    private static final String domain = "dysmsapi.aliyuncs.com";

    /**
     * 阿里短信服务接口
     */
    public static SendSmsResponse sendSms(String phone, String code, String templateCode, String accessKeyId, String accessKeySecret, String signName) {
        SendSmsResponse sendSmsResponse = null;
        try {
            Map<String, String> dataMap = new HashMap<>();
            dataMap.put("code", code);
            String data = JSON.toJSONString(dataMap);
            //可自助调整超时时间
            System.setProperty("sun.net.client.defaultConnectTimeout", "5000");
            System.setProperty("sun.net.client.defaultReadTimeout", "5000");
            //初始化acsClient,暂不支持region化<br>/　　　　　　　　　　　　　 //accessKeyId  控制台找<br>　　　　　　　　　　　　　　//accessKeySecret  在创建用户的时候与上面的KeyId一起出现，只出现一次，第一次要记得保存，否则后面找不到需要重新创建
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
            IAcsClient acsClient = new DefaultAcsClient(profile);
            //组装请求对象-具体描述见控制台
            SendSmsRequest request = new SendSmsRequest();
            //必填:要发送的手机号
            request.setPhoneNumbers(phone);
            //必填:短信签名-在短信控制台中找
            request.setSignName(signName);
            //必填:短信模板-在短信控制台中找
            request.setTemplateCode(templateCode);
            //可选:要发送的内容，这里data要注意格式，是map格式转为String的
            request.setTemplateParam(data);
            //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
            request.setOutId("0");
            //此处可能会抛出异常
            sendSmsResponse = acsClient.getAcsResponse(request);
        } catch (ClientException e) {
            log.error("-----------调用阿里短信发送失败--------------");
            e.printStackTrace();
        }
        return sendSmsResponse;
    }
}
