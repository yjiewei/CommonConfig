package com.yjiewei.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * 自定义的realm实现，将认证/授权数据的来源转为数据库的实现
 * @author yjiewei
 * @date 2021/7/17
 */
public class CustomerRealm extends AuthorizingRealm {

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        System.out.println("自定义的realm获得登录的用户名是 " + username);
        // 假设我们从数据库中查询一个数据记录，然后这里和用户名进行比较
        if ("yjiewei".equals(username)) {
            // 参数 1.数据库用户名  2.返回数据库中的正确密码（再去别的地方比较）  3.当前realm名字
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, "123456", this.getName());
            return authenticationInfo;
        }
        return null;
    }
}
