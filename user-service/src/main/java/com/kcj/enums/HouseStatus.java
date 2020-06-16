package com.kcj.enums;

/**
 * 房档类型
 */
public enum HouseStatus {

	/**
	 * 未售空置
	 */
	CHECKIN(1, "未售空置"),
	
	/**
	 * 已入住
	 */
	VACANCY(2, "已入住"),
	
	/**
	 * 已售未收
	 */
	UNSOLD(3, "已售未收"),
	
	/**
	 * 已收未装
	 */
	HAVE_NOT_BEEN_INSTALLED(4, "已收未装"),
	
	/**
	 * 已装修
	 */
	DECORATED(5, "已装修"),
	/**
	 * 已售空置
	 */
	SOLDVACANT(6,"已售空置"),
	
	/**
	 * 未售已租
	 */
	UNSOLDRENTED(7,"未售已租");
	private Integer code;
	private String name;

	private HouseStatus(Integer code, String name) {
		this.code = code;
		this.name = name;
	}

	public Integer getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public static HouseStatus from(Integer code) throws Exception {
		switch (code) {
		case 1:
			return CHECKIN;
		case 2:
			return VACANCY;
		case 3:
			return UNSOLD;
		case 4:
			return HAVE_NOT_BEEN_INSTALLED;
		case 5:
			return DECORATED;
		case 6:
			return SOLDVACANT;
		case 7:
			return UNSOLDRENTED;
		default:
			throw new Exception("code错误");
		}
	}
	
	public static HouseStatus get(String name) {
		for(HouseStatus s:HouseStatus.values()) {
			if(s.getName().equals(name)) {
				return s;
			}
		}
		return CHECKIN;
	}
}
