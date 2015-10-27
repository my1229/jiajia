package com.rfw.jiajia.mail.logic;

public interface IUserRegister {

	/**
	 * 注册邮箱验证
	 */
	public Boolean sendRegisterEmail(String userName, String emailAddr);

}
