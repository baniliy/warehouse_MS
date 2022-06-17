package com.baniliy.servlet;

import com.baniliy.bean.Material;
import com.baniliy.service.MaterialService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "product", value = "/product")
public class ProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 解决乱码
        request.setCharacterEncoding("utf-8");
       // 设置属性值使jsp切换对应模块
       HttpSession session = request.getSession();
       session.setAttribute("function",2);
       session.removeAttribute("SelectAdd");
       // 调用模糊查询服务对物料类型进行查询
        MaterialService materialService = new MaterialService();
        List<Material> A = materialService.queryByKey("kind", "A");
        List<Material> B = materialService.queryByKey("kind", "B");
        List<Material> C = materialService.queryByKey("kind", "C");
        List<Material> D = materialService.queryByKey("kind", "D");
        System.out.println(A);
        System.out.println(B);
        System.out.println(C);
        System.out.println(D);
        request.setAttribute("A",A);
        request.setAttribute("B",B);
        request.setAttribute("C",C);
        request.setAttribute("D",D);
        // 转发显示数据
       request.getRequestDispatcher("/manage.jsp").forward(request,response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
