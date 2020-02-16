<%--
  Created by IntelliJ IDEA.
  User: 24360
  Date: 2019/12/23
  Time: 20:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="/WEB-INF/tld/myFn.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>员工管理</title>
    <%@include file="common.jsp"%>
    <script type="text/javascript" src="/js/views/employee.js"></script>
    <script type="text/javascript">

    </script>
</head>
<body>
<%-- 数据表格 --%>
    <table id="emp_datagrid">
    </table>

<%--  添加、员工信息对话框   --%>
    <div id="emp_dialog">
        <form id="emp_dialog_form" method="post">
            <table align="center" style="margin-top:20px;">
                <input type="hidden" name="id" />
                <tr>
                    <td>用户名</td>
                    <td><input type="text" id="username" name="username" class="easyui-validatebox" required="true"/></td>
                </tr>
                <tr>
                    <td>真实姓名</td>
                    <td><input type="text" id="realName" name="realName" class="easyui-validatebox" required="true"/></td>
                </tr>
                <tr>
                    <td>联系方式</td>
                    <td><input type="text" id="tel" name="tel" class="easyui-validatebox" required="true"/></td>
                </tr>
                <tr>
                    <td>邮箱</td>
                    <td><input type="text" id="email" name="email" class="easyui-validatebox" required="true"/></td>
                </tr>
                <tr>
                    <td>部门</td>
                    <td><input type="text" id="dept_id" name="dept.id" class="easyui-combobox" required="true" editable="false" data-options="valueField:'id',textField:'name',url:'/queryDept'"/></td>
                </tr>
                <tr>
                    <td>录入时间</td>
                    <td><input type="text" id="inputTime" name="inputTime" class="easyui-datetimebox" required="true" editable="false"/></td>
                </tr>
                <tr>
                    <td>角色</td>
                    <td><input type="text" id="roleList" class="easyui-combobox" editable="false" data-options="valueField:'id',textField:'name',url:'/queryRoleToEmp',multiple:true"/></td>
                </tr>
            </table>
        </form>
    </div>

<%--  数据表顶部按钮  --%>
    <div id="emp_datagrid_tb">
        <input id="searchBox" type="text" size="20" style="border-radius:10px;outline: none;padding:3px;">
        <a class="easyui-linkbutton" iconCls="icon-search" plain="true" data-cmd="searchBtn">搜索</a>
        <span>|</span>
        <c:if test="${fn:checkPermission('/saveEmp')}">
            <a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">新增</a>
        </c:if>

        <c:if test="${fn:checkPermission('/saveEmp')}">
            <a id="emp_datagrid_edit" class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">编辑</a>
        </c:if>

        <c:if test="${fn:checkPermission('/deleteEmp')}">
            <a id="emp_datagrid_delete" class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="delete">离职</a>
        </c:if>
        <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
        <span>|</span>
        <a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="intoExcel">导入表</a>
        <a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="exportEmpExcel">导出表</a>
    </div>
<%-- 对话框底部按钮 --%>
    <div id="emp_dialog_btns">
        <a class="easyui-linkbutton" iconCls="icon-save" data-cmd="save">保存</a>
        <a class="easyui-linkbutton" iconCls="icon-cancel" data-cmd="cancel">取消</a>
    </div>

</body>
</html>
