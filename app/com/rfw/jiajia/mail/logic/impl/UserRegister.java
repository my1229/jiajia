package com.rfw.jiajia.mail.logic.impl;

import com.rfw.common.utils.MailUtil;
import com.rfw.jiajia.mail.logic.IUserRegister;

public class UserRegister implements IUserRegister {

	@Override
	public Boolean sendRegisterEmail(String userName, String emailAddr) {

		String mailTitle = "会员邮箱认证--来自加加";
		String url = "www.baidu.com";

		String mailConcept = "您好：" + userName + "\n\t您正在加加进行邮箱验证，请点击您的验证链接 " + url + " 完成注册。"
				+ "\n\t为确保您能正常收到系统发的通知，我们强烈推荐您将folway@qq.com加进您的邮箱的白名单或通讯录。\n\t若有不清楚的地方，请咨询我司在线客服，或致电：18768184438，感谢您的支持！";

		// String mailConcept = "<div id=\"mailContentContainer\" class=\"qmbox
		// qm_con_body_content\">您好：" + "userName"
		// + "<br>&nbsp;&nbsp; 您正在加加进行邮箱验证，您的邮箱链接是：" + "<a href='" + url +
		// "'>点击" + url + "完成注册</a>"
		// + "<br>&nbsp;&nbsp; 为确保您能正常收到系统发的通知，我们强烈推荐您将<a
		// href=\"mailto:folway@qq.com\"
		// target=\"_blank\">folway@qq<wbr>.com</a>加进您的邮箱的白名单或通讯录。<br>若有不清楚的地方，请咨询我司在线客服，或致电：<span
		// style=\"border-bottom:1px dashed #ccc;z-index:1\" t=\"7\"
		// onclick=\"return false;\"
		// data=\"18768184438\">18768184438</span>，感谢您的支持！<br><br><br></div>";

		MailUtil.sendMail(emailAddr, mailTitle, mailConcept);

		return null;
	}

	public static void main(String[] args) {
		new UserRegister().sendRegisterEmail("hahahah", "folway@qq.com");
	}

}
