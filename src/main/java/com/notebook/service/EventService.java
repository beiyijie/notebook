package com.notebook.service;

import java.util.List;

import com.notebook.entity.Event;

/**
 * 事件服务接口
 */
public interface EventService {
    
    /**
     * 添加事件
     * @param event 事件信息
     * @return 添加成功返回true，失败返回false
     */
    boolean addEvent(Event event);
    
    /**
     * 更新事件
     * @param event 事件信息
     * @return 更新成功返回true，失败返回false
     */
    boolean updateEvent(Event event);
    
    /**
     * 删除事件
     * @param id 事件ID
     * @param userId 用户ID
     * @return 删除成功返回true，失败返回false
     */
    boolean deleteEvent(Integer id, Integer userId);
    
    /**
     * 获取事件详情
     * @param id 事件ID
     * @return 事件信息
     */
    Event getEventById(Integer id);
    
    /**
     * 获取用户的所有事件
     * @param userId 用户ID
     * @return 事件列表
     */
    List<Event> getEventsByUserId(Integer userId);
    
    /**
     * 获取分类下的所有事件
     * @param categoryId 分类ID
     * @return 事件列表
     */
    List<Event> getEventsByCategoryId(Integer categoryId);
    
    /**
     * 根据条件查询事件
     * @param userId 用户ID
     * @param categoryId 分类ID
     * @param priority 优先级
     * @param status 状态
     * @param keyword 关键词
     * @return 事件列表
     */
    List<Event> getEventsByCondition(Integer userId, Integer categoryId, Integer priority, Integer status, String keyword);
    
    /**
     * 更新事件状态
     * @param id 事件ID
     * @param userId 用户ID
     * @param status 状态
     * @return 更新成功返回true，失败返回false
     */
    boolean updateEventStatus(Integer id, Integer userId, Integer status);
} 