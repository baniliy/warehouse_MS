package com.baniliy.service;

import com.baniliy.bean.Material;
import com.baniliy.bean.User;
import com.baniliy.dao.UserDao;

import java.util.List;

public class UserManageService {

    UserDao userDao = new UserDao();

    /*查询数据库中所有用户*/
    public List<User> queryAll(){
        return  userDao.queryAllUsers();
    }
    /*增加用户*/
    public Boolean add(User user){ return userDao.addUser(user) != 0;}
    /*根据ID查找*/
    public User query(Integer id){
        return userDao.queryUserById(id);
    }
    /*根据ID修改*/
    public Boolean update(User user){return userDao.updateUser(user) != 0;}
    /*根据ID查找*/
    public boolean delete(Integer id){return userDao.deleteUserById(id) != 0;}
    /*根据Key查找所有符合匹配项（模糊查询）*/
    public List<User> queryByKey(String k ,String s){
        return userDao.queryUserByKey(k,s);
    }
}
