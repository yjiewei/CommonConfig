package com.yjiewei.dao;

import com.yjiewei.entity.Perms;
import com.yjiewei.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author yjiewei
 * @date 2021/7/18
 */
@Mapper
public interface UserDao {

    void save(User user);

    // 根据身份信息认证的方法
    User findByUserName(String username);

    //根据用户名查询所有角色
    User findRolesByUserName(String username);

    //根据角色id查询权限集合
    List<Perms> findPermsByRoleId(String id);

}
