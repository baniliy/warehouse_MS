package com.baniliy.dao;

import com.baniliy.bean.Material;
import com.baniliy.bean.User;
import com.baniliy.utils.DBUtils1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    /*
    * 添加用户
    * */
    public int addUser(User User) {
        int num=0;
        try {
            Connection conn=DBUtils1.getConnection();;
            PreparedStatement psta = conn.prepareStatement("insert into USER(username,password,email,status,authority) values(?,?,?,?,?)");
            psta.setString(1,User.getUsername());
            psta.setString(2,User.getPassword());
            psta.setString(3,User.getEmail());
            psta.setInt(4,User.getStatus());
            psta.setInt(5,User.getAuthority());
            num=psta.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }

    /*
    * 根据ID删除用户
    * */
    public int deleteUserById(Integer id) {
        int num=0;
        try {
            Connection conn= DBUtils1.getConnection();
            PreparedStatement psta = conn.prepareStatement("delete from USER where id=?");
            psta.setInt(1,id);
            num=psta.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }

    /*
    * 根据ID修改用户信息
    * */
    public int updateUser(User User) {
        int num=0;
        try {
            Connection conn= DBUtils1.getConnection();
            PreparedStatement psta = conn.prepareStatement("update USER set username=?,password=?,email=?,status=? ,authority=? where id=?");
            psta.setString(1,User.getUsername());
            psta.setString(2,User.getPassword());
            psta.setString(3,User.getEmail());
            psta.setInt(4,User.getStatus());
            psta.setInt(5,User.getAuthority());
            psta.setInt(6,User.getId());
            num=psta.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }

    /*
     * 根据用户名、密码和身份码查询用户
     * */
    public User queryUserByUP(User user) {
        User user1=null;
        try {
            Connection conn= DBUtils1.getConnection();
            PreparedStatement psta = conn.prepareStatement("select * from USER where username=? and password=? and authority=?");
            psta.setString(1,user.getUsername());
            psta.setString(2,user.getPassword());
            psta.setInt(3,user.getAuthority());
            ResultSet rs = psta.executeQuery();

            if (rs.next())
            {
                Integer id1=rs.getInt("id");
                String username=rs.getString("username");
                String password=rs.getString("password");
                String email=rs.getString("email");
                Integer status = rs.getInt("status");
                Integer authority = rs.getInt("authority");
                user1=new User(id1,username,password,email,status,authority);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user1;
    }

    /*
    * 根据ID查询用户
    * */
    public User queryUserById(Integer id) {
        User user=null;
        try {
            Connection conn= DBUtils1.getConnection();
            PreparedStatement psta = conn.prepareStatement("select * from USER where id=?");
            psta.setInt(1,id);
            ResultSet rs = psta.executeQuery();

            if (rs.next())
            {
                Integer id1=rs.getInt("id");
                String username=rs.getString("username");
                String password=rs.getString("password");
                String email=rs.getString("email");
                Integer status = rs.getInt("status");
                Integer authority = rs.getInt("authority");
                user=new User(id1,username,password,email,status,authority);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    /*
     * 根据用户名查询用户
     * */
    public User queryUserByUserName(String username) {
        User user=null;
        try {
            Connection conn= DBUtils1.getConnection();
            PreparedStatement psta = conn.prepareStatement("select * from USER where username=?");
            psta.setString(1,username);
            ResultSet rs = psta.executeQuery();

            if (rs.next())
            {
                Integer id1=rs.getInt("id");
                String username1=rs.getString("username");
                String password=rs.getString("password");
                String email=rs.getString("email");
                Integer status = rs.getInt("status");
                Integer authority = rs.getInt("authority");
                user=new User(id1,username1,password,email,status,authority);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    /*
     * 根据关键词模糊查询
     * Param:
     *      k (serial,name,kind,) 种类；
     *      s 具体值；
     * */
    public List<User> queryUserByKey(String k, String s) {
        List<User> users= new ArrayList<>();
        try {
            Connection conn= DBUtils1.getConnection();
            String sql = "select * from USER where "+k+" like ?";
            PreparedStatement psta = conn.prepareStatement(sql);
            psta.setString(1,"%"+s+"%");               //  模糊查询
            System.out.println("\tSQL----------"+psta.toString());
            ResultSet rs = psta.executeQuery();

            while (rs.next()) {
                Integer id1=rs.getInt("id");
                String username1=rs.getString("username");
                String password=rs.getString("password");
                String email=rs.getString("email");
                Integer status = rs.getInt("status");
                Integer authority = rs.getInt("authority");
                User user=new User(id1,username1,password,email,status,authority);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    /*
    * 查询所有用户
    * */
    public List<User> queryAllUsers() {
        List<User> Users = new ArrayList<User>();
        try {
            Connection conn = DBUtils1.getConnection();
            Statement sta = conn.createStatement();
            ResultSet rs = sta.executeQuery("select * from USER");
            int rows = 0;
            while (rs.next()) {
                Integer id1=rs.getInt("id");
                String username=rs.getString("username");
                String password=rs.getString("password");
                String email=rs.getString("email");
                Integer status = rs.getInt("status");
                Integer authority = rs.getInt("authority");
                User user=new User(id1,username,password,email,status,authority);
                Users.add(user);
                rows++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Users;
    }

    /*
    * 查询用户总数
    * */
    public Integer queryTotalCount() {
        int count=0;
        try {
            Connection conn= DBUtils1.getConnection();
            Statement sta = conn.createStatement();
            ResultSet rs=sta.executeQuery("select count(*) from USER");
            if (rs.next())
            {
                count=rs.getInt(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    /*
    * 分页查询
    * */
    public List<User> queryForPageItems(int begin, int pageSize) {
        List<User> Users = new ArrayList<User>();
        try {
            Connection conn = DBUtils1.getConnection();
            PreparedStatement psta = conn.prepareStatement("select * from USER limit ?,?");
            psta.setInt(1,begin);
            psta.setInt(2,pageSize);
            ResultSet rs = psta.executeQuery();
            int rows = 0;
            while (rs.next()) {
                Integer id1=rs.getInt("id");
                String username=rs.getString("username");
                String password=rs.getString("password");
                String email=rs.getString("email");
                Integer status = rs.getInt("status");
                Integer authority = rs.getInt("authority");
                User user=new User(id1,username,password,email,status,authority);
                Users.add(user);
                rows++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Users;
      
    }


    /*public Integer queryForPageTotalCountByPrice(int min, int max) {
        int count=0;
        try {
            Connection conn= DBUtils1.getConnection();
            PreparedStatement psta = conn.prepareStatement("select count(*) from USER where price between ? and ?");
            psta.setInt(1,min);
            psta.setInt(2,max);
            ResultSet rs=psta.executeQuery();
            if (rs.next())
            {
                count=rs.getInt(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
    public List<User> queryForPageItemsByPrice(int begin, int pageSize, int min, int max) {
        List<User> Users = new ArrayList<User>();
        try {
            Connection conn = DBUtils1.getConnection();
            PreparedStatement psta = conn.prepareStatement("select * from USER where price between ? and ? order by price limit ?,?");
            psta.setInt(1,begin);
            psta.setInt(2,pageSize);
            psta.setInt(3,min);
            psta.setInt(4,max);
            ResultSet rs = psta.executeQuery();
            int rows = 0;
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Double price = rs.getDouble("price");
                String author = rs.getString("author");
                Integer sales = rs.getInt("sales");
                Integer stock = rs.getInt("stock");
                String path = rs.getString("img_path");
                User User = new User(id, name, author, price, sales, stock, path);
                Users.add(User);
                rows++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Users;
    }*/
}
