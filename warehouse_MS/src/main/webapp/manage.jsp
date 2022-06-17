<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>管理页面</title>
    <link rel="icon" href="<%=request.getContextPath()%>/img/logo.svg" type="image/x-icon">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/manage.css" />
    <%String flag = (String) request.getAttribute("flag");                      //**************************************提示窗
        if(flag!=null){%><script>alert("<%=flag%>")</script><%}%>
</head>
<body>
<div class="container">
    <c:if test="${!(sessionScope.SelectAdd==0)}">             <%-- 0：主页状态，背景不显示--%>
        <img
            src="<%=request.getContextPath()%>/img/undraw_financial_data_re_p0fl.svg"
            alt=""
            class="background-img"
        />
    </c:if>
    <%------------------------------------------------ 导航栏 --------------------------------------------------%>
    <header class="header">
        <div class="topnav clearfix">
            <li class="menu-con">
                <!-- <a href="#">LOGO</a> -->
                <img src="<%=request.getContextPath()%>/img/BANILIY.svg" alt=""/>
            </li>
            <li>
                <a href="<%=request.getContextPath()%>/material/selectAll">物料管理</a>
                <div class="submenu">
                    <div><a href="<%=request.getContextPath()%>/material/selectAll">查询</a></div>
                    <div><a href="<%=request.getContextPath()%>/material/selectAll">修改</a></div>
                    <div><a href="<%=request.getContextPath()%>/material/add">添加</a></div>
                </div>
            </li>
            <li>
                <a href="<%=request.getContextPath()%>/product">产品模型</a>
                <div class="submenu">
                    <div>待开发</div>
                    <div>待开发</div>
                    <div>待开发</div>
                </div>
            </li>
            <c:if test="${sessionScope.user.authority==0}">
            <li>
                <a href="<%=request.getContextPath()%>/user/selectAll">人员管理</a>
                <div class="submenu">
                    <div><a href="<%=request.getContextPath()%>/user/selectAll">查询</a></div>
                    <div><a href="<%=request.getContextPath()%>/user/selectAll">修改</a></div>
                    <div><a href="<%=request.getContextPath()%>/user/add">添加</a></div>
                </div>
            </li>
            </c:if>
            <c:if test="${sessionScope.user.authority==1}">
            <li><a href="#"></a><div class="submenu"></div></li>
            </c:if>
            <li>
                <a href="#"><img src="<%=request.getContextPath()%>/img/ellipsis.svg" alt=""/></a>
                <div class="submenu">
                    <div><a href="<%=request.getContextPath()%>/logoutServlet">登出</a></div>
                    <div>待开发</div>
                    <div>待开发</div>
                </div>
            </li>
            <li>
                <a href="#"><img src="<%=request.getContextPath()%>/img/user.svg" alt=""/></a>
                <div class="submenu">
                    <c:if test="${sessionScope.user.authority==0}">
                        <p><img src="<%=request.getContextPath()%>/img/admin.svg" alt=""/>&nbsp;&nbsp;管理权限</p>
                        <div>管理员</div>
                    </c:if>
                    <c:if test="${sessionScope.user.authority==1}">
                        <p><img src="<%=request.getContextPath()%>/img/admin.svg" alt=""/>&nbsp;&nbsp;管理权限</p>
                        <div>职员</div>
                    </c:if>
                    <p><img src="<%=request.getContextPath()%>/img/username.svg" alt=""/>&nbsp;&nbsp;当前用户</p>
                    <div>${sessionScope.user.username}</div>
                    <p><img src="<%=request.getContextPath()%>/img/mail.svg" alt=""/>&nbsp;&nbsp;邮箱地址</p>
                    <div>&nbsp;&nbsp;${sessionScope.user.email}&nbsp;&nbsp;</div>
                </div>
            </li>
            <li>
                <a href="#"><img src="<%=request.getContextPath()%>/img/search.svg" alt=""/></a>
            </li>
        </div>
    </header>
    <%------------------------------------------------ main --------------------------------------------------%>
    <div class="main">
        <span class="block"></span>
        <%------------------------------------------------ 功能菜单 --------------------------------------------------%>
        <c:if test="${sessionScope.function==0||sessionScope.function==1}">
        <div class="function-menu">
            <c:if test="${sessionScope.function==1}">  <%-- 物料管理侧边栏 --%>
                <div><a href="<%=request.getContextPath()%>/material/selectAll">物料查询</a></div>
                <div><a href="<%=request.getContextPath()%>/material/selectAll">信息修改</a></div>
                <div><a href="<%=request.getContextPath()%>/material/add">物料添加</a></div>
            </c:if>
            <c:if test="${sessionScope.function==0}">  <%-- 用户管理侧边栏 --%>
                <div><a href="<%=request.getContextPath()%>/user/selectAll">用户查询</a></div>
                <div><a href="<%=request.getContextPath()%>/user/selectAll">信息修改</a></div>
                <div><a href="<%=request.getContextPath()%>/user/add">用户添加</a></div>
            </c:if>
        </div>
        </c:if>
        <%------------------------------------------------ 物料查询 --------------------------------------------------%>
        <c:if test="${sessionScope.SelectAdd==1}">
        <div class="table-area index-class">
            <form class="item-search" method="post" action="<%=request.getContextPath()%>/material/select">
                <img src="<%=request.getContextPath()%>/img/view-list.svg" alt=""/>
                <p>物料查询</p>
                <input type="submit" value="搜索" />
                <input type="search" name="key" placeholder="请输入关键词"/>
                <input type="hidden" value="false" name="all">   <%-- 添加隐藏项，告知服务器提供关键词搜索而非查询所有 --%>
                <div>
                    类型
                    <select name="op">
                        <option value="serial"><p>编号</p></option>
                        <option value="name"><p>名称</p></option>
                        <option value="kind"><p>所属类型</p></option>
                    </select>
                </div>
            </form>
            <div>
                <table class="table" cellspacing="0">
                    <tr>
                        <td> </td>
                        <td>编号</td>
                        <td>名称</td>
                        <td>所属类型</td>
                        <td>库存数量</td>
                        <td>存放位置</td>
                        <td>更新时间</td>
                        <td>状态</td>
                        <td colspan="2">操作</td>
                    </tr>
                    <c:forEach items="${sessionScope.materials}" var="material" varStatus="s">
                        <tr>
                            <td>${s.count}</td>
                            <td>${material.serial}</td>
                            <td>${material.name}</td>
                            <td>${material.kind}</td>
                            <td>${material.num}</td>
                            <td>${material.location}</td>
                            <td>${material.updatetime}</td>
                            <td>${material.status}</td>
                            <td><a href="<%=request.getContextPath()%>/material/update?id=${material.id}"><input type="button" value="修改" /></a></td>
                            <td><a href="<%=request.getContextPath()%>/material/delete?id=${material.id}"><input type="button" value="删除" /></a></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
            <div class="paginaction">
                <div>共计 ${requestScope.pageNum} 页 &rAtail; 第 ${requestScope.page1+1} 页</div>
                <c:forEach begin="1" end="${requestScope.pageNum}" var="p">
                    <div onclick="window.open('<%=request.getContextPath()%>/material/${sessionScope.action}?page=${p-1}','_self')">
                        <c:out value="${p}"/>
                    </div>
                </c:forEach>
            </div>
        </c:if>
        <%------------------------------------------------ 物料添加(修改) ---------------------------------------------%>
        <c:if test="${sessionScope.SelectAdd==2}">
        <div class="add-area index-class">
            <c:if test="${requestScope.echo==1}">
                <div class="item-title">
                    <img src="<%=request.getContextPath()%>/img/update.svg" alt="">
                    <p>物料修改</p>
                </div>
                <form action="<%=request.getContextPath()%>/material/updateForm" method="post" class="add-form">
                    <input type="hidden" name="id" value="${requestScope.material.id}" />
                    <table>
                        <tr>
                            <td>编&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号</td>
                            <td><input type="text" name="serial" value="${requestScope.material.serial}"/></td>
                            <td>格式“2022xxxxx”，例：“20220528”</td>
                        </tr>
                        <tr>
                            <td> 名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称 </td>
                            <td> <input type="text" name="name" value="${requestScope.material.name}"/></td>
                            <td> </td>
                        </tr>
                        <tr>
                            <td>所属类型</td>
                            <td><input type="text" name="kind" value="${requestScope.material.kind}"/></td>
                            <td>格式X,X，例：“A,B,C”</td>
                        </tr>
                        <tr>
                            <td>库存数量</td>
                            <td><input type="text" name="num" value="${requestScope.material.num}"/></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>存放位置</td>
                            <td>
                                <input type="text" name="location" value="${requestScope.material.location}"/>
                            </td>
                            <td>格式“x#xxx”，例：“8#116”</td>
                        </tr>
                    </table>
                    <input type="submit" value="提交">
                    <input type="reset" value="重置">
                </form>
                </c:if>
            <c:if test="${requestScope.echo!=1}">
                <div class="item-title">
                    <img src="<%=request.getContextPath()%>/img/add.svg" alt="">
                    <p>物料添加</p>
                </div>
                <form action="<%=request.getContextPath()%>/material/addForm" method="post" class="add-form">
                    <table>
                    <tr>
                        <td>编&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号</td>
                        <td><input type="text" name="serial" /></td>
                        <td>格式“2022xxxxx”，例：“20220528”</td>
                    </tr>
                    <tr>
                        <td> 名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称 </td>
                        <td> <input type="text" name="name"/></td>
                        <td> </td>
                    </tr>
                    <tr>
                        <td>所属类型</td>
                        <td><input type="text" name="kind"/></td>
                        <td>格式X,X，例：“A,B,C”</td>
                    </tr>
                    <tr>
                        <td>库存数量</td>
                        <td><input type="text" name="num"/></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>存放位置</td>
                        <td>
                            <input type="text" name="location"/>
                        </td>
                        <td>格式“x#xxx”，例：“8#116”</td>
                    </tr>
                </table>
                    <input type="submit" value="提交">
                    <input type="reset" value="重置">
                </form>
            </c:if>
        </div>
        </c:if>
        <%------------------------------------------------ 欢迎界面 --------------------------------------------------%>
        <c:if test="${sessionScope.SelectAdd==0}">
            <div class="hello">
                <p>你好,Baniliy!</p>
                <img src="<%=request.getContextPath()%>/img/undraw_welcoming_re_x0qo.svg" alt="">
            </div>
        </c:if>
        <%-------------------------------------------- 用户管理----------------------------------------------%>
        <c:if test="${sessionScope.function==0}">
            <%-------------------------------------------- 用户管理查询----------------------------------------------%>
            <c:if test="${sessionScope.SelectAdd==3}">
                <div class="user-table-area index-class">
                    <form class="user-search" method="post" action="<%=request.getContextPath()%>/user/select">
                        <img src="<%=request.getContextPath()%>/img/view-list.svg" alt="" />
                        <p>用户查询</p>
                        <input type="submit" value="搜索" />
                        <input type="search" name="key" placeholder="请输入关键词"/>
