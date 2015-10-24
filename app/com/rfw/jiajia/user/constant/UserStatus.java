package com.rfw.jiajia.user.constant;

public enum UserStatus {

	UN_VERIFIED(1, "没有验证", "用户没有经过邮箱验证"),

	VERIFIED(2, "已经验证", "用户通过邮箱验证");

	/**
	 * 用户状态
	 */
	private Integer status;

	/**
	 * 状态描述
	 */
	private String statusDesc;

	/**
	 * 状态详细描述
	 */
	private String detailedDesc;

	private UserStatus(Integer status, String statusDesc, String detailedDesc) {
		this.status = status;
		this.statusDesc = statusDesc;
		this.detailedDesc = detailedDesc;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public String getDetailedDesc() {
		return detailedDesc;
	}

	public void setDetailedDesc(String detailedDesc) {
		this.detailedDesc = detailedDesc;
	}

}
