$(function(){
    // 1.抽取变量
    // 2.将所有方法统一的管理
    // 3.对按钮进行统一的监听

    var roleDatagrid,roleDatagridEditAndDelete,roleDialog,roleDialogForm,allPermissions,selfPermission;
    roleDatagrid = $("#role_datagrid");
    roleDatagridEditAndDelete = $("#role_datagrid_edit,#role_datagrid_delete");
    roleDialog = $("#role_dialog");
    roleDialogForm = $("#role_dialog_form");
    allPermissions = $("#allPermissions");
    selfPermission = $("#selfPermission");

    roleDatagrid.datagrid({
        fit:true,
        url:"/roleList",
        fitColumns:true,
        rownumbers:true,
        pagination:true,
        singleSelect:true,
        toolbar:"#role_datagrid_tb",
        pageList:[1,10,20,30,40,50],
        columns:[
            [
                {field:'sn',align:'center',title:'编号',width:1},
                {field:'name',align:'center',title:'名称',width:1},
            ]
        ],

    });
    roleDialog.dialog({
        width:650,
        height:450,
        buttons:"#role_dialog_btns",
        closed:true,
    })

//  所有权限窗格
    allPermissions.datagrid({
        width:300,
        height:300,
        url:"/permissionList",
        title:"所有权限",
        fitColumns: true,
        pagination:true,
        rownumbers:true,
        singleSelect:true,
        columns:[
            [
                {field:"name",title:"权限名",align:'center',width:1},
            ]
        ],
        onDblClickRow:function(index,row){
            // 1.判断selfPermission是否有当前选中的数据
                // 1.1 拿到selfPermission中所有的数据 id
            var selfRows = selfPermission.datagrid("getRows");
                // 1.2 标记
            var flag = true;
                // 1.3 索引，选中使用
            var index = -1;
                // 1.4 遍历与选中的数据 id  是否相同
            for(var i = 0 ; i < selfRows.length ; i++){
                if(row.id == selfRows[i].id){
                    // 1.5 如果有相同的数据，使flag = false,
                    flag = false;
                    index = i;
                    break;
                }
            }
            // flag = true,新增
            // flag = false,选中
            if(flag){
                selfPermission.datagrid("appendRow",row);
            } else{
                selfPermission.datagrid("selectRow",index);
            }

        }
    })
    var pager = allPermissions.datagrid("getPager");
    pager.pagination({
        showPageList: false,
        showRefresh: false,
        displayMsg: '',
    });

//  已有权限窗格
    selfPermission.datagrid({
        width:300,
        height:300,
        title:"已有权限",
        rownumbers:true,
        fitColumns:true,
        singleSelect: true,
        columns:[[
            {field:"name",title:"权限名",align:'center',width:1},
        ]],
        onDblClickRow:function(index,row){
            selfPermission.datagrid("deleteRow",index);
        }
    })





//  统一方法管理
    var cmdObj = {
    // 打开新增角色对话框
        add:function(){
            roleDialog.dialog("open").dialog("setTitle","添加角色");
            $("[name='id'],[name='sn'],[name='name']").val("");
            // 清空数据表格的内容
            selfPermission.datagrid("loadData",{rows:[]});
        },
    // 打开修改员工对话框
        edit:function(){
            var row = roleDatagrid.datagrid("getSelected");
            if(row){
                roleDialog.dialog("open").dialog("setTitle","修改员工信息");
                $("[name='id'],[name='sn'],[name='name']").val("");
                // 需要查询该角色的权限
                var options = selfPermission.datagrid("options");
                options.url = "/openEdit";
                selfPermission.datagrid("load",{
                    rId:row.id
                });
                roleDialogForm.form("load",row);//同名匹配原则
            } else{
                $.messager.alert("提示","请选中一条需要编辑的数据","info");
            }

        },
    // 删除角色
        delete:function(){
            var row = roleDatagrid.datagrid("getSelected");
            if(row.length == 0){
                $.messager.alert("系统提示","请至少选择操作一条数据!")
                return;
            }
            // 获取该行的id
            var id = row.id;
            $.messager.confirm("系统提示","确定删除这个角色吗？"
                ,function(r){
                    if(r){
                        $.post(
                            "/deleteRole",
                            {id:id},
                            function(result){
                                if(result.success){
                                    $.messager.alert("系统提示",result.msg,"info");
                                    roleDatagrid.datagrid("reload");
                                } else{
                                    $.messager.alert("系统提示",result.msg,"info");
                                }
                            }
                            ,"json");
                    }
                }
            );

        },
    // 关闭对话框，取消按钮
        cancel:function(){
            $("[name='id'],[name='sn'],[name='name']").val("");
            roleDialog.dialog("close");
        },
    //  刷新
        reload:function(){
            roleDatagrid.datagrid("reload");
        },
    // 保存员工信息
        save:function(){
            // 发送异步请求
            roleDialogForm.form("submit",{
                url:"/saveRole",
                // 可以给表单额外添加参数
                onSubmit:function(parame){
                    var rows = selfPermission.datagrid("getRows");
                    for(var i = 0 ; i < rows.length ; i++){
                        parame["permissionList[" +i+ "].id"] = rows[i].id;
                    }
                },
                success:function(data){
                    data = $.parseJSON(data);
                    if(data.success){
                        $.messager.alert("提示",data.msg,"info",function(){
                            // 关闭对话框
                            roleDialog.dialog("close");
                            // 刷新数据表
                            roleDatagrid.datagrid("reload");
                        });
                    } else{
                        $.messager.alert("提示",data.msg,"info");
                    }
                }
            });
        },
    //    关键字查询
        searchBtn:function(){
            var value = $("#searchBox").val();
            roleDatagrid.datagrid("load",{"keyword":value});
        }
    };


//   对所有指定按钮进行监听
    $("a[data-cmd]").on("click",function(){
        var cmd = $(this).data("cmd");
        cmdObj[cmd]();
    });

});

function formatDeptName(val,row){
    return val.name;
}



