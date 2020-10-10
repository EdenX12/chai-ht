package com.tian.sakura.cdd.common.util;

import com.tian.sakura.cdd.common.id.DefaultIdGenerator;

import java.util.UUID;

/**
 * 生成uuid工具类（基于twitter snowflake）
 *
 *
 * @author id
 */
public class IdGenUtil {

	private IdGenUtil() {

	}

	public static String generateId(String prefix, Boolean timestampFlag) {
		return DefaultIdGenerator.getInstance().genUUID(prefix,timestampFlag);
	}
	
	public static String generateId(Boolean timestampFlag) {
		return DefaultIdGenerator.getInstance().genUUID("",timestampFlag);
	}

	/**
	 * 前缀不带时间戳
	 * @return
	 */
	public static String generateId() {
		return DefaultIdGenerator.getInstance().genUUID("",false);
	}

	public static String uuid(){
		return UUID.randomUUID().toString().replaceAll("-","");
	}

}
