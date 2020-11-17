/*
 * @author yangjiewei
 * @date 2020/11/12 19:59
 */
package com.yjiewei.test;

import com.yjiewei.dao.AccountDao;
import com.yjiewei.entity.Account;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestMybatis {

    @Test
    public void run2() throws IOException {
        // 加载mybatis配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession session = sqlSessionFactory.openSession();
        AccountDao dao = session.getMapper(AccountDao.class);

        dao.saveAccount(new Account("yangjiewei", 15000D)); // 注意这里默认开启事务，而没有默认提交，所以得手动提交

        List<Account> all = dao.findAll();
        System.out.println("mybatis可以用，我现在先增后查");
        for (Account account : all) {
            System.out.println(account);
        }
        session.commit();
        session.close();
        resourceAsStream.close();
    }
}
