package com.baniliy.servlet;

import com.baniliy.bean.User;
import com.baniliy.service.LoginService;
import com.baniliy.service.RegisterService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "registerServlet", value = "/registerServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取表单参数参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        int status = Integer.parseInt(request.getParameter("status"));
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setStatus(status);
        System.out.println(user);
        // 调用登录服务验证用户
        RegisterService registerService = new RegisterService();
        if(!registerService.register(user))
        {
            System.out.println("注册失败");
            request.setAttribute("flag","❌ 注册失败");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
        else {
            System.out.println("注册成功");
            request.setAttribute("flag","👻 注册成功");
            request.getRequestDispatcher("/login.jsp").forward(request,response);

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
