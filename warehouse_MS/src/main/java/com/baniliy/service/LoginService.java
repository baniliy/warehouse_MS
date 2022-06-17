package com.baniliy.service;

import com.baniliy.bean.User;
import com.baniliy.dao.UserDao;

public class LoginService {

    /*完成登录验证*/
    public User login(User user){
        UserDao userDao = new UserDao();

        return userDao.queryUserByUP(user);
    }
}
