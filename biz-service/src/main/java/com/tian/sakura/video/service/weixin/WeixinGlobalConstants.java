package com.tian.sakura.video.service.weixin;

public class WeixinGlobalConstants {
	// 微信网页授权请求
	public static final String WEIXIN_AUTHORIZEURL = "https://open.weixin.qq.com/connect/oauth2/authorize?";
	// 微信网页授权access_token
	public static final String WEIXIN_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token?";
	// 微信用户详细信息
	public static final String WEIXIN_USER_INFO = "https://api.weixin.qq.com/cgi-bin/user/info?";
	// 微信普通access_token
	public static final String WEIXIN_COMMON_ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?";
	//调用微信JS接口的临时票据
	public static final String JSAPI_TICKE ="https://api.weixin.qq.com/cgi-bin/ticket/getticket?";
	//微信公众号APPID [需要改成从数据库获取]
	public static final String APP_ID ="wx40057da55312a236";
	//微信公众号secret [需要改成从数据库获取]
	public static final String APP_SECRET ="80444be7d55af6a48bac54970fd37974";

}
