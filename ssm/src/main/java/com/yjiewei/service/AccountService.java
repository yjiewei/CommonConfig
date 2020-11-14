package com.yjiewei.service;

import com.yjiewei.entity.Account;

import java.util.List;

public interface AccountService {

    List<Account> findAll();

    void saveAccount(Account account) throws Exception;
}
