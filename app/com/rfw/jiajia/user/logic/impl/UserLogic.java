package com.rfw.jiajia.user.logic.impl;

import com.rfw.jiajia.user.dao.impl.UserDaoImpl;
import com.rfw.jiajia.user.dao.interfaces.IUserDao;
import com.rfw.jiajia.user.logic.IUserLogic;
import com.rfw.jiajia.user.models.User;

/**
 * 
 * @author lizhong.chen
 * @date 2015年2月27日上午11:38:14
 * @description 用户logic
 * @version V1.0
 */
public class UserLogic implements IUserLogic {

    private static IUserDao userDao = UserDaoImpl.getInstance();

    private UserLogic() {
    }

    private static class UserLogicHolder {
        private static IUserLogic instance = new UserLogic();
    }

    public static IUserLogic getInstance() {
        return UserLogicHolder.instance;
    }

    @Override
    public User selectUser(String name) {
        return userDao.selectByName(name);
    }

    @Override
    public User selectUser(String name, String pwd) {
        return userDao.selectByNameAndPwd(name, pwd);
    }

    @Override
    public long addUser(User user) {
        return userDao.insert(user);
    }

    @Override
    public long updateUser(User user) {
        return userDao.updateById(user);
    }

    @Override
    public long deleteUser(String name) {
        return userDao.deleteByName(name);
    }

}
