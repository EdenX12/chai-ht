package com.tian.sakura.video.service.weixin.impl;

import com.tian.sakura.video.service.weixin.WeixinApi;
import com.tian.sakura.video.service.weixin.WeixinGlobalConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * https://developers.weixin.qq.com/doc/offiaccount/OA_Web_Apps/JS-SDK.html
 * @author liuhg
 *
 */


@Service
public class WeixinApiImpl implements WeixinApi {
	@Autowired
	private RestTemplate restTemplate;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//微信普通access_token
	@Override
	public String getCommonTokent(String appid,String secret) throws Exception {
		String accessToken = "";

		String url = WeixinGlobalConstants.WEIXIN_COMMON_ACCESS_TOKEN.concat("grant_type=client_credential").concat("&appid=")
				.concat(appid).concat("&secret=").concat(secret);
		
		ResponseEntity<CommonAccessTokenMessage>  json = restTemplate.getForEntity(url, CommonAccessTokenMessage.class);
		//logger.info(" 微信普通access token " + json.getBody().getAccess_token());

		if (json == null || json.getBody() == null || json.getBody().getErrcode() != null) {
			
			logger.error(json.getBody().getErrcode()+json.getBody().getErrmsg());
			
			throw new Exception(json.getBody().getErrcode()+json.getBody().getErrmsg());
	
		} else {
			accessToken = json.getBody().getAccess_token();
		}

		return accessToken;
	}
	//微信网页授权access_token
	@Override
	public String getPageTokent() throws Exception {
		StringBuilder sb = new StringBuilder(WeixinGlobalConstants.WEIXIN_ACCESS_TOKEN);

		return null;
	}
	//调用微信JS接口的临时票据
	@Override
	public String getJsapiTicket(String appid,String secret) throws Exception {
		String jsapiTicket = "";
		
		//获取普通token 
		String commonAccessToken = getCommonTokent(appid,secret);
		StringBuilder sb = new StringBuilder(WeixinGlobalConstants.JSAPI_TICKE);
		sb.append("type=jsapi&access_token=").append(commonAccessToken);
		//logger.info(sb.toString());
		ResponseEntity<JsapiTicket>  responseEntity  = restTemplate.getForEntity(sb.toString(), JsapiTicket.class);
		
		if (responseEntity  == null || responseEntity.getBody() == null || responseEntity .getBody().getErrcode() == null) {
			
			logger.error(responseEntity .getBody().getErrcode()+responseEntity .getBody().getErrmsg());
			
			throw new Exception(responseEntity .getBody().getErrcode()+responseEntity .getBody().getErrmsg());
	
		} else {
			jsapiTicket = responseEntity .getBody().getTicket();
		}
		
		return jsapiTicket;
	}
	
	//JS-SDK签名
	@Override
	public Map<String, String> jssdkSign(String appid, String secret, String url) throws Exception {
        String jsapiTicket = getJsapiTicket(appid,secret);
        Map<String, String> ret = new HashMap<String, String>();
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String string1;
        String signature = "";

        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapiTicket +
                  "&noncestr=" + nonce_str +
                  "&timestamp=" + timestamp +
                  "&url=" + url;
        //logger.info(string1);

        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        }
        catch (NoSuchAlgorithmException e)
        {
            logger.error("jssdk签名 SHA-1 报错", e);
            throw new Exception("jssdk签名 SHA-1 报错");
        }
        catch (UnsupportedEncodingException e)
        {
            logger.error("jssdk签名 编码报错", e);
            throw new Exception("jssdk签名 编码报错");
        }

        ret.put("url", url);
        ret.put("jsapi_ticket", jsapiTicket);
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);
        //logger.info(jsapiTicket+"=="+nonce_str+"=="+timestamp+"=="+signature);
        return ret;
	}

    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

}
