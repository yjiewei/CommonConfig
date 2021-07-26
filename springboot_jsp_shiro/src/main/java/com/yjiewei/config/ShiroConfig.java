package com.yjiewei.config;

import com.yjiewei.cache.RedisCacheManager;
import com.yjiewei.shiro.CustomerRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.HashMap;
import java.util.Map;

/**
 * 整合shiro框架相关的配置类
 * @apiNote shiroFilter拦截到所有的请求之后，通过web安全管理器对请求进行处理，安全管理器需要realm来
 *          决定使用什么方式来做检验等工作，比如用的是jdbc还是mybatis等等
 * @author yjiewei
 * @date 2021/7/18
 */
@Configuration
public class ShiroConfig {

    // 1.创建shiroFilter，负责拦截所有请求
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 1.1配置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        // 1.2配置完安全管理器，你还得告诉拦截器你的系统那些是受限资源哪些是公共资源
        //    对于受限资源，或者公共资源，通常使用通配符来匹配，不然代码看起来可不太优雅
        Map<String, String> map = new HashMap<>();
        //map.put("/index.jsp", "authc"); // 访问资源，受限资源 authc（过滤器）代表请求这个资源需要认证和授权
        map.put("/user/login", "anon");
        map.put("/user/register", "anon");
        map.put("/register.jsp", "anon");
        map.put("/**", "authc");
        map.put("/user/getImage", "anon"); // 放过验证码
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        // 1.3默认认证界面路径
        shiroFilterFactoryBean.setLoginUrl("/login.jsp");

        return shiroFilterFactoryBean;
    }

    // 2.创建web安全管理器
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(Realm realm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(realm);

        return defaultWebSecurityManager;
    }

    // 3.创建自定义的realm
    // 为什么要设置凭证匹配器呢？
    //      首先你放入数据库的密码使用了散列，以及散列次数并加了盐，由于盐不变可以从数据库中获取，
    //      再者重新登录做校验时，相当于再次对你的密码做加密，但是realm是不知道你之前怎么做的加密，你得告诉它，
    //      最后，凭证匹配器是shiro内部实现的，相当于你要用什么方式来做校验。
    @Bean
    @Primary
    public Realm getRealm(){

        // realm是用来定义你的校验方式，域对象，设置你的凭证匹配器，加密方式，散列次数
        CustomerRealm customerRealm = new CustomerRealm();

        // 1.设置凭证匹配器
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        // 2.匹配器中使用的摘要算法
        credentialsMatcher.setHashAlgorithmName("md5");
        // 3.散列的次数
        credentialsMatcher.setHashIterations(1024);

        // 4.开启缓存管理器
        customerRealm.setCachingEnabled(true);
        customerRealm.setAuthenticationCachingEnabled(true);
        customerRealm.setAuthorizationCachingEnabled(true);
        // customerRealm.setCacheManager(new EhCacheManager());
        customerRealm.setCacheManager(new RedisCacheManager());

        customerRealm.setCredentialsMatcher(credentialsMatcher);
        return customerRealm;
    }

}
