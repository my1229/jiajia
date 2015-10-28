package com.rfw.jiajia.mail.logic;

import com.rfw.jiajia.user.models.User;

public interface IUserRegister {

    /**
     * 注册邮箱验证
     */
    public Boolean sendRegisterEmail(User user, String emailAddr);

}
