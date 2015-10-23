package controllers;

import org.apache.commons.lang.StringUtils;

import jiajia.app.demo.logic.IUserLogic;
import jiajia.app.demo.logic.impl.UserLogic;
import jiajia.app.demo.models.User;
import jiajia.common.utils.MD5Util;
import play.mvc.Controller;

/**
 * 
 * @author lizhong.chen
 * @date 2015年2月27日上午11:42:18
 * @description 用户交互接口
 * @version V1.0
 */
public class UserCtrl extends Controller {

	private static IUserLogic userLogic = UserLogic.getInstance();

	/**
	 * 注册
	 * 
	 * @param name
	 * @param pwd
	 */
	public static void register(String userName, String pwd, String email) {

		if (StringUtils.isBlank(userName)) {
			renderJSON("请输入用户名...");
		}
		if (StringUtils.isBlank(pwd)) {
			renderJSON("请输入密码...");
		}
		if (StringUtils.isBlank(email)) {
			renderJSON("请输入邮箱...");
		}

		User user = userLogic.selectUser(userName);
		if (user != null) {
			renderJSON("注册失败，用户名已存在！");
		}

		boolean isVerification = false;

		pwd = MD5Util.MD5(pwd);

		user = new User(userName, pwd, email, isVerification);
		long ret = userLogic.addUser(user);
		if (ret > 0) {
			// TODO MailUtil.sendMail(email, mailTitle, mailConcept);
			renderJSON("请登录您的邮箱：" + email + "进行最后的验证...");
		}
		renderJSON("注册失败！");
	}

	/**
	 * 登陆
	 * 
	 * @param userName
	 * @param pwd
	 */
	public static void login(String userName, String pwd) {

		if (StringUtils.isBlank(userName)) {
			renderJSON("请输入用户名...");
		}

		if (StringUtils.isBlank(pwd)) {
			renderJSON("请输入密码...");
		}

		userName = userName.trim();
		pwd = MD5Util.MD5(pwd);

		User user = userLogic.selectUser(userName, pwd);
		if (user != null) {
			renderJSON("登陆成功");
		}

		renderJSON("登陆失败，用户名或密码错误！");
	}

}
