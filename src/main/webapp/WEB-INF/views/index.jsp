<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>客户关系管理</title>
    <%@include file="common.jsp"%>
    <link rel="stylesheet" href="/css/public1.css"/>
    <script type="text/javascript" src="/js/views/index.js"/>
    <script type="text/javascript" src="/js/views/welcome.js"></script>
    <script type="text/javascript">

    </script>
</head>
<body>
<div id="container" class="easyui-layout" fit="true">
<%--头部--%>
    <div data-options="region:'north'" style="height:100px;background:url('/img/back.png') no-repeat;background-size: cover">
        <div id="top">
            <h1>天行管理系统</h1>
            <div id="top_links">
                <p>
                    <font style="margin-right:10px;">当前用户 : ${username}</font>
                    <a href="${pageContext.request.contextPath}/logout" class="btr-logout">安全退出</a>
                </p>
            </div>
        </div>
    </div>
<%-- 侧边栏   --%>
    <div data-options="region:'west'" style="width:180px">
    <%--  手风琴下拉菜单 --%>
        <div class="easyui-accordion" fit="true">
            <div title="菜单">
                <!-- 使用树组件来定义菜单 -->
                <ul id="menuTree"></ul>
            </div>
            <div title="帮助"></div>
            <div title="简介"></div>
        </div>
    </div>
<%-- 主体   --%>
    <div data-options="region:'center'" style="padding:5px;background:#eee;">
        <!-- 正文内容 -->
        <div id="myTabs" class="easyui-tabs" fit="true">
            <div title="首页" closable="true" >
                <center><h1>欢迎登陆首页</h1></center>
                <div id="welcome">
                    <div style="width:20%"></div>
                    <div style="width:55%"></div>
                    <div style="width:25%"></div>
                </div>
            </div>
        </div>
    </div>
<%-- 底部   --%>
<%--<div data-options="region:'south'"
     style="height:30px;  background: url('/img/tail.png') no-repeat; background-size: cover">
    <center>Copyright ©2015-2016 广州小码哥教育科技有限公司 (<font style="color: #fdfffb;">版权所有,侵权必究</font>)</center>
</div>--%>

</div>
</body>
</html>
