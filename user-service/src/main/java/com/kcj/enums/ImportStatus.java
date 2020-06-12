package com.kcj.enums;
/**
 * 导入记录的状态
 * @author 枚举
 *
 */
public enum ImportStatus {
	importing(0,"导入中"),success(1,"成功"),fail(-1,"失败"),deleteing(-2,"删除中"),deleteSuccess(2,"删除成功");

	private int value;

	private String desc;

	public int getValue() {
		return value;
	}

	public String getDesc() {
		return desc;
	}

	private ImportStatus(int value, String desc) {
		this.value = value;
		this.desc = desc;
	}

}
