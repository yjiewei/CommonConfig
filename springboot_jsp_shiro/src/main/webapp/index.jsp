<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>shiro framework</title>
</head>
<body>
<%-- 设置资源访问受限 --%>
<h1>系统主页V1.0</h1><a href="${pageContext.request.contextPath}/user/logout">退出登录</a>
    <ul>
        <shiro:hasAnyRoles name="user_manager, admin, addinfo_manager">
            <li><a href=""> 用户管理</a>
                <ul>
                    <shiro:hasPermission name="user:add:*">
                        <li><a href=""> 添加 </a> </li>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="user:delete:*">
                        <li><a href=""> 删除 </a> </li>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="user:update:*">
                        <li><a href=""> 修改 </a> </li>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="user:find:*">
                        <li><a href=""> 查询 </a> </li>
                    </shiro:hasPermission>
                </ul>
            </li>
        </shiro:hasAnyRoles>

        <shiro:hasAnyRoles name="user_manager, admin, addinfo_manager">
            <li><a href=""> 订单管理</a>
                <ul>
                    <shiro:hasPermission name="user:add:*">
                        <li><a href=""> 添加 </a> </li>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="user:delete:*">
                        <li><a href=""> 删除 </a> </li>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="user:update:*">
                        <li><a href=""> 修改 </a> </li>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="user:find:*">
                        <li><a href=""> 查询 </a> </li>
                    </shiro:hasPermission>
                </ul>
            </li>
        </shiro:hasAnyRoles>

        <shiro:hasRole name="admin">
            <li><a href=""> 商品管理</a> </li>
            <li><a href=""> 物流管理</a> </li>
        </shiro:hasRole>

        <shiro:hasRole name="user">
            <li><a href=""> 仅普通用户可见</a> </li>
            <li><a href=""> 公共资源 </a> </li>
        </shiro:hasRole>
    </ul>

    <hr>
    <shiro:guest>
        游客访问 <a href = "login.jsp"></a>
    </shiro:guest>

    <%--user 标签：用户已经通过认证\记住我 登录后显示响应的内容--%>
    <shiro:user>
        欢迎[<shiro:principal/>]登录 <a href = "logout">退出</a>
    </shiro:user>

    <%--authenticated标签：用户身份验证通过，即 Subjec.login 登录成功 不是记住我登录的--%>
    <shiro:authenticated>
        用户[<shiro:principal/>] 已身份验证通过
    </shiro:authenticated>

    <%--notAuthenticated标签：用户未进行身份验证，即没有调用Subject.login进行登录,包括"记住我"也属于未进行身份验证--%>
    <shiro:notAuthenticated>
        未身份验证(包括"记住我")
    </shiro:notAuthenticated>

    <%-- principal 标签：显示用户身份信息，默认调用
    Subjec.getPrincipal()获取，即Primary Principal--%>
   <%-- <shiro:principal property = "username"/>--%>

    <%-- hasRole标签：如果当前Subject有角色将显示body体内的内容--%>
    <shiro:hasRole name = "admin">
        用户[<shiro:principal/>]拥有角色admin
    </shiro:hasRole>

    <%--hasAnyRoles标签：如果Subject有任意一个角色(或的关系)将显示body体里的内容--%>
    <shiro:hasAnyRoles name = "admin,user">
        用户[<shiro:principal/>]拥有角色admin 或者 user
    </shiro:hasAnyRoles>

    <%--lacksRole:如果当前 Subjec没有角色将显示body体内的内容--%>
    <shiro:lacksRole name = "admin">
        用户[<shiro:principal/>]没有角色admin
    </shiro:lacksRole>

    <%--hashPermission:如果当前Subject有权限将显示body体内容--%>
    <shiro:hasPermission name = "user:create">
        用户[<shiro:principal/>] 拥有权限user:create
    </shiro:hasPermission>

    <%--lacksPermission:如果当前Subject没有权限将显示body体内容--%>
    <shiro:lacksPermission name = "org:create">
        用户[<shiro:principal/>] 没有权限org:create
    </shiro:lacksPermission>

</body>
</html>