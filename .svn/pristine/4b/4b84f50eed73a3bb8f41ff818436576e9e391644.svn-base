package com.tian.sakura.cdd.common.util;

import com.tian.sakura.cdd.common.exception.BizRuntimeException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;

public class DESEncryptUtil {
	
	private static SecretKeyFactory keyFactory = null;
	
	private static Cipher cipher = null;
	static {
		try {
			keyFactory = SecretKeyFactory.getInstance("DES");
		} catch (Exception e) {
			keyFactory = null;
		}
		try {
			cipher = Cipher.getInstance("DES");
		} catch (Exception e) {
			cipher = null;
		}
	}

	/**
	 * DES加密
	 * 
	 * @param data
	 * @param password
	 * @return
	 */
	public static String encrypt(String data, String password) {
		if (data == null || data.isEmpty()) {
			throw new IllegalArgumentException("data-参数不能为空");
		}
		try {
			SecureRandom random = new SecureRandom();
			DESKeySpec desKey = new DESKeySpec(password.getBytes());
			// 创建一个密匙工厂，然后用它把DESKeySpec转换成
			if (keyFactory == null) {
				keyFactory = SecretKeyFactory.getInstance("DES");
			}
			SecretKey securekey = keyFactory.generateSecret(desKey);
			// Cipher对象实际完成加密操作
			if (cipher == null) {
				cipher = Cipher.getInstance("DES");
			}
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
			// 执行加密操作
			return ByteStringConvertUtil.byte2HexStr(cipher.doFinal(data.getBytes()));
		} catch (Exception e) {
			throw new BizRuntimeException("998","DES加密失败!",e);
		}
	}

	public static String decrypt(String data, String password) {
		if (data == null || data.isEmpty()) {
			return data;
		}
		try {
			// DES算法要求有一个可信任的随机数源
			SecureRandom random = new SecureRandom();
			// 创建一个DESKeySpec对象
			DESKeySpec desKey = new DESKeySpec(password.getBytes());
			// 创建一个密匙工厂
			if (keyFactory == null) {
				keyFactory = SecretKeyFactory.getInstance("DES");
			}
			// 将DESKeySpec对象转换成SecretKey对象
			SecretKey securekey = keyFactory.generateSecret(desKey);
			// Cipher对象实际完成解密操作
			if (cipher == null) {
				cipher = Cipher.getInstance("DES");
			}
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.DECRYPT_MODE, securekey, random);
			// 真正开始解密操作
			return new String(cipher.doFinal(ByteStringConvertUtil.hexStr2Byte(data)));
		} catch (Exception e) {
			throw new BizRuntimeException("998","DES解密失败!",e);
		}
	}
	
	public static void main(String[] args){
		String a = DESEncryptUtil.encrypt("12345","3c348ec817");
		System.out.println(a);
	}
}
