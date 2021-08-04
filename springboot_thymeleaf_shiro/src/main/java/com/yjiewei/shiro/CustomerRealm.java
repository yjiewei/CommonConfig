package com.yjiewei.shiro;

import com.yjiewei.entity.Perms;
import com.yjiewei.entity.User;
import com.yjiewei.service.UserService;
import com.yjiewei.util.ApplicationContextUtils;
import com.yjiewei.util.MyByteSource;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * 自定义realm
 * @author yjiewei
 * @date 2021/7/18
 */
public class CustomerRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        System.out.println("调用授权验证：" + primaryPrincipal);
        // 根据主权限信息获取角色和权限信息
        UserService userService = (UserService) ApplicationContextUtils.getBean("userService"); // 直接注入不可行？
        User user = userService.findRolesByUserName(primaryPrincipal);

        // 授权角色用户信息
        if(!CollectionUtils.isEmpty(user.getRoles())){

            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

            // 这里内部对用户角色和权限做了一个连接并添加到授权信息中
            user.getRoles().forEach(role->{
                simpleAuthorizationInfo.addRole(role.getName()); //添加角色信息

                //权限信息
                List<Perms> perms = userService.findPermsByRoleId(role.getId());
                System.out.println("perms:"+perms);

                if(!CollectionUtils.isEmpty(perms) && perms.get(0)!=null ){
                    perms.forEach(perm->{
                        simpleAuthorizationInfo.addStringPermission(perm.getName());
                    });
                }
            });
            return simpleAuthorizationInfo;
        }
        return null;
//        System.out.println("用户信息");
//
//        /**
//         * @note 当你设置资源受限时，后台授权得有匹配的角色和权限去获取相对应的资源，前面是锁，这个是看你有没有钥匙🔑
//         */
//        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        // 新增两个角色
//        authorizationInfo.addRole("user");
//        authorizationInfo.addRole("user_manager");
//        // user用户对所有资源类型都有新增和删除功能
//        authorizationInfo.addStringPermission("user:add:*");
//        authorizationInfo.addStringPermission("user:delete:*");
//
//        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        String username = (String) authenticationToken.getPrincipal();
        // 模拟实现
        /*if ("yjiewei".equals(username)){
            return new SimpleAuthenticationInfo(username, "123456", this.getName());
        }*/
        // 从数据库中获取对应的用户名及密码等信息做认证
        // 1.在工厂中获取service对象
        UserService userService = (UserService) ApplicationContextUtils.getBean("userService");

        // 2.根据身份信息做查询
        User user = userService.findByUserName(username);

        // 3.用户不为空，则返回数据库信息
        if (!ObjectUtils.isEmpty(user)) {
            // 3.1返回数据库中的信息，底层和用户输入的信息做校验
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user.getUsername(),
                    user.getPassword(),
                    new MyByteSource(user.getSalt()), // 需要自己去实现salt的序列化
                    this.getName());
            return simpleAuthenticationInfo;
        }
        return null;
    }
}
