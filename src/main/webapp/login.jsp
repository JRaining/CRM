<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客户关系管理系统</title>
    <link rel="stylesheet" href="/css/style.css">
    <%@include file="/WEB-INF/views/common.jsp"%>
    <script type="text/javascript">
        // 监听回车键，执行提交表单方法
        function keydownSubmit(event){
            if(event.keyCode == 13){
                submitForm();
            }
        }


        // 提交登录账号和密码:把账号和密码传递到后台
        function submitForm(){
            // console.log($("form").serialize())
            $.post(
                "/login",
                $("form").serialize(),
                function (result){
                    if(result.success){
                        window.location.href="/index"
                    }else{
                        alert(result.msg);
                    }
                }
            ,"json");
        }
    </script>
</head>
<body>

<div class="login_box">

    <form class="login_form" method="post">
        <h1>Welcome</h1>
        <input class="txtb" name="username" type="text" placeholder="UserName" />
        <input class="txtb" name="password" type="password" placeholder="Password" />
        <input class="login_btn" type="submit" name="" value="Login" onclick="submitForm()" onkeydown="keydownSubmit()" />
    </form>
</div>

</body>
</html>