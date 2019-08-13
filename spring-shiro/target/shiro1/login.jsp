<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
    <input type="text" name="username" placeholder=用户名 maxlength="11" /><br/>
    <input type="password" name="password" placeholder=密码 maxlength="11" /><br/>
    <input type="checkbox" checked="checked"  id="rememberMe">记住我</label>
    <input type="submit"  id="login" value="登录" />
<script src="./js/jquery1.8.3.min.js"></script>
<script type="application/javascript">
    $("#login").click(function () {
        $.ajax({
            type:"POST",
            url:"${basePath}/login",
            data:{username:$('input[name="username"]').val(),
                password:$('input[name="password"]').val(),
                rememberMe:$("#rememberMe").is(':checked')},
            dataType:"json",
            success:function (result) {
                if (result && result.status==200){
                    window.location.href= result.message || "${basePath}/";
                }
            },
            error:function (e) {
                console.log(e,e.message);
            }
        })
    });
</script>
</body>
</html>