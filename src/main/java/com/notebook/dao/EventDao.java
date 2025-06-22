package com.notebook.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.notebook.entity.Event;

/**
 * 事件Dao接口
 */
public interface EventDao {
    /**
     * 添加事件
     * @param event 事件对象
     * @return 影响的行数
     */
    int insert(Event event);
    
    /**
     * 修改事件
     * @param event 事件对象
     * @return 影响的行数
     */
    int update(Event event);
    
    /**
     * 删除事件
     * @param id 事件ID
     * @param userId 用户ID（确保只能删除自己的事件）
     * @return 影响的行数
     */
    int delete(@Param("id") Integer id, @Param("userId") Integer userId);
    
    /**
     * 通过ID查询事件
     * @param id 事件ID
     * @return 事件对象
     */
    Event findById(Integer id);
    
    /**
     * 查询用户所有事件
     * @param userId 用户ID
     * @return 事件列表
     */
    List<Event> findByUserId(Integer userId);
    
    /**
     * 根据分类ID查询事件
     * @param categoryId 分类ID
     * @return 事件列表
     */
    List<Event> findByCategoryId(Integer categoryId);
    
    /**
     * 根据条件查询事件
     * @param userId 用户ID
     * @param categoryId 分类ID
     * @param priority 优先级
     * @param status 状态
     * @param keyword 关键词
     * @return 事件列表
     */
    List<Event> findByCondition(@Param("userId") Integer userId,
                              @Param("categoryId") Integer categoryId,
                              @Param("priority") Integer priority,
                              @Param("status") Integer status,
                              @Param("keyword") String keyword);
    
    /**
     * 修改事件状态
     * @param id 事件ID
     * @param userId 用户ID（确保只能修改自己的事件）
     * @param status 状态
     * @return 影响的行数
     */
    int updateStatus(@Param("id") Integer id, @Param("userId") Integer userId, @Param("status") Integer status);
} 