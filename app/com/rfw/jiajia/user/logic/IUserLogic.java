package com.rfw.jiajia.user.logic;

import com.rfw.jiajia.user.models.User;

/**
 * 
 * @author lizhong.chen
 * @date 2015年2月27日上午11:35:46
 * @description 用户logic
 * @version V1.0
 */
public interface IUserLogic {

    /**
     * 查询用户
     * 
     * @param name
     * @return
     */
    public User selectUser(String name);

    /**
     * 查询用户
     * 
     * @param name
     * @param pwd
     * @return
     */
    public User selectUser(String name, String pwd);

    /**
     * 添加用户
     * 
     * @param user
     * @return
     */
    public long addUser(User user);

    /**
     * 更新用户数据
     * 
     * @param user
     * @return
     */
    public long updateUser(User user);

    /**
     * 删除用户
     * 
     * @param name
     * @return
     */
    public long deleteUser(String name);
}
