package com.yjiewei.service;

import com.yjiewei.entity.Perms;
import com.yjiewei.entity.User;

import java.util.List;

/**
 * @author yjiewei
 * @date 2021/7/18
 */
public interface UserService {
    // 注册用户方法
    void register(User user);

    // 根据用户名查询业务的方法
    User findByUserName(String username);

    //根据用户名查询所有角色
    User findRolesByUserName(String username);

    //根据角色id查询权限集合
    List<Perms> findPermsByRoleId(String id);

}
