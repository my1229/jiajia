package controllers;

import java.util.Date;

import jiajia.app.demo.models.User;
import jiajia.common.utils.JsonUtil;
import play.mvc.Controller;

public class JsonTestCtrl extends Controller {

	public static void josnTest() {
		User user = new User();
		user.setAddTs(new Date());
		user.setUpdateTs(new Date());
		user.setUserName("小明");
		user.setPwd("aaa");

		String userjson = JsonUtil.getJson(user);

		System.out.println(userjson);

		User user1 = JsonUtil.toObject(userjson, User.class);

		System.out.println(user1.getUserName());

		renderJSON(JsonUtil.getJson(user1));
	}
}
