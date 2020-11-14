/*
 * @author yangjiewei
 * @date 2020/11/12 16:26
 */
package com.yjiewei.test;

import com.yjiewei.entity.Account;
import com.yjiewei.service.AccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class TestSpring {

    // 事务有效
    @Test
    public void run1() throws Exception {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        AccountService accountService = ac.getBean("accountServiceImpl", AccountService.class);
        accountService.saveAccount(new Account("yyy",0000D));
    }
}
