package com.tian.sakura.cdd.common.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

/**
 * 业务工具类
 *
 * @author lvzonggang
 */
public class BizUtils {

    private static final String des_miyao = "olkji675yhn" ;

    /**
     * 加密
     * @param mingwen 明文
     * @return
     */
    public static String encryptByEDS(String mingwen) {
        return DESEncryptUtil.encrypt(mingwen,des_miyao);
    }

    /**
     * 解密
     * @param miwen 密文
     * @return
     */
    public static String decryptByEDS(String miwen) {
        return DESEncryptUtil.decrypt(miwen,des_miyao);
    }

    public static int randomBetween(int min, int max) {
		//创建Random类对象
		Random random = new Random();
		//产生随机数
		int number = random.nextInt(max - min + 1) + min;
		return number;
	}

	/**
	 * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址,
	 *
	 * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
	 * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
	 *
	 * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130,
	 * 192.168.1.100
	 *
	 * 用户真实IP为： 192.168.1.110
	 *
	 * @param request
	 * @return
	 */
	public static String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 随机码，包含大写字母，小写字母和数字
	 *
	 * @param length
	 * @return
	 */
	public static String generatorRandomCode(int length){

		char[] codeSeq = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'M', 'N','O', 'P', 'Q', 'R', 'S', 'T', 'U','V', 'W', 'X', 'Y', 'Z',
				'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
				'0','1','2', '3', '4', '5', '6', '7', '8', '9' };
		Random random = new Random();
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < length; i++) {
			String r = String.valueOf(codeSeq[random.nextInt(codeSeq.length)]);// random.nextInt(10));
			s.append(r);
		}
		return s.toString();
	}

	/**
	 * 随机码，大写字母和数字
	 * @param length
	 * @return
	 */
	public static String generatorRandomCodeWithoutLowercase(int length){

		char[] codeSeq = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'M', 'N','O', 'P', 'Q', 'R', 'S', 'T', 'U','V', 'W', 'X', 'Y', 'Z',
				'0','1','2', '3', '4', '5', '6', '7', '8', '9' };
		Random random = new Random();
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < length; i++) {
			String r = String.valueOf(codeSeq[random.nextInt(codeSeq.length)]);// random.nextInt(10));
			s.append(r);
		}
		return s.toString();
	}

	public static String desensiteMobile(String mobile) {
		if (StringUtils.isEmpty(mobile)) {
			return "";
		}
		StringBuilder sb = new StringBuilder(mobile.substring(0, 4))
				.append("****")
				.append(mobile.substring(mobile.length()-4));
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(desensiteMobile("15900955400"));
	}
}
