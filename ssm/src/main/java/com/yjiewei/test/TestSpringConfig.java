/*
 * @author yangjiewei
 * @date 2020/11/12 16:26
 */
package com.yjiewei.test;

import com.yjiewei.config.SpringConfig;
import com.yjiewei.entity.Account;
import com.yjiewei.service.AccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpringConfig {

    // 事务有效
    @Test
    public void run1() throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        AccountService accountService = context.getBean(AccountService.class);
        accountService.saveAccount(new Account("testspringconfig",0000D));
    }
}
