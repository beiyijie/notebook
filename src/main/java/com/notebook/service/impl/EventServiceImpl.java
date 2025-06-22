package com.notebook.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.notebook.dao.EventDao;
import com.notebook.entity.Event;
import com.notebook.service.EventService;

/**
 * 事件服务实现类
 */
@Service
public class EventServiceImpl implements EventService {
    
    @Autowired
    private EventDao eventDao;
    
    /**
     * 新增事件
     * @param event 事件对象
     * @return 操作结果，true成功，false失败
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addEvent(Event event) {
        // 设置默认值
        if (event.getPriority() == null) {
            event.setPriority(0); // 默认低优先级
        }
        if (event.getStatus() == null) {
            event.setStatus(0); // 默认未完成
        }
        
        // 执行新增
        return eventDao.insert(event) > 0;
    }
    
    /**
     * 修改事件
     * @param event 事件对象
     * @return 操作结果，true成功，false失败
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateEvent(Event event) {
        return eventDao.update(event) > 0;
    }
    
    /**
     * 删除事件
     * @param id 事件ID
     * @param userId 用户ID
     * @return 操作结果，true成功，false失败
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteEvent(Integer id, Integer userId) {
        return eventDao.delete(id, userId) > 0;
    }
    
    /**
     * 根据ID查询事件
     * @param id 事件ID
     * @return 事件对象
     */
    @Override
    public Event getEventById(Integer id) {
        return eventDao.findById(id);
    }
    
    /**
     * 根据用户ID查询事件列表
     * @param userId 用户ID
     * @return 事件列表
     */
    @Override
    public List<Event> getEventsByUserId(Integer userId) {
        return eventDao.findByUserId(userId);
    }
    
    /**
     * 根据分类ID查询事件列表
     * @param categoryId 分类ID
     * @return 事件列表
     */
    @Override
    public List<Event> getEventsByCategoryId(Integer categoryId) {
        return eventDao.findByCategoryId(categoryId);
    }
    
    /**
     * 根据条件查询事件列表
     * @param userId 用户ID
     * @param categoryId 分类ID
     * @param priority 优先级
     * @param status 状态
     * @param keyword 关键词
     * @return 事件列表
     */
    @Override
    public List<Event> getEventsByCondition(Integer userId, Integer categoryId, Integer priority, Integer status, String keyword) {
        return eventDao.findByCondition(userId, categoryId, priority, status, keyword);
    }
    
    /**
     * 更新事件状态
     * @param id 事件ID
     * @param userId 用户ID
     * @param status 状态
     * @return 操作结果，true成功，false失败
     */
    @Override
    public boolean updateEventStatus(Integer id, Integer userId, Integer status) {
        return eventDao.updateStatus(id, userId, status) > 0;
    }
} 