## 仓库管理系统V1.0（无框架）
> [本项目开源地址](https://github.com/baniliy/warehouse_MS)
>
> 素材说明
>
> - [SVG图片素材](https://undraw.co/illustrations)
> - [Master GO素材库](https://www.mastergo.com/)和[阿里巴巴图标库](https://www.iconfont.cn/?spm=a313x.7781069.1998910419.d4d0a486a)
> - 字体苹方

### 需求分析

1. 登录注册模块完成不同身份(管理员/职员)的用户注册和登录验证
   - 注册的用户需要管理员授权才可完成登录，否则不具备登录条件
   - 不同用户身份权限设置(数据库中用户属性配置)
2. 管理模块完成物料的增删改查以及产品模型管理
   -  分页查询
   - 关键词检索(分页)
3. 管理员拥有的用户管理权限(用户的增删改查)
   - 管理员对新用户授权
   - 关键词检索
4. 管理模块可以展示登录用户的信息、注销当前用户并上述2的功能切换
   - 登录后需将登录用户的信息从数据库中查询出并保存到会话
   - 注销时需要将会话中将登录用户清除
   - 导航栏对不同功能模块的切换(EL标签)

该WEB工程通过MVC框架，以业务模型、交互界面、控制器三层分层开发。

### 前端

> 数据获取：`EL`表达式和`JSTL`标签

#### 登录注册（login.jsp）

**登录表单**：

1. 通过JavaScript监听文本输入控件(input:text)内容，当检测到输入框文本发生变化(change)时触发服务函数通过正则表达式匹配自动检测并提示输入文本是否符合格式要求(预设的用户名、密码格式)，以达到登录用户名和密码的格式约束；
2. 通过单选输入控件(input:radio)完成设置登录身份选择
3. 通过提交按钮(button:submit)完成表单提交
4. 提交方式post，响应地址`/loginServlet`

**注册表单**：

1. 同上通过JavaScript监听文本输入控件(input:text)内容，当检测到输入框文本发生变化(change)时触发服务函数通过正则表达式匹配自动检测并提示输入文本是否符合格式要求(预设的用户名、密码、邮箱格式)，以达到登录用户名、密码以及邮箱的格式约束；
2. 通过单选输入控件(input:radio)完成设置登录身份选择
3. 通过提交按钮(button:submit)完成表单提交
4. 提交方式post，响应地址`/registerServlet`



#### 额外细节说明

**消息提示弹窗(所有提示窗口)：**

​	可以用如下简单的方式通过在jsp文件中注入少量的Java代码来实现接收请求头里的提示消息并以弹窗的形式展示，在相应的servlet转发时只需在`flag`属性下赋值即可在jsp中进行不同提示信息的展示。

```java
request.setAttribute("flag","⛔ 登录失败");
request.getRequestDispatcher("/login.jsp").forward(request,response);
```


```jsp
<%
	String flag = (String) request.getAttribute("flag");
	if(flag!=null){
%>
	<script>alert("<%=flag%>")</script>
<%
    }
%>
```

相关包导入坐标

```xml
  <dependency>
      <groupId>jstl</groupId>
      <artifactId>jstl</artifactId>
      <version>1.2</version>
  </dependency>
  <dependency>
      <groupId>taglibs</groupId>
      <artifactId>standard</artifactId>
      <version>1.1.2</version>
  </dependency>
```

在JSP页面上引入JSTL标签库
```jsp
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
```

**登录和注册表单切换**

​	表单之间的切换主要通过JavaScript配合CSS来实现。CSS样式上对外层大容器设置溢出隐藏(overflow: hidden;)，然后在JavaScript文件中，当监听到去注册按钮点击时，将会对容器的类列表添加指定的类属性(完成盒子的平移)，当监听到去登录按钮点击时，会在容器的类列表移除上述添加的类属性(盒子恢复原来的位置)。

```js
sign_up_btn.addEventListener("click", () => {   // 点击去注册
  container.classList.add("sign-up-mode");
});
sign_in_btn.addEventListener("click", () => {   // 点击去登录
  container.classList.remove("sign-up-mode");
});
```



#### **数据管理（manage.jsp）**

**功能模块切换(布局切换控制)**

​	在管理页面中包含所有功能，通过在servlet内根据当前所需的功能将状态值(对应表见下图)保存到session内，然后在通过EL表达式和JSP标准标签库(JSTL)，完成在前端不同功能模块的呈现。

```
以下键值对在session中，控制jsp页面布局样式实现不同模块的切换

function    0   用户管理
function   	1	物料管理
function   	2	产品模型

SelectAdd 	0	欢迎页面
SelectAdd	1 	物料搜索
SelectAdd	2 	物料添加、物料信息修改（echo=1数据回显）
SelectAdd	3 	用户管理查询
SelectAdd	4 	用户管理添加
SelectAdd	5 	用户管理修改

user.status 0	管理员 
user.status 1	职员	用户管理功能关闭
```

![jsp主框架](C:\Users\23858\Desktop\warehouse_MS图集\jsp主框架.png)

**分页查询**

​	首先通过JSTL标签和EL表达式完成对session内传递的对象进行数据展示，再根据页数(pageNum)完成对页码按钮的生成。其中pageNum是由数据库查询统计的总数count通过运算得到(int pageNum = (count%max_item==0) ?  count/max_item: (count/max_item)+1; // 计算需要分多少页)，具体做法是通过三目运算让总数除每页每页最多数目取余，若整除则直接返回总数与每页最多数目相除的结果，否则在原结果的基础上加一返回。调用分页查询服务，传递参数开始的索引值即每页最多数目乘以当前页码(max_item*page, max_item)以及每页最多数目max_item。jsp部分代码和效果图见下图

```jsp
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
<div class="paginaction">
	<div>共计 ${requestScope.pageNum} 页 &rAtail; 第 ${requestScope.page1+1} 页</div>
	<c:forEach begin="1" end="${requestScope.pageNum}" var="p">
		<div onclick="window.open('<%=request.getContextPath()%>/material/${sessionScope.action}?page=${p-1}','_self')">
		<c:out value="${p}"/>
		</div>
	</c:forEach>
</div>
```

![分页列表](C:\Users\23858\Desktop\warehouse_MS图集\分页列表.png)




### 数据库

> MYSQL数据库5.7.24<br>
> DRUID连接池1.0.9<br>

#### SQL文件

`baniliy`数据库创建，`user`用户表创建和数据添加


```mysql
########################################### 用户管理表
-- 创建数据库
CREATE DATABASE baniliy;

-- 删除user表
DROP TABLE
IF
	EXISTS USER;
	
-- 创建用户表
CREATE TABLE USER (
	id INT PRIMARY KEY auto_increment,
	username VARCHAR ( 20 ) UNIQUE,
	PASSWORD VARCHAR ( 32 ),
	email VARCHAR ( 32 ),
	STATUS INT DEFAULT 0 ,
  authority INT DEFAULT null
	);

-- 添加数据
INSERT INTO USER ( username, PASSWORD, email, STATUS , authority )
VALUES
	( 'admin0', '123', 'admin0@baniliy.com', 1 ,0),
	( 'user0', '234', 'user0@baniliy.com', 1, 1 ),
	( 'user1', '234', 'user1@baniliy.com', 0, 1 );

-- 查询所有数据
SELECT
	* 
FROM
	USER;
```

`material`物料表创建和数据添加

```sql
########################################### 物料管理表
-- 删除表
DROP TABLE
IF
	EXISTS material;
	
-- 创建用户表
CREATE TABLE material (
	id INT PRIMARY KEY auto_increment,
	serial VARCHAR ( 20 ) UNIQUE,
	name VARCHAR ( 32 ),
	kind VARCHAR ( 32 ),
	num int ( 32 ),
	location VARCHAR ( 32 ),
	updatetime VARCHAR ( 32 ),
	STATUS INT DEFAULT NULL 
	);

-- 添加数据
INSERT INTO material ( serial, name, kind, num , location , updatetime , STATUS)
VALUES
	( 202205001, '基本部件1', 'A,B', 100 , "8#116" ,"20220528", 1),
	( 202205002, '基本部件2', 'A,C', 120 , "1#116" ,"20220528", 1),
	( 202205003, '基本部件3', 'B,C,D', 120 , "2#116" ,"20220528", 0),
	( 202205004, '基本部件4', 'A,B,C,D', 140 , "3#116" ,"20220529", 0),
	( 202205005, '基本部件5', 'B,D', 150 , "4#116" ,"20220529", 1),
	( 202205006, '基本零件1', 'A,B', 100 , "8#116" ,"20220528", 1),
	( 202205007, '基本配件2', 'A,C', 120 , "1#116" ,"20220528", 1),
	( 202205008, '基本零件3', 'B,C,D', 120 , "2#116" ,"20220528", 0),
	( 202205009, '基本零件4', 'A,B,C,D', 140 , "3#116" ,"20220529", 0),
	( 202205010, '基本零件5', 'B,D', 150 , "4#116" ,"20220529", 1),
	( 202205011, '基本配件1', 'A,B', 100 , "8#116" ,"20220528", 1),
	( 202205012, '基本配件2', 'A,C', 120 , "1#116" ,"20220528", 1),
	( 202205013, '基本零件3', 'B,C,D', 120 , "2#116" ,"20220528", 0),
	( 202205014, '基本零件4', 'A,B,C,D', 140 , "3#116" ,"20220529", 0),
	( 202205015, '基本零件5', 'B,D', 150 , "4#116" ,"20220529", 1),
	( 202205016, '核心部件1', 'B,D', 150 , "4#116" ,"20220529", 1),
	( 202205017, '核心部件2', 'A,B', 100 , "8#116" ,"20220528", 1),
	( 202205018, '核心部件3', 'A,C', 120 , "1#116" ,"20220528", 1),
	( 202205019, '核心部件4', 'B,C,D', 120 , "2#116" ,"20220528", 0),
	( 202205020, '核心部件5', 'A,B,C,D', 140 , "3#116" ,"20220529", 0),
	( 202205021, '核心部件6', 'B,D', 150 , "4#116" ,"20220529", 1);

-- 查询所有数据
SELECT
	* 
FROM
	material;
```



### bean层

​	完成对模型结构定义，并提供相应的函数接口。该项目中规定了物料和用户两个模型，便于后续数据打包和调用。其中物料属性和用户属性如下所示

**物料模型**

- 编号
- 名称
- 所属类型（生产某类产品的物料）
- 库存数量
- 存放位置
- 更新时间
- 状态

**用户模型**

- 编号
- 用户名
- 登录密码
- 邮箱
- 状态（账户是否激活）
- 权限 （管理员或普通用户）



### dao层

​	规定获取相应的数据接口函数，通过执行sql语句对数据库的相应操作，完成对数据的存取，为服务层提供数据操作服务。为进一步便捷对数据库完成数据存取操作，解决传统开发中的数据库连接问题，使用了数据库连接池技术(阿里德鲁伊连接池)，让数据库连接池复制配、管理和释放数据库连接，避免了不断重复与数据库建立连接。（具体描述见下文，遇到问题及解决方法）

​	由于项目主要是对数据库中两个表(用户和物料)进行操作，因此分别构建了两个dao层服务(MaterialDao和UserDao)。每个接口函数具体实现思路如下：1.首先从连接池中获取连接建立连接；2.设置SQL查询语句指定当前接口需要完成的数据库操作(预留待填充参数)；3.按索引值设置相应参数从而填充上述的SQL语句缺少的参数值；4.执行SQL语句完成对数据库的一次操作并获取返回值；5.遍历查询得到的结果并返回。

### service层

​	主要完成来自servlet分配具体的业务，其中所需的数据是通过调用上述dao层接口完成对数据获取和存储，然后根据需要返回相应的结果(用户对象列表等)，包括登录验证、注册管理、物料管理以及用户管理四个方面。

​	登录验证(LoginService)，完成普通用户和管理员登录验证，验证成功返回登录的该用户在数据库中详细记录信息，便于登录成功后在一次session内，主页面始终展示信息；

​	注册管理，完成普通用户和管理员注册的信息存储，调用dao层数据插入成功后返回Boolean型结果；

​	物料管理，完成用户请求物料管理服务，通过dao层完成对物料表的增删改查；

​	用户管理，完成用户请求用户管理服务，通过dao层完成对用户表的增删改查；

### servlet层

主要完成如下业务：1.处理用户请求(解析请求头、获取请求参数等)；2.分配相应的业务到服务层(service)；3.请求转发及重定向等。

​	为进一步解决优化URL匹配问题，让地址更加规范如下所示，自定义了一个类BaseServlet继承HttpServlet类并重写相关方法，通过在方法service内完成对地址的解析，让servlet可以根据请求的最后一段路径来进行方法分发(具体实现方法见下图java代码所示)。

```html
"/(user/material)/(select/selectAll/update/delete/add)?param1=xx&param1=xx"
href="<%=request.getContextPath()%>/material/select
href="<%=request.getContextPath()%>/user/add    
href="<%=request.getContextPath()%>/material/update?id=${material.id}
```

```java
public class BaseServlet extends HttpServlet {
    //根据请求的最后一段路径来进行方法分发
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 解决乱码
        req.setCharacterEncoding("utf-8");
        //1. 获取请求路径
        String uri = req.getRequestURI(); // /warehouse_MS/user/selectAll
        System.out.println("🌈 uri"+uri);
        //2. 获取最后一段路径，方法名
        int index = uri.lastIndexOf('/');
        String methodName = uri.substring(index + 1); //  /selectAll? selectAll?
        System.out.println("🌈 methodName"+methodName);
        //2. 执行方法
        //2.1 获取MaterialServlet /UserServlet 字节码对象 Class
        System.out.println(this);
        Class<? extends BaseServlet> cls = this.getClass();
        //2.2 获取方法 Method对象
        try {
            Method method = cls.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            //2.3 执行方法
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
```

​	在物料管理(MaterialServlet)和用户管理(UserServlet)内继承上述BaseServlet类，@WebServlet("/material/*")注解内加入相应的URl匹配规则，在其类域内编写相应的方法即可完成根据路径进行方法分配调用。



### 遇到问题及解决方法

修改或删除当前登录用户的信息时

**多次调用数据库查询后相关服务无响应**

解决方法，根据官方文档说明，druid连接池可以通过后台监控页面来查看SQL语句指定状况以及连接池状态，具体操作如下：

1. 开启druid数据库连接池的监控，设置Web应用程序配置文件(web.xml)，配置如下

```xml
<web-app>
  <display-name>Archetype Created Web Application</display-name>
  <!--  开启druid数据库连接池的监控 -->
  <servlet>
    <servlet-name>DruidStatView</servlet-name>
    <servlet-class>com.alibaba.druid.support.http.StatViewServlet</servlet-class>
  </servlet>
  <servlet-mapping>
    <servlet-name>DruidStatView</servlet-name>
    <url-pattern>/druid/*</url-pattern><!-- 路径 -->
  </servlet-mapping>
</web-app>
```

2. 在连接池配置文件(utils/DBUtils1)，添加筛选规则dataSource.setFilters("stat");
3. 启动项目后，即可根据localhost/druid/index.html访问到Druid监控页面，如下如图所示

![image-20220615104959881](C:\Users\23858\AppData\Roaming\Typora\typora-user-images\image-20220615104959881.png)

4. 查看数据源选项，如下图所示可以发现，当项目内数据库请求次数达到峰值(预设20)处于无响应状态，由监控数据可以发现执行20次后既达到峰值连接池内不产生逻辑销毁导致连接池内一直处于峰值状态，导致数据库请求无响应。

![image-20220615105501440](C:\Users\23858\AppData\Roaming\Typora\typora-user-images\image-20220615105501440.png)

5. 根据官方文档内的参数配置详解，可以通过指定一系列的逻辑销毁规则使一些请求完成的连接销毁释放资源，从而解决问题。相关连接池配置文件(utils/DBUtils1)如下

```java
dataSource.setMaxActive(20);
dataSource.setInitialSize(10);
// 定时销毁物理连接
dataSource.setFilters("stat");
dataSource.setTimeBetweenEvictionRunsMillis(6000);
dataSource.setMinEvictableIdleTimeMillis(3000);
dataSource.setRemoveAbandoned(true);
dataSource.setRemoveAbandonedTimeout(5);
```

6. 配置后的结果，可以发现在项目运行的过程中，连接池根据规则自动会产生逻辑销毁释放资源从而解决了以上问题。

![image-20220615110642684](C:\Users\23858\AppData\Roaming\Typora\typora-user-images\image-20220615110642684.png)
