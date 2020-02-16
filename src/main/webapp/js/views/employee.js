$(function(){
    // 1.抽取变量
    // 2.将所有方法统一的管理
    // 3.对按钮进行统一的监听

    var empDatagrid,empDatagridEditAndDelete,empDialog,empDialogForm;
    empDatagrid = $("#emp_datagrid");
    empDatagridEditAndDelete = $("#emp_datagrid_edit,#emp_datagrid_delete");
    empDialog = $("#emp_dialog");
    empDialogForm = $("#emp_dialog_form");

    empDatagrid.datagrid({
        fit:true,
        fitColumns:true,
        url:"/employeeList",
        rownumbers:true,
        pagination:true,
        toolbar:"#emp_datagrid_tb",
        pageList:[1,10,20,30,40,50],
        onClickRow:function(index,row){
            if(row.state){
                empDatagridEditAndDelete.linkbutton("enable");
            } else{
                empDatagridEditAndDelete.linkbutton("disable");
            }
        },
        columns:[
            [
                {field:'cb',checkbox:'true',align:'center'},
                {field:'username',align:'center',title:'用户名',width:1},
                {field:'realName',align:'center',title:'真实姓名',width:1},
                {field:'tel',align:'center',title:'电话号码',width:1},
                {field:'email',align:'center',title:'邮箱',width:1},
                {field:'dept',align:'center',title:'部门名称',width:1,formatter:formatDeptName},
                {field:'inputTime',align:'center',title:'入职时间',width:1},
                {field:'stateStr',align:'center',title:'状态',width:1},
                {field:'state',hidden:true},
                {field:'adminStr',align:'center',title:'是否超级管理员',width:1},
            ]
        ],

    });
    empDialog.dialog({
        width:300,
        height:320,
        buttons:"#emp_dialog_btns",
        closed:true,
    })

//  统一方法管理
    var cmdObj = {
    // 打开新增员工对话框
        add:function(){
            empDialog.dialog("open").dialog("setTitle","添加员工信息");
            empDialogForm.form("clear");
        },
    // 打开修改员工对话框
        edit:function(){
            var selectRows = empDatagrid.datagrid("getSelections");
            if(selectRows.length != 1){
                $.messager.alert("系统提示","请选择一条要编辑的数据!")
                return;
            }
            var row = selectRows[0];
            empDialog.dialog("open").dialog("setTitle","修改员工信息");
            empDialogForm.form("clear");
            // 特殊属性的处理
            if(row.dept){
                row["dept.id"] = row.dept.id;
            }
            // 回显该员工拥有的角色
            // .1 使用同步请求获取该员工拥有的角色
            var htmlData = $.ajax({
                url:"/queryRoleOfEmp?id=" + row.id,
                async:false
            }).responseText;
            // .2 获取数据，并设置入下拉框中
            htmlData = $.parseJSON(htmlData);
            $("#roleList").combobox("setValues",htmlData);
            empDialogForm.form("load",row);
        },
    // 员工离职
        delete:function(){
            var selectRows = empDatagrid.datagrid("getSelections");
            if(selectRows.length == 0){
                $.messager.alert("系统提示","请至少选择操作一条数据!")
                return;
            }
            // 获取选中数据的id数组
            var ids = [];
            for(var i = 0; i < selectRows.length;i++){
                ids[i] = selectRows[i].id;
            }
            // 将数组转换字符串
            var strIds = ids.join(",");
            $.messager.confirm("系统提示","确定这 <font color=red>"  + selectRows.length + "</font>位员工离职吗？"
                ,function(r){
                    if(r){
                        $.post(
                            "/deleteEmp",
                            {ids:strIds},
                            function(result){
                                if(result.success){
                                    $.messager.alert("系统提示",result.msg,"info");
                                    empDatagrid.datagrid("reload");
                                } else{
                                    $.messager.alert("系统提示",result.msg,"info");
                                }
                            }
                            ,"json");
                    }
                }
            );

        },
        // 导出员工表
        exportEmpExcel:function(){
          $.post(
              "/exportEmpExcel",
              function(result){
                  if(result.success){
                     $.messager.alert("系统提示",result.msg,"info");
                  } else{
                      $.messager.alert("系统提示",result.msg,"info");
                  }
              }
          ,"json");
        },
    // 关闭对话框，取消按钮
        cancel:function(){
            empDialogForm.form("clear");
            empDialog.dialog("close");
        },
    //  刷新
        reload:function(){
            empDatagrid.datagrid("reload");
        },
    // 保存员工信息
        save:function(){
            // 发送异步请求
            empDialogForm.form("submit",{
                url:"/saveEmp",
                onSubmit:function(param){
                    var ids = $("#roleList").combobox("getValues");
                    for(var i = 0; i < ids.length; i++){
                        param["roleList[" + i + "].id"] = ids[i];
                    }
                },
                success:function(data){
                    data = $.parseJSON(data);
                    if(data.success){
                        $.messager.alert("提示",data.msg,"info",function(){
                            // 关闭对话框
                            empDialog.dialog("close");
                            // 刷新数据表
                            empDatagrid.datagrid("reload");
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
            empDatagrid.datagrid("load",{"keyword":value});
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



