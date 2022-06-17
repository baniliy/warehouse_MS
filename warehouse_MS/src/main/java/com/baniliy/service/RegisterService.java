package com.baniliy.service;

import com.baniliy.bean.User;
import com.baniliy.dao.UserDao;

public class RegisterService {

    public boolean register(User user){
        UserDao userDao = new UserDao();
        if(userDao.queryUserByUserName(user.getUsername())==null){  // 用户名唯一
            userDao.addUser(user);
            System.out.println("用户已添加");
            return true;
        }
        else {
            System.out.println("用户名已存在");
            return false;
        }
    }
}
