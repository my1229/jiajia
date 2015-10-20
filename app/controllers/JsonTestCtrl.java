package controllers;

import java.util.Date;

import play.mvc.Controller;
import rfw.app.demo.models.User;
import rfw.common.utils.JsonUtil;

public class JsonTestCtrl extends Controller {

    public static void josnTest() {
        User user = new User();
        user.setAddTs(new Date());
        user.setUpdateTs(new Date());
        user.setDescr("描述");
        user.setName("小明");
        user.setPwd("aaa");

        String userjson = JsonUtil.getJson(user);

        System.out.println(userjson);

        User user1 = JsonUtil.toObject(userjson, User.class);

        System.out.println(user1.getName());

        renderJSON(JsonUtil.getJson(user1));
    }
}
