package controllers;

import org.apache.commons.lang.StringUtils;

import com.rfw.common.utils.DateUtils;
import com.rfw.common.utils.MD5Util;
import com.rfw.common.utils.UnicodeUtil;
import com.rfw.jiajia.mail.logic.IUserRegister;
import com.rfw.jiajia.mail.logic.impl.UserRegister;
import com.rfw.jiajia.user.constant.UserStatus;
import com.rfw.jiajia.user.logic.IUserLogic;
import com.rfw.jiajia.user.logic.impl.UserLogic;
import com.rfw.jiajia.user.models.User;

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
	private static IUserRegister userRegister = UserRegister.getInstance();

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

		pwd = MD5Util.MD5(pwd);

		int status = UserStatus.UN_VERIFIED.getStatus();

		user = new User(userName, pwd, email, status, StringUtils.EMPTY, 0L);
		long ret = userLogic.addUser(user);
		if (ret > 0) {
			// TODO MailUtil.sendMail(email, mailTitle, mailConcept);

			userRegister.sendRegisterEmail(userName, email);

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

	public static void verification(String userName, String session) {

		userName = UnicodeUtil.revert(userName);
		User user = userLogic.selectUser(userName);
		Long now = System.currentTimeMillis();

		if (user == null) {
			renderJSON("验证失败，请先注册！");
		}
		Long validateTime = user.getValidateTime() == null ? 0 : user.getValidateTime();

		if (now - validateTime > DateUtils.DAY_MILLIS) {
			renderJSON("验证链接已过期，请重新验证！");
		}

		boolean result = userLogic.verification(user, session);

		if (result) {
			user.setStatus(user.getStatus() | UserStatus.VERIFIED.getStatus());
			if (userLogic.updateUser(user) > 0) {
				renderJSON("验证成功！");
			}
		}
		renderJSON("验证失败，请重新验证！");

	}

}
