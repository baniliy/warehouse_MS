package com.baniliy.servlet;

import com.baniliy.bean.User;
import com.baniliy.service.LoginService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "loginServlet", value = "/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取表单参数参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int authority = Integer.parseInt(request.getParameter("authority"));
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setAuthority(authority);
        System.out.println(user);
        // 调用登录服务验证用户
        LoginService loginService = new LoginService();
        User current_user = loginService.login(user);
        System.out.println(user);
        if(current_user==null)
        {
            System.out.println("登录失败");
            request.setAttribute("flag","⛔ 登录失败");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
        else {
            // 判断当期登录用户是否激活
            if(current_user.getStatus()==1){
                System.out.println("登录成功");
                HttpSession session = request.getSession();
                session.setAttribute("user",current_user);
                session.setAttribute("function",1);
                session.setAttribute("SelectAdd",0);
                request.getRequestDispatcher("/manage.jsp").forward(request,response);
    //            request.getRequestDispatcher("/index.jsp").forward(request,response);
            }
            else {
                System.out.println("登录失败");
                request.setAttribute("flag","⛔ 抱歉当前用户未激活，请等待管理员认证激活");
                request.getRequestDispatcher("/login.jsp").forward(request,response);
            }

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
