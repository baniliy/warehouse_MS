<!--
    author：Baniliy
-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <script src="https://kit.fontawesome.com/64d58efce2.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/Login.css" />
    <title>登录注册</title>
    <%
        String flag = (String) request.getAttribute("flag");
        if(flag!=null){
    %>
        <script>alert("<%=flag%>")</script>
    <%
        }
    %>
</head>

<body>
<div class="container">
    <div class="forms-container">
        <div class="signin-signup">
            <form method="post" action="<%=request.getContextPath()%>/loginServlet" class="sign-in-form">
                <h2 class="title">登录</h2>
                <div class="input-field">
                    <i class="fas fa-user"></i>
                    <input type="text" name="username" placeholder="用户名" />
                    <p class="tips-false">用户名输入有误</p>
                    <img src="<%=request.getContextPath()%>/img/true.svg" alt="" class="icon-tips"/>
                </div>
                <div class="input-field">
                    <i class="fas fa-lock"></i>
                    <input type="password" name="password" placeholder="密码" />
                    <p class="tips-false">密码输入有误</p>
                    <img src="<%=request.getContextPath()%>/img/true.svg" alt="" class="icon-tips" />
                </div>
                <div class="input-field">
                    <label class="input-clerk">
                        <input type="radio" name="status" class="radio-status-clerk" value="1" checked />
                        <span class="clerk">职员</span>
                    </label>
                    <img src="<%=request.getContextPath()%>/img/矩形.svg" alt="" class="rectangle" />
                    <label class="input-admin">
                        <input type="radio" name="status" value="0" class="radio-status-admin" />
                        <span class="admin">管理员</span>
                    </label>
                </div>
                <input type="submit" value="立即登录" class="btn solid" />
            </form>
            <form action="<%=request.getContextPath()%>/registerServlet" method="post" class="sign-up-form">
                <h2 class="title">注册</h2>
                <div class="input-field">
                    <i class="fas fa-user"></i>
                    <input type="text" placeholder="用户名" name="username"/>
                    <p class="tips-false">用户名输入有误</p>
                    <img src="<%=request.getContextPath()%>/img/true.svg" alt="" class="icon-tips" />
                </div>
                <div class="input-field">
                    <i class="fas fa-envelope"></i>
                    <input type="email" placeholder="邮箱" name="email"/>
                    <p class="tips-false">邮箱输入有误</p>
                    <img src="<%=request.getContextPath()%>/img/true.svg" alt="" class="icon-tips" />
                </div>
                <div class="input-field">
                    <i class="fas fa-lock"></i>
                    <input type="password" placeholder="密码" name="password"/>
                    <p class="tips-false">密码输入有误</p>
                    <img src="<%=request.getContextPath()%>/img/true.svg" alt="" class="icon-tips" />
                </div>
                <div class="input-field">
                    <label class="input-clerk">
                        <input type="radio" name="status" class="radio-status-clerk" value="1" checked />
                        <span class="clerk">职员</span>
                    </label>
                    <img src="<%=request.getContextPath()%>/img/矩形.svg" alt="" class="rectangle" />
                    <label class="input-admin">
                        <input type="radio" name="status" class="radio-status-admin" value="0" />
                        <span class="admin">管理员</span>
                    </label>
                </div>
                <input type="submit" class="btn" value="立即注册" />
            </form>
        </div>
    </div>
    <div class="title-header">
        <!-- <p>Baniliy</p>
            <p>仓库管理系统</p> -->
        <img src="<%=request.getContextPath()%>/img/title.svg" alt="" />
    </div>
    <div class="title-end">
        <p>AUTHOR | YQM <br />CONTACT | Baniliy@163.com</p>
    </div>
    <div class="panels-container">
        <div class="panel left-panel">
            <div class="content">
                <h3>加入我们</h3>
                <p>加入我们，成为本站的一份子。</p>
                <button class="btn transparent" id="sign-up-btn">去注册</button>
            </div>
            <img src="<%=request.getContextPath()%>/img/log.svg" class="image" alt="" />
        </div>
        <div class="panel right-panel">
            <div class="content">
                <h3>已有帐号？</h3>
                <p>立即登录已有帐号，进入仓管系统。</p>
                <button class="btn transparent" id="sign-in-btn">去登录</button>
            </div>
            <img src="<%=request.getContextPath()%>/img/register.svg" class="image" alt="" />
        </div>
    </div>
</div>
<script src="<%=request.getContextPath()%>/javaScript/Login.js"></script>
</body>

</html>