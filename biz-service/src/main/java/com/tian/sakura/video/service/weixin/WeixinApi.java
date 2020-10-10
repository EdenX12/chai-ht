package com.tian.sakura.video.service.weixin;

import java.util.Map;

public interface WeixinApi {
	//微信普通access_token
	public String getCommonTokent(String appid,String secret) throws Exception;
	
	//微信网页授权access_token
	public String getPageTokent() throws Exception;
	
	//调用微信JS接口的临时票据
	public String getJsapiTicket(String appid,String secret) throws Exception;
	
	//JS-SDK签名
	public Map<String,String> jssdkSign(String appid,String secret,String url) throws Exception; 

}
