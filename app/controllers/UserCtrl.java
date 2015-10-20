package controllers;

import java.util.ArrayList;
import java.util.List;

import jiajia.app.demo.logic.IUserLogic;
import jiajia.app.demo.logic.impl.UserLogic;
import jiajia.app.demo.models.Requie;
import jiajia.app.demo.models.Requies;
import jiajia.app.demo.models.User;
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
    public static void register(String name, String pwd) {
        User user = userLogic.selectUser(name);
        if (user != null) {
            renderJSON("注册失败，用户名已存在！");
        }

        user = new User(name, pwd);
        long ret = userLogic.addUser(user);
        if (ret > 0) {
            renderJSON("注册成功，用户名:" + name);
        }
        renderJSON("注册失败！");
    }

    /**
     * 登陆
     * 
     * @param name
     * @param pwd
     */
    public static void login(String name, String pwd) {
        User user = userLogic.selectUser(name, pwd);
        if (user != null) {
            renderJSON("登陆成功");
        }

        renderJSON("登陆失败，用户名或密码错误！");
    }

    public static void testJson() {
        List<Requie> rlist = new ArrayList<Requie>();
        rlist.add(new Requie("年龄", "大于20岁"));
        rlist.add(new Requie("性别", "男"));
        Requies r = new Requies();

        r.setRequies(rlist);
        renderJSON(r);
    }
}
