package com.yjiewei.dao;

import com.yjiewei.entity.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountDao {

    @Select("select * from account")
    public List<Account> findAll();

    @Insert("insert into account(username,money) values(#{username},#{money})")
    public void saveAccount(Account account);
}
