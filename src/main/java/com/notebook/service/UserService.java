package com.notebook.service;

import com.notebook.entity.User;

/**
 * 用户服务接口
 */
public interface UserService {
    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return 登录成功返回用户信息，失败返回null
     */
    User login(String username, String password);
    
    /**
     * 用户注册
     * @param user 用户信息
     * @return 注册成功返回true，失败返回false
     */
    boolean register(User user);
    
    /**
     * 检查用户名是否存在
     * @param username 用户名
     * @return 存在返回true，不存在返回false
     */
    boolean checkUsernameExists(String username);
    
    /**
     * 根据ID查询用户
     * @param id 用户ID
     * @return 用户信息
     */
    User getUserById(Integer id);
    
    /**
     * 修改用户信息
     * @param user 用户信息
     * @return 修改成功返回true，失败返回false
     */
    boolean updateUser(User user);
    
    /**
     * 修改密码
     * @param id 用户ID
     * @param oldPassword 原密码
     * @param newPassword 新密码
     * @return 修改成功返回true，失败返回false
     */
    boolean updatePassword(Integer id, String oldPassword, String newPassword);
    
    /**
     * 重置用户密码（无需验证旧密码）
     * @param id 用户ID
     * @param newPassword 新密码
     * @return 重置成功返回true，失败返回false
     */
    boolean resetPassword(Integer id, String newPassword);
} 