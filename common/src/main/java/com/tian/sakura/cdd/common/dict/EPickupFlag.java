package com.tian.sakura.cdd.common.dict;

/**
 * 客户自提货场景 0-未提货， 1- 已提货
 * 
 * @author liuhg
 *
 */
public enum EPickupFlag {
	NOT_PICKUP(0, "未取货"), HAS_PICKEDUP(1, "已取货");
	EPickupFlag(int code, String name) {
		this.code = code;
		this.name = name;
	}

	private int code;
	private String name;

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public static String getNameByCode(Integer code) {
		EPickupFlag[] values = EPickupFlag.values();
		for (EPickupFlag pickupFlag : values) {
			if (pickupFlag.getCode() == code) {
				return pickupFlag.getName();
			}
		}
		return "未知";
	}
}
