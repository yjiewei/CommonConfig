package com.yjiewei;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;

/**
 * 简单认证
 * @author yjiewei
 * @date 2021/7/17
 */
@Slf4j
public class AuthenticatorTest {
    public static void main(String[] args) {
        // 1.创建安全管理器
        DefaultSecurityManager securityManager = new DefaultSecurityManager();

        // 2.给安全管理器设置realm  域对象
        securityManager.setRealm(new IniRealm("classpath:shiro.ini"));

        // 3.SecurityUtils给全局工具类设置安全管理器
        SecurityUtils.setSecurityManager(securityManager);

        // 4.关键对象subject主体
        Subject subject = SecurityUtils.getSubject();

        // 5.创建令牌  认证可能会抛出两种异常，用户名不对UnknownAccountException，密码不对IncorrectCredentialsException
        UsernamePasswordToken token = new UsernamePasswordToken("yjiewei", "123456");
        subject.login(token);

        // 6.查看验证结果
        System.out.println("认证结果：" + subject.isAuthenticated());
    }
}
