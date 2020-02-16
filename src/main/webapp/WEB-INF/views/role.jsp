<%--
  Created by IntelliJ IDEA.
  User: 24360
  Date: 2019/12/23
  Time: 20:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>角色管理</title>
    <%@include file="common.jsp"%>
    <script type="text/javascript" src="/js/views/role.js"></script>
    <script type="text/javascript">

    </script>
</head>
<body>
<%-- 数据表格 --%>
    <table id="role_datagrid">
    </table>

<%--  添加、员工信息对话框   --%>
    <div id="role_dialog">
        <form id="role_dialog_form" method="post">
            <table align="center" style="margin-top:20px;">
                <input type="hidden" name="id" />
                <tr>
                    <td>角色名称:<input type="text" id="roleName" name="name" /></td>
                    <td>角色编号:<input type="text" id="roleSn" name="sn"/></td>
                </tr>
                <tr>
                    <td>
                        <table id="allPermissions">

                        </table>
                    </td>
                    <td>
                        <table id="selfPermission">

                        </table>
                    </td>
                </tr>
            </table>
        </form>
    </div>

<%--  数据表顶部按钮  --%>
    <div id="role_datagrid_tb">
        <input id="searchBox" type="text" size="20" style="border-radius:10px;outline: none;padding:3px;">
        <a class="easyui-linkbutton" iconCls="icon-search" plain="true" data-cmd="searchBtn">搜索</a>
        <span>|</span>
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">新增</a>
        <a id="role_datagrid_edit" class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">编辑</a>
        <a id="role_datagrid_delete" class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="delete">删除</a>
        <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
    </div>
<%-- 对话框底部按钮 --%>
    <div id="role_dialog_btns">
        <a class="easyui-linkbutton" iconCls="icon-save" data-cmd="save">保存</a>
        <a class="easyui-linkbutton" iconCls="icon-cancel" data-cmd="cancel">取消</a>
    </div>

</body>
</html>
