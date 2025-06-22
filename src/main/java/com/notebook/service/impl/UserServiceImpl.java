package com.notebook.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.notebook.dao.UserDao;
import com.notebook.entity.User;
import com.notebook.service.UserService;

/**
 * 用户服务实现类
 */
@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserDao userDao;
    
    /**
     * 用户登录
     */
    @Override
    public User login(String username, String password) {
        // 直接使用明文密码
        
        // 先根据用户名查询用户
        User user = userDao.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
    
    /**
     * 用户注册
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean register(User user) {
        // 检查用户名是否存在
        if (checkUsernameExists(user.getUsername())) {
            return false;
        }
        
        // 直接使用明文密码
        return userDao.insert(user) > 0;
    }
    
    /**
     * 检查用户名是否存在
     */
    @Override
    public boolean checkUsernameExists(String username) {
        return userDao.countByUsername(username) > 0;
    }
    
    /**
     * 更新用户信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateUser(User user) {
        return userDao.update(user) > 0;
    }
    
    /**
     * 修改密码
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updatePassword(Integer userId, String oldPassword, String newPassword) {
        // 查询用户
        User user = userDao.findById(userId);
        if (user == null) {
            return false;
        }
        
        // 验证旧密码，直接比较明文
        if (!user.getPassword().equals(oldPassword)) {
            return false;
        }
        
        // 更新密码，直接使用明文
        return userDao.updatePassword(userId, newPassword) > 0;
    }

    @Override
    public User getUserById(Integer id) {
        return userDao.findById(id);
    }
    
    /**
     * 重置用户密码（无需验证旧密码）
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean resetPassword(Integer id, String newPassword) {
        return userDao.updatePassword(id, newPassword) > 0;
    }
} 