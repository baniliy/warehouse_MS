/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2022-06-13 11:13:09 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<!--\r\n");
      out.write("    author：Baniliy\r\n");
      out.write("-->\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"zh-CN\">\r\n");
      out.write("\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"UTF-8\" />\r\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\r\n");
      out.write("    <script src=\"https://kit.fontawesome.com/64d58efce2.js\" crossorigin=\"anonymous\"></script>\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath());
      out.write("/css/Login.css\" />\r\n");
      out.write("    <link rel=\"icon\" href=\"./img/logo.svg\" type=\"image/x-icon\">\r\n");
      out.write("    <title>登录注册</title>\r\n");
      out.write("    ");

        String flag = (String) request.getAttribute("flag");
        if(flag!=null){
    
      out.write("\r\n");
      out.write("        <script>alert(\"");
      out.print(flag);
      out.write("\")</script>\r\n");
      out.write("    ");

        }
    
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("<div class=\"container\">\r\n");
      out.write("    <div class=\"forms-container\">\r\n");
      out.write("        <div class=\"signin-signup\">\r\n");
      out.write("            <form method=\"post\" action=\"");
      out.print(request.getContextPath());
      out.write("/loginServlet\" class=\"sign-in-form\">\r\n");
      out.write("                <h2 class=\"title\">登录</h2>\r\n");
      out.write("                <div class=\"input-field\">\r\n");
      out.write("                    <i class=\"fas fa-user\"></i>\r\n");
      out.write("                    <input type=\"text\" name=\"username\" placeholder=\"用户名\" />\r\n");
      out.write("                    <p class=\"tips-false\">用户名输入有误</p>\r\n");
      out.write("                    <img src=\"./img/true.svg\" alt=\"\" class=\"icon-tips\"/>\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"input-field\">\r\n");
      out.write("                    <i class=\"fas fa-lock\"></i>\r\n");
      out.write("                    <input type=\"password\" name=\"password\" placeholder=\"密码\" />\r\n");
      out.write("                    <p class=\"tips-false\">密码输入有误</p>\r\n");
      out.write("                    <img src=\"./img/true.svg\" alt=\"\" class=\"icon-tips\" />\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"input-field\">\r\n");
      out.write("                    <label class=\"input-clerk\">\r\n");
      out.write("                        <input type=\"radio\" name=\"authority\" class=\"radio-status-clerk\" value=\"1\" checked />\r\n");
      out.write("                        <span class=\"clerk\">职员</span>\r\n");
      out.write("                    </label>\r\n");
      out.write("                    <img src=\"./img/矩形.svg\" alt=\"\" class=\"rectangle\" />\r\n");
      out.write("                    <label class=\"input-admin\">\r\n");
      out.write("                        <input type=\"radio\" name=\"authority\" value=\"0\" class=\"radio-status-admin\" />\r\n");
      out.write("                        <span class=\"admin\">管理员</span>\r\n");
      out.write("                    </label>\r\n");
      out.write("                </div>\r\n");
      out.write("                <input type=\"submit\" value=\"立即登录\" class=\"btn solid\" />\r\n");
      out.write("            </form>\r\n");
      out.write("            <form action=\"");
      out.print(request.getContextPath());
      out.write("/registerServlet\" method=\"post\" class=\"sign-up-form\">\r\n");
      out.write("                <h2 class=\"title\">注册</h2>\r\n");
      out.write("                <div class=\"input-field\">\r\n");
      out.write("                    <i class=\"fas fa-user\"></i>\r\n");
      out.write("                    <input type=\"text\" placeholder=\"用户名\" name=\"username\"/>\r\n");
      out.write("                    <p class=\"tips-false\">用户名输入有误</p>\r\n");
      out.write("                    <img src=\"./img/true.svg\" alt=\"\" class=\"icon-tips\" />\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"input-field\">\r\n");
      out.write("                    <i class=\"fas fa-envelope\"></i>\r\n");
      out.write("                    <input type=\"email\" placeholder=\"邮箱\" name=\"email\"/>\r\n");
      out.write("                    <p class=\"tips-false\">邮箱输入有误</p>\r\n");
      out.write("                    <img src=\"./img/true.svg\" alt=\"\" class=\"icon-tips\" />\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"input-field\">\r\n");
      out.write("                    <i class=\"fas fa-lock\"></i>\r\n");
      out.write("                    <input type=\"password\" placeholder=\"密码\" name=\"password\"/>\r\n");
      out.write("                    <p class=\"tips-false\">密码输入有误</p>\r\n");
      out.write("                    <img src=\"./img/true.svg\" alt=\"\" class=\"icon-tips\" />\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"input-field\">\r\n");
      out.write("                    <label class=\"input-clerk\">\r\n");
      out.write("                        <input type=\"radio\" name=\"authority\" class=\"radio-status-clerk\" value=\"1\" checked />\r\n");
      out.write("                        <span class=\"clerk\">职员</span>\r\n");
      out.write("                    </label>\r\n");
      out.write("                    <img src=\"./img/矩形.svg\" alt=\"\" class=\"rectangle\" />\r\n");
      out.write("                    <label class=\"input-admin\">\r\n");
      out.write("                        <input type=\"radio\" name=\"authority\" class=\"radio-status-admin\" value=\"0\" />\r\n");
      out.write("                        <span class=\"admin\">管理员</span>\r\n");
      out.write("                    </label>\r\n");
      out.write("                </div>\r\n");
      out.write("                <input type=\"submit\" class=\"btn\" value=\"立即注册\" />\r\n");
      out.write("            </form>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("    <div class=\"title-header\">\r\n");
      out.write("        <!-- <p>Baniliy</p>\r\n");
      out.write("            <p>仓库管理系统</p> -->\r\n");
      out.write("        <img src=\"./img/title.svg\" alt=\"\" />\r\n");
      out.write("    </div>\r\n");
      out.write("    <div class=\"title-end\">\r\n");
      out.write("        <p>AUTHOR | YQM <br />CONTACT | Baniliy@163.com</p>\r\n");
      out.write("    </div>\r\n");
      out.write("    <div class=\"panels-container\">\r\n");
      out.write("        <div class=\"panel left-panel\">\r\n");
      out.write("            <div class=\"content\">\r\n");
      out.write("                <h3>加入我们</h3>\r\n");
      out.write("                <p>加入我们，成为本站的一份子。</p>\r\n");
      out.write("                <button class=\"btn transparent\" id=\"sign-up-btn\">去注册</button>\r\n");
      out.write("            </div>\r\n");
      out.write("            <img src=\"./img/log.svg\" class=\"image\" alt=\"\" />\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"panel right-panel\">\r\n");
      out.write("            <div class=\"content\">\r\n");
      out.write("                <h3>已有帐号？</h3>\r\n");
      out.write("                <p>立即登录已有帐号，进入仓管系统。</p>\r\n");
      out.write("                <button class=\"btn transparent\" id=\"sign-in-btn\">去登录</button>\r\n");
      out.write("            </div>\r\n");
      out.write("            <img src=\"./img/register.svg\" class=\"image\" alt=\"\" />\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("</div>\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath());
      out.write("/javaScript/Login.js\"></script>\r\n");
      out.write("</body>\r\n");
      out.write("\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}