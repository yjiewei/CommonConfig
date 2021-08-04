package com.yjiewei.service;

import com.yjiewei.dao.UserDao;
import com.yjiewei.entity.Perms;
import com.yjiewei.entity.User;
import com.yjiewei.util.SaltUtil;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yjiewei
 * @date 2021/7/18
 */
@Component("userService")
@Transactional
public class UserServiceImpl implements UserService{

    @Resource
    private UserDao userDao;

    @Override
    public void register(User user) {
        // 处理业务 md5+salt+hash
        String salt = SaltUtil.getSalt(10);
        user.setSalt(salt);
        Md5Hash md5Hash = new Md5Hash(user.getPassword(), salt, 1024);
        user.setPassword(md5Hash.toHex());
        userDao.save(user);
    }

    @Override
    public User findByUserName(String username) {
        return userDao.findByUserName(username);
    }

    @Override
    public User findRolesByUserName(String username) {
        return userDao.findRolesByUserName(username);
    }

    @Override
    public List<Perms> findPermsByRoleId(String id) {
        return userDao.findPermsByRoleId(id);
    }
}
