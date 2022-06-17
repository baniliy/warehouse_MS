<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<h2>Hello World!</h2>
<%--<c:if test="true">--%>
<%--    <h3>ttttttt</h3>--%>
<%--</c:if>--%>
<%--    <div>${SelectAdd}</div>--%>
<%--<div>${sessionScope.user.username}</div>--%>
<a href="<%=request.getContextPath()%>/login.jsp">登录页面测试</a>
<a href="<%=request.getContextPath()%>/testServlet">用户管理页面测试</a>
<form method="post" action="<%=request.getContextPath()%>/loginServlet" name="">
    <input type="text" value="admin0" name="username">
    <input type="text" value="123" name="password">
    <input type="text" value="0" name="authority">
    <input type="submit" value="测试">
</form>
</body>
</html>
