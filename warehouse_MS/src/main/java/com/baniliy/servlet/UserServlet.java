package com.baniliy.servlet;

import com.baniliy.bean.User;
import com.baniliy.service.UserManageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    private UserManageService userManageService = new UserManageService();
    // 查询所有用户
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException ,IOException{
        // 设置属性值使jsp切换对应模块
        System.out.println("selectAll-start");
        HttpSession session = request.getSession();
        session.setAttribute("function",0);
        session.setAttribute("SelectAdd",3);
        // 调用服务查询
        System.out.println("selectAll-search");
        List<User> users = userManageService.queryAll();
        System.out.println(users);
        session.setAttribute("users",users);
        System.out.println("selectAll-done");
        request.getRequestDispatcher("/manage.jsp").forward(request,response);
    }
    // 根据类型和关键字模糊查询
    public void select(HttpServletRequest request, HttpServletResponse response) throws ServletException ,IOException{
        // 设置属性值使jsp切换对应模块
        System.out.println("select-start");
        HttpSession session = request.getSession();
        session.setAttribute("function",0);
        session.setAttribute("SelectAdd",3);
        // 获取请求头参数
        String key = request.getParameter("key");
        String op = request.getParameter("op");
        System.out.println("op:"+op+"\tkey:"+key);
        // 调用服务查询
        System.out.println("select-search");
        List<User> users = userManageService.queryByKey(op,key);
        System.out.println(users);
        session.setAttribute("users",users);
        System.out.println("select-done");
        request.getRequestDispatcher("/manage.jsp").forward(request,response);
    }
    // 请求添加用户页面
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException ,IOException{
        // 设置属性值使jsp切换对应模块
        System.out.println("add-start");
        HttpSession session = request.getSession();
        session.setAttribute("function",0);
        session.setAttribute("SelectAdd",4);
        System.out.println("add-done");
        request.getRequestDispatcher("/manage.jsp").forward(request,response);
    }
    // 处理添加用户表单
    public void addForm (HttpServletRequest request, HttpServletResponse response) throws ServletException ,IOException{
        System.out.println("add-form");
        // 获取请求头信心
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        Integer status = Integer.parseInt(request.getParameter("status"));
        Integer authority = Integer.parseInt(request.getParameter("authority"));
        User user = new User(null,username,password,email,status,authority);
        // 调用服务查询
        System.out.println("user-service-add");
        System.out.println(user);
        if (userManageService.add(user)){
            System.out.println("addForm-done");
        }
        else {
            System.out.println("addForm-false");
            request.setAttribute("flag","用户添加失败");
        }
        // 设置属性值使jsp切换对应模块
        HttpSession session = request.getSession();
        session.setAttribute("function",0);
        session.setAttribute("SelectAdd",4);
        request.getRequestDispatcher("/manage.jsp").forward(request,response);
    }
    // 请求修改用户页面（回显用户信息）
    public void update (HttpServletRequest request, HttpServletResponse response) throws ServletException ,IOException{
        System.out.println("update-start");
        // 获取请求头信息
        Integer id = Integer.parseInt(request.getParameter("id"));
        // 调用服务查询
        System.out.println("user-service-update Id:"+id);
        User user = userManageService.query(id);
        request.setAttribute("user1",user);
        System.out.println(user);
        // 设置属性值使jsp切换对应模块
        HttpSession session = request.getSession();
        session.setAttribute("function",0);
        session.setAttribute("SelectAdd",5);
        request.getRequestDispatcher("/manage.jsp").forward(request,response);
    }
    // 处理修改用户信息表单
    public void updateForm (HttpServletRequest request, HttpServletResponse response) throws ServletException ,IOException{
        System.out.println("updateForm-start");
        // 获取请求头信息
        Integer id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        Integer status = Integer.parseInt(request.getParameter("status"));
        Integer authority = Integer.parseInt(request.getParameter("authority"));
        User user = new User(id,username,password,email,status,authority);
        System.out.println(user);
        // 判断修改的用户是否为当前登录用户
        // 获取当前用户信息
        HttpSession session = request.getSession();
        User current_user = (User)session.getAttribute("user");
        System.out.println("当前登录用户为："+current_user);
        // 调用服务
        System.out.println("user-service-updateForm");
        if (userManageService.update(user)){
            System.out.println("user-service-updateForm-done");
            if(Objects.equals(current_user.getId(), id))
            {
                System.out.println("⚠️ 您修改的用户为当前登录用户");
                request.setAttribute("flag","当前用户信息已修改请重新登录！！");
                request.getRequestDispatcher("/login.jsp").forward(request,response);
            }
            else {
                request.getRequestDispatcher("/user/selectAll").forward(request,response);
            }
        }
        else {
            System.out.println("user-service-updateForm-false");
            request.setAttribute("flag","修改用户信息失败");
            request.getRequestDispatcher("/user/selectAll").forward(request,response);
        }
    }
    // 根据ID删除用户
    public void delete (HttpServletRequest request, HttpServletResponse response) throws ServletException ,IOException{
        System.out.println("delete-start");
        // 获取请求头信息
        Integer id = Integer.parseInt(request.getParameter("id"));
        // 调用服务查询
        boolean delete = userManageService.delete(id);
        System.out.println("user-service-delete Id:"+id);
        if(delete){
            System.out.println("delete-done");
        }
        else {
            System.out.println("delete-fail");
        }
        request.getRequestDispatcher("/user/selectAll").forward(request,response);
    }
}
