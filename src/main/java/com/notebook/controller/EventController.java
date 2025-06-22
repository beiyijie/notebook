package com.notebook.controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.notebook.entity.Event;
import com.notebook.entity.User;
import com.notebook.service.EventService;
import com.notebook.util.Result;

/**
 * 事件控制器
 */
@RestController
@RequestMapping("/event")
public class EventController {
    
    @Autowired
    private EventService eventService;
    
    /**
     * 获取当前用户的所有事件
     */
    @GetMapping("/list")
    public Result<List<Event>> getEventList(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return Result.failure(401, "未登录");
        }
        List<Event> events = eventService.getEventsByUserId(user.getId());
        return Result.success(events);
    }
    
    /**
     * 根据分类ID获取事件列表
     */
    @GetMapping("/list/category/{categoryId}")
    public Result<List<Event>> getEventListByCategory(@PathVariable Integer categoryId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return Result.failure(401, "未登录");
        }
        List<Event> events = eventService.getEventsByCategoryId(categoryId);
        return Result.success(events);
    }
    
    /**
     * 添加事件
     */
    @PostMapping("/add")
    public Result<Event> addEvent(@RequestBody Event event, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return Result.failure(401, "未登录");
        }
        
        event.setUserId(user.getId());
        // 设置默认值
        if (event.getPriority() == null) {
            event.setPriority(0); // 默认低优先级
        }
        if (event.getStatus() == null) {
            event.setStatus(0); // 默认未完成
        }
        
        if (eventService.addEvent(event)) {
            return Result.success(event);
        } else {
            return Result.failure("添加失败");
        }
    }
    
    /**
     * 更新事件
     */
    @PutMapping("/update")
    public Result<Void> updateEvent(@RequestBody Event event, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return Result.failure(401, "未登录");
        }
        
        event.setUserId(user.getId());
        if (eventService.updateEvent(event)) {
            return Result.success("更新成功");
        } else {
            return Result.failure("更新失败");
        }
    }
    
    /**
     * 删除事件
     */
    @DeleteMapping("/delete/{id}")
    public Result<Void> deleteEvent(@PathVariable Integer id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return Result.failure(401, "未登录");
        }
        
        if (eventService.deleteEvent(id, user.getId())) {
            return Result.success("删除成功");
        } else {
            return Result.failure("删除失败");
        }
    }
    
    /**
     * 获取事件详情
     */
    @GetMapping("/info/{id}")
    public Result<Event> getEventInfo(@PathVariable Integer id) {
        Event event = eventService.getEventById(id);
        if (event != null) {
            return Result.success(event);
        } else {
            return Result.failure("事件不存在");
        }
    }
    
    /**
     * 更新事件状态
     */
    @PutMapping("/status/{id}/{status}")
    public Result<Void> updateEventStatus(@PathVariable Integer id, 
                                       @PathVariable Integer status, 
                                       HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return Result.failure(401, "未登录");
        }
        
        if (eventService.updateEventStatus(id, user.getId(), status)) {
            return Result.success("状态更新成功");
        } else {
            return Result.failure("状态更新失败");
        }
    }
    
    /**
     * 根据条件查询事件
     */
    @GetMapping("/search")
    public Result<List<Event>> searchEvents(
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) Integer priority,
            @RequestParam(required = false) Integer status,
            @RequestParam(required = false) String keyword,
            HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return Result.failure(401, "未登录");
        }
        
        List<Event> events = eventService.getEventsByCondition(
                user.getId(), categoryId, priority, status, keyword);
        return Result.success(events);
    }
} 