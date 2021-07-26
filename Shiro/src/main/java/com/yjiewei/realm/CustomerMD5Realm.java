package com.yjiewei.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 使用自定义realm加入md5和salt和hash
 * @apiNote 这里假设都是从数据库中根据token中的用户名来查找数据库中匹配的用户信息，后续做凭证比对
 *          注意，这是默认的凭证比对是SimpleCredentialsMaster，如果我们的密码用了hash值，那么这个
 *          凭证比对器也需要修改，也就是具体比较的细节得换掉
 *          另外，验证时加入第三个参数的盐值，传入时不需要，凭证比较器会自动获取进行比较，反正凭证器只需要你指定hash的算法
 *          第四个参数是realm的名字
 *          如果散列1024次呢？ md5+salt+hash1024
 * @author yjiewei
 * @date 2021/7/17
 */
public class CustomerMD5Realm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        System.out.println("当前拿到的身份是："+ primaryPrincipal);
        // 从数据库中获得权限信息，用户信息 yjiewei  admin  user
        Set<String> set = new HashSet<>();
        set.add("admin");
        set.add("user");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo(set);
        // 另外一种设置角色的方法
        authorizationInfo.addRole("super");
        authorizationInfo.addStringPermission("user:*:01");
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String principal = (String) authenticationToken.getPrincipal();
        // 第三个参数为随机盐
        if ("yjiewei".equals(principal)) {
            return new SimpleAuthenticationInfo(principal, "458c2980fa62f02a98280bb8c4795425", ByteSource.Util.bytes("1234567890"), this.getName());
        }
        return null;
    }
}
