package com.yjiewei;

import com.yjiewei.realm.CustomerMD5Realm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;

import java.util.Arrays;

/**
 * @author yjiewei
 * @date 2021/7/17
 */
public class CustomerMD5RealmAuthenticatorTest {
    public static void main(String[] args) {
        // 1.创建安全管理器
        DefaultSecurityManager securityManager = new DefaultSecurityManager();

        // 2.给安全管理器设置realm  域对象
        CustomerMD5Realm realm = new CustomerMD5Realm();

        // 2.1 设置realm使用hash凭证匹配器 并设置使用密码
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        // 2.2 告知散列次数，如果是md5+salt+hash1024
        hashedCredentialsMatcher.setHashIterations(1024);

        realm.setCredentialsMatcher(hashedCredentialsMatcher);
        securityManager.setRealm(realm);

        // 3.SecurityUtils给全局工具类设置安全管理器
        SecurityUtils.setSecurityManager(securityManager);

        // 4.关键对象subject主体
        Subject subject = SecurityUtils.getSubject();

        // 5.创建令牌  认证可能会抛出两种异常，用户名不对UnknownAccountException，密码不对IncorrectCredentialsException
        UsernamePasswordToken token = new UsernamePasswordToken("yjiewei", "123456");
        try{
            subject.login(token);
            System.out.println("登录成功");
        }catch (IncorrectCredentialsException exception) {
            exception.printStackTrace();
            System.out.println("密码错误");
        }catch (UnknownAccountException exception) {
            exception.printStackTrace();
            System.out.println("用户名错误");
        }

        // 6.认证用户进行授权
        if (subject.isAuthenticated()) {
            // 6.1 基于角色权限校验
            System.out.println(subject.hasRole("super"));

            // 6.2 基于多角色权限校验
            System.out.println(subject.hasAllRoles(Arrays.asList("admin", "user")));

            // 6.3 是否拥有其中一个角色
            boolean[] booleans = subject.hasRoles(Arrays.asList("admin", "super"));
            for (boolean aBoolean : booleans) {
                System.out.println(aBoolean);
            }

            // 6.4 基于权限字符串的访问控制  资源标识符：操作：资源类型
            boolean permitted = subject.isPermitted("user:update:01");// user模块的所有权限
            System.out.println("目前是否拥有user权限："+ permitted);

            // 6.5 分别有什么权限，全部权限
        }
    }
}
