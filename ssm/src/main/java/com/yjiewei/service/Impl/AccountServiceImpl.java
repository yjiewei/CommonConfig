/*
 * @author yangjiewei
 * @date 2020/11/12 16:15
 */
package com.yjiewei.service.Impl;

import com.yjiewei.dao.AccountDao;
import com.yjiewei.entity.Account;
import com.yjiewei.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountDao accountDao;

//    @Resource
//    private TransactionTemplate transactionTemplate;

    @Override
    public List<Account> findAll() {
        System.out.println("这里只是模拟业务层来 查询所有账户...");
        return accountDao.findAll();
    }

    // 编程式事务可行
//    @Override
//    public void saveAccount(Account account) {
//        System.out.println("这里只是模拟业务层： 保存账户...");
//        // 编程式事务可行
//        transactionTemplate.execute(status -> {
//            accountDao.saveAccount(account);
//            int i = 10;
//            if (i == 10) throw new RuntimeException();
//            return null;
//        });
//    }








    @Transactional
    @Override
    public void saveAccount(Account account){
        System.out.println("这里只是模拟业务层： 保存账户...");
        accountDao.saveAccount(account);
        int i = 10;
        if (i == 10) throw new RuntimeException();
    }
}








