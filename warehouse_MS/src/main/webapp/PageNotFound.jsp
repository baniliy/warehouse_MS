<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <style>
        /* 设置本地字体 */
        @font-face {
            font-family: 'PingFang';
            src:url('./fonts/PingFang Medium.ttf')
        }
        body{
            font-family: "PingFang",Medium;
            font-size: 16px;
        }
        .main{
            width: 100%;
            height: 100%;
            text-align: center;
            position: relative;
        }
        .main img{
            width: auto;
            height: auto;
            padding-top: 10%;
        }
        .main p{
            /* border: 1px solid #ddd; */
            width: auto;
            height: 50px;
            line-height: 50px;
            position: absolute;
            top: -12px;
            left: 145px;
            color: #7c7c7c9c;
        }
        .main a{
            /* border: 1px solid #ddd; */
            width: 125px;
            height: 40px;
            line-height: 40px;
            position: absolute;
            top: 8px;
            left: 6px;
            background: #5995fc9f;
            /* border-radius: 4px; */
            color: white;
            text-decoration: none;
            cursor: pointer;
            box-shadow: 0px 6px 24px -1px rgb(0 0 0 / 41%);
        }
        .main a::after{
            content: " ";
            background: #5995fc;
            height: 40px;
            position: absolute;
            top: 0;
            left: 0;
            z-index: -1;
            transition: all 1.1s;
            width: 0;
        }
        .main a:hover::after{
            width: 125px;
        }
    </style>
</head>
<body>
<%--<h2>Hello World!</h2>--%>
<div class="main">
    <p>抱歉无可用资源</p>
    <a href="<%=request.getContextPath()%>/login.jsp">尝试返回登录</a>
    <img src="./img/undraw_page_not_found_re_e9o6.svg" alt="">
</div>
</body>
</html>
