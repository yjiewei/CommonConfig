<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>shiro framework</title>
</head>
<body>
    <h1>用户登录</h1>

    <form action="${pageContext.request.contextPath}/user/login" method="post">
        用户名：<input type="text" name="username" > <br>
        密码：<input type="password" name="password"> <br>
        请输入验证码: <input type="text" name="code"><img src="${pageContext.request.contextPath}/user/getImage" alt=""><br>
        <input type="submit" value="登录">
    </form>
</body>
</html>