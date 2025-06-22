package com.notebook.dao;

import org.apache.ibatis.annotations.Param;

import com.notebook.entity.User;

/**
 * 用户Dao接口
 */
public interface UserDao {
    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 用户对象
     */
    User findByUsername(String username);
    
    /**
     * 添加用户
     * @param user 用户对象
     * @return 影响的行数
     */
    int insert(User user);
    
    /**
     * 根据ID查询用户
     * @param id 用户ID
     * @return 用户对象
     */
    User findById(Integer id);
    
    /**
     * 更新用户信息
     * @param user 用户对象
     * @return 影响的行数
     */
    int update(User user);
    
    /**
     * 修改密码
     * @param id 用户ID
     * @param newPassword 新密码
     * @return 影响的行数
     */
    int updatePassword(@Param("id") Integer id, @Param("newPassword") String newPassword);
    
    /**
     * 根据用户名统计用户数量
     * @param username 用户名
     * @return 用户数量
     */
    int countByUsername(String username);

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return 影响的行数
     */
    int login(@Param("username") String username, @Param("password") String password);
} 