<%--                        <input type="hidden" value="false" name="all">--%>
                        <div>
                            类型
                            <!-- <img src="img/chevron-down.svg" alt=""> -->
                            <select name="op">
                                <option value="username"><p>用户名</p></option>
                                <option value="email"><p>邮箱</p></option>
<%--                                <option value="status"><p>状态</p></option>--%>
                            </select>
                        </div>
                    </form>
                    <div>
                        <table class="user-table" cellspacing="0">
                            <tr>
                                <td ></td>
                                <td>用户名</td>
                                <td>邮箱</td>
                                <td>状态</td>
                                <td>权限</td>
                                <td colspan="2">操作</td>
                            </tr>
                            <c:forEach items="${sessionScope.users}" var="u" varStatus="s">
                                <tr>
                                    <td >${s.count}</td>
                                    <td>${u.username}</td>
                                    <td>${u.email}</td>
                                    <c:if test="${u.status==0}"><td>禁用</td></c:if>
                                    <c:if test="${u.status==1}"><td>启用</td></c:if>
                                    <c:if test="${u.authority==0}"><td>管理员</td></c:if>
                                    <c:if test="${u.authority==1}"><td>职员</td></c:if>
                                    <td><a href="<%=request.getContextPath()%>/user/update?id=${u.id}"><input type="button" value="修改" /></a></td>
                                    <td><a href="<%=request.getContextPath()%>/user/delete?id=${u.id}"><input type="button" value="删除" /></a></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </c:if>
            <%-------------------------------------------- 用户管理添加----------------------------------------------%>
            <c:if test="${sessionScope.SelectAdd==4}">
                <div class="user-add-area index-class">
                    <div class="user-title">
                        <img src="<%=request.getContextPath()%>/img/add.svg" alt="" />
                        <p>用户添加</p>
                    </div>
                    <form action="<%=request.getContextPath()%>/user/addForm" method="post" class="user-add-form">
                        <input type="hidden"/>
                        <table>
                            <tr>
                                <td>用&nbsp;户&nbsp;名</td>
                                <td><input type="text" name="username"/></td>
                                <td>格式至少5位字母或数字，例：“admin0”</td>
                            </tr>
                            <tr>
                                <td>
                                    密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码
                                </td>
                                <td><input type="text" name="password" value="baniliy"/></td>
                                <td>格式至少6位字符，默认"baniliy"</td>
                            </tr>
                            <tr>
                                <td>
                                    邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱
                                </td>
                                <td><input type="text" name="email" /></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态</td>
                                <td>
                                    <select name="status">
                                        <option value="1">启用</option>
                                        <option value="0">禁用</option>
                                    </select></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>权&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;限</td>
                                <td>
                                    <select name="authority">
                                        <option value="1">职员</option>
                                        <option value="0">管理员</option>
                                    </select></td>
                                <td></td>
                            </tr>
                        </table>
                        <input type="submit" value="提交" />
                        <input type="reset" value="重置" />
                    </form>
                </div>
            </c:if>
            <%-------------------------------------------- 用户管理修改----------------------------------------------%>
            <c:if test="${sessionScope.SelectAdd==5}">
                <div class="user-add-area index-class">
                    <div class="user-title">
                        <img src="<%=request.getContextPath()%>/img/update.svg" alt="" />
                        <p>用户修改</p>
                    </div>
                    <form action="<%=request.getContextPath()%>/user/updateForm" method="post" class="user-add-form">
                        <input type="hidden" name="id" value="${requestScope.user1.id}" />
                        <table>
                            <tr>
                                <td>用&nbsp;户&nbsp;名</td>
                                <td><input type="text" name="username" value="${requestScope.user1.username}"/></td>
                                <td>格式至少5位字母或数字，例：“admin0”</td>
                            </tr>
                            <tr>
                                <td>
                                    密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码
                                </td>
                                <td><input type="text" name="password" value="${requestScope.user1.password}"/></td>
                                <td>格式至少6位字符，默认"baniliy"</td>
                            </tr>
                            <tr>
                                <td>
                                    邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱
                                </td>
                                <td><input type="text" name="email" value="${requestScope.user1.email}"/></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态</td>
                                <td>
                                    <select name="status">
                                        <c:if test="${requestScope.user1.status==1}">
                                            <option value="1" selected>启用</option>
                                            <option value="0">禁用</option>
                                        </c:if>
                                        <c:if test="${requestScope.user1.status==0}">
                                            <option value="1">启用</option>
                                            <option value="0" selected>禁用</option>
                                        </c:if>
                                    </select></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>权&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;限</td>
                                <td>
                                    <select name="authority">
                                        <c:if test="${requestScope.user1.authority==1}">
                                            <option value="1" selected>职员</option>
                                            <option value="0">管理员</option>
                                        </c:if>
                                        <c:if test="${requestScope.user1.authority==0}">
                                            <option value="1">职员</option>
                                            <option value="0"  selected>管理员</option>
                                        </c:if>
                                    </select></td>
                                <td></td>
                            </tr>
                        </table>
                        <input type="submit" value="提交" />
                        <input type="reset" value="重置" />
                    </form>
                </div>
            </c:if>
        </c:if>
        <%-------------------------------------------- 产品模型----------------------------------------------%>
        <c:if test="${sessionScope.function==2}">
            <div class="product-table-area index-class" >
                <table class="product-table" cellspacing="0">
                    <tr>
                        <td>产品名称</td>
                        <td>所需物料</td>
                    </tr>
                    <tr>
                        <td >A</td>
                        <td>
                            <c:forEach items="${requestScope.A}" var="p">
                                <div onclick="window.open('<%=request.getContextPath()%>/updateServlet?id=${p.id}&UP=false','_self')">
                                    <p>${p.serial}</p>
                                    <p>${p.name}</p>
                                </div>
                            </c:forEach>
                        </td>
                    </tr>
                    <tr>
                        <td >B</td>
                        <td>
                            <c:forEach items="${requestScope.B}" var="p">
                                <div onclick="window.open('<%=request.getContextPath()%>/updateServlet?id=${p.id}&UP=false','_self')">
                                    <p>${p.serial}</p>
                                    <p>${p.name}</p>
                                </div>
                            </c:forEach>
                        </td>
                    </tr>
                    <tr>
                        <td >C</td>
                        <td>
                            <c:forEach items="${requestScope.C}" var="p">
                                <div onclick="window.open('<%=request.getContextPath()%>/updateServlet?id=${p.id}&UP=false','_self')">
                                    <p>${p.serial}</p>
                                    <p>${p.name}</p>
                                </div>
                            </c:forEach>
                        </td>
                    </tr>
                    <tr>
                        <td >D</td>
                        <td>
                            <c:forEach items="${requestScope.D}" var="p">
                                <div onclick="window.open('<%=request.getContextPath()%>/updateServlet?id=${p.id}&UP=false','_self')">
                                    <p>${p.serial}</p>
                                    <p>${p.name}</p>
                                </div>
                            </c:forEach>
                        </td>
                    </tr>
                </table>
            </div>
        </c:if>
    </div>
<script src="<%=request.getContextPath()%>/javaScript/manage.js"></script>
</body>
</html>

