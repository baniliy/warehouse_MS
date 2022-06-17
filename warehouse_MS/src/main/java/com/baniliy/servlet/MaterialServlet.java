package com.baniliy.servlet;

import com.baniliy.bean.Material;
import com.baniliy.service.MaterialService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/material/*")
public class MaterialServlet extends BaseServlet {
//    private UserManageService userManageService = new UserManageService();
    private MaterialService materialService = new MaterialService();
    int max_item = 9; //  一页显示的最大数目
    int page = 0; // 当前第几页，初始默认第一页

    // 查询所有用户
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException ,IOException{
        // 设置属性值使jsp切换对应模块
        System.out.println("selectAll-start");
        HttpSession session = request.getSession();
        session.setAttribute("function",1);
        session.setAttribute("SelectAdd",1);
        session.setAttribute("action","selectAll");
        // 获取参数
        if (request.getParameter("page")!=null){    // 判断是否是初次请求
            page = Integer.parseInt(request.getParameter("page"));
        }
        // 调用服务查询
        System.out.println("selectAll-search");
        int count = materialService.queryTotalCount();
        int pageNum = (count%max_item==0) ?  count/max_item: (count/max_item)+1; // 计算需要分多少页
        request.setAttribute("pageNum",pageNum); // 页数
        request.setAttribute("page1",page); // 当前第几页
        System.out.println("count->"+count+"\tpageNum->"+pageNum+"\tpage->"+page);
//        request.setAttribute("pages",count);
        /*(0-8,9-17,18-26)
        * Start | num
        *  0   9
        *  9   9
        *
        * */
        List<Material> materials = materialService.queryForPage(max_item*page, max_item);
        System.out.println(materials);
        session.setAttribute("materials",materials);
        System.out.println("selectAll-done");
        request.getRequestDispatcher("/manage.jsp").forward(request,response);
    }
    // 根据类型和关键字模糊查询
    public void select(HttpServletRequest request, HttpServletResponse response) throws ServletException ,IOException{
        // 设置属性值使jsp切换对应模块
        System.out.println("selectAll-start");
        HttpSession session = request.getSession();
        session.setAttribute("function",1);
        session.setAttribute("SelectAdd",1);
        session.setAttribute("action","select");
        // 获取参数
        String key;
        String op;
        if (request.getParameter("page")!=null){  // 判断是否是初次请求,不是说明需要获取页数和参数
            page = Integer.parseInt(request.getParameter("page"));
            key = (String) session.getAttribute("key");
            op = (String) session.getAttribute("op");
        }
        else {   // 第一次请求将保留的上次查询的类型和关键词，便于下次翻页的时候获取参数
            key = request.getParameter("key");
            op = request.getParameter("op");
            session.setAttribute("key",key);
            session.setAttribute("op",op);
        }
        // 调用服务查询
        System.out.println("selectAll-search");
        System.out.println("op:"+op+"\tkey:"+key);
        int count = materialService.queryTotalCountByKey(op,key);
        int pageNum = (count%max_item==0) ?  count/max_item: (count/max_item)+1; // 计算需要分多少页
        request.setAttribute("pageNum",pageNum); // 页数
        request.setAttribute("page1",page); // 当前第几页
        System.out.println("count->"+count+"\tpageNum->"+pageNum+"\tpage->"+page);
//        request.setAttribute("pages",count);
        /*(0-8,9-17,18-26)
         * Start | num
         *  0   9
         *  9   9
         * */
        List<Material> materials = materialService.queryForPageByKey(op,key,max_item*page, max_item);
        System.out.println(materials);
        session.setAttribute("materials",materials);
        System.out.println("selectAll-done");
        request.getRequestDispatcher("/manage.jsp").forward(request,response);
    }
    // 请求添加用户页面
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException ,IOException{
        // 获取表单参数参数(判断是请求加载添加页面还是请求添加数据)
        String add = request.getParameter("add");
        System.out.println("addServlet-add-false");
        // 设置属性值使jsp切换对应模块
        HttpSession session = request.getSession();
        session.setAttribute("function",1);
        session.setAttribute("SelectAdd",2);
        // 转发请求添加页面
        System.out.println("addServlet-manage.jsp-done");
        request.getRequestDispatcher("/manage.jsp").forward(request,response);
    }
    // 处理添加用户表单
    public void addForm (HttpServletRequest request, HttpServletResponse response) throws ServletException ,IOException{
        System.out.println("addServlet-add-false");
        String serial = request.getParameter("serial");
        String name = request.getParameter("name");
        String kind = request.getParameter("kind");
        Integer num = Integer.parseInt(request.getParameter("num"));
        String location = request.getParameter("location");
        // 获取当前时间并转换格式
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd");
        String updatetime=simpleDateFormat.format(new Date());
        Material material = new Material(null,serial,name,kind,num,location,updatetime,0);
        // 调用添加数据服务并判断操作是否成功
//           AddService addService = new AddService();
        MaterialService materialService = new MaterialService();
        Boolean aBoolean = materialService.add(material);
//           Boolean aBoolean = addService.add(material);
        if(aBoolean){
            // 提示数据添加成功
            request.setAttribute("flag","👻数据添加成功！");
            // 转发请求添加页面
            System.out.println("addServlet-add-done");
            request.getRequestDispatcher("/manage.jsp").forward(request,response);
        }
        else {
            // 提示数据添加错误
            request.setAttribute("flag","❌数据添加错误！\n您输入的编号已存在");
            // 转发请求添加页面
            System.out.println("addServlet-add-fail");
            request.getRequestDispatcher("/manage.jsp").forward(request,response);
        }
    }
    // 请求修改用户页面（回显用户信息）
    public void update (HttpServletRequest request, HttpServletResponse response) throws ServletException ,IOException{
        // 获取表单参数参数(根据唯一识别编号)
        String up = request.getParameter("UP");
        Integer id = Integer.parseInt(request.getParameter("id"));
        System.out.println("updateServlet-update-"+id);
        MaterialService materialService = new MaterialService();
        request.setAttribute("echo",1);  // 数据回显
//            QueryService queryService = new QueryService();
        Material material = materialService.query(id);
//            Material material = queryService.query(id);
        request.setAttribute("material",material);
        System.out.println("queryService-"+id+"done");
        // 设置属性值使jsp切换对应模块
        HttpSession session = request.getSession();
        session.setAttribute("function",1);
        session.setAttribute("SelectAdd",2);
        // 转发请求添加页面
        System.out.println("updateServlet-manage.jsp-done");
        request.getRequestDispatcher("/manage.jsp").forward(request,response);
    }
    // 处理修改用户信息表单
    public void updateForm (HttpServletRequest request, HttpServletResponse response) throws ServletException ,IOException{
        System.out.println("updateServlet-update-true");
        Integer id = Integer.parseInt(request.getParameter("id"));
        String serial = request.getParameter("serial");
        String name = request.getParameter("name");
        String kind = request.getParameter("kind");
        Integer num = Integer.parseInt(request.getParameter("num"));
        String location = request.getParameter("location");
        // 获取当前时间并转换格式
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String updatetime=simpleDateFormat.format(new Date());
        Material material = new Material(id,serial,name,kind,num,location,updatetime,2);
//            UpdateService updateService = new UpdateService();
        Boolean update = materialService.update(material);
//            Boolean update = updateService.update(material);
        if(update){
            System.out.println("updateServlet-update-done");
        }
        else {
            System.out.println("updateServlet-update-fail");
            request.setAttribute("flag","❌修改失败，提示注意编号唯一性");
        }
        request.getRequestDispatcher("/queryServlet?all=true").forward(request,response);
    }
    // 根据ID删除用户
    public void delete (HttpServletRequest request, HttpServletResponse response) throws ServletException ,IOException{
        // 获取表单参数参数(根据唯一识别编号)
        Integer id = Integer.parseInt(request.getParameter("id"));
        System.out.println("deleteServlet-delete-"+id);
        // 调用删除服务
        MaterialService materialService = new MaterialService();
        boolean delete = materialService.delete(id);
//        DeleteService deleteService = new DeleteService();
//        boolean delete = deleteService.delete(id);
        if(delete){
            System.out.println("deleteServlet-delete-done");
        }
        else {
            System.out.println("deleteServlet-delete-fail");
        }
        // 转发请求查询页面
        request.getRequestDispatcher("/queryServlet?all=true").forward(request,response);
    }
}
