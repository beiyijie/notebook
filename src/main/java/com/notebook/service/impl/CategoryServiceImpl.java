package com.notebook.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.notebook.dao.CategoryDao;
import com.notebook.dao.EventDao;
import com.notebook.entity.Category;
import com.notebook.service.CategoryService;

/**
 * 分类服务实现类
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    
    @Autowired
    private CategoryDao categoryDao;
    
    @Autowired
    private EventDao eventDao;
    
    /**
     * 新增分类
     * @param category 分类对象
     * @return 操作结果，true成功，false失败
     */
    @Override
    public boolean addCategory(Category category) {
        // 检查分类名是否已存在
        if (checkNameExists(category.getName(), category.getUserId())) {
            return false;
        }
        return categoryDao.insert(category) > 0;
    }
    
    /**
     * 修改分类
     * @param category 分类对象
     * @return 操作结果，true成功，false失败
     */
    @Override
    public boolean updateCategory(Category category) {
        // 检查是否存在同名分类
        if (checkNameExists(category.getName(), category.getUserId())) {
            return false;
        }
        return categoryDao.update(category) > 0;
    }
    
    /**
     * 删除分类
     * @param id 分类ID
     * @param userId 用户ID
     * @return 操作结果，true成功，false失败
     */
    @Override
    @Transactional
    public boolean deleteCategory(Integer id, Integer userId) {
        // 查询分类下是否有事件
        List<com.notebook.entity.Event> events = eventDao.findByCategoryId(id);
        if (events != null && !events.isEmpty()) {
            // 将该分类下的所有事件的分类ID设为null
            for (com.notebook.entity.Event event : events) {
                event.setCategoryId(null);
                eventDao.update(event);
            }
        }
        // 删除分类
        return categoryDao.delete(id, userId) > 0;
    }
    
    /**
     * 根据ID查询分类
     * @param id 分类ID
     * @return 分类对象
     */
    @Override
    public Category getCategoryById(Integer id) {
        return categoryDao.findById(id);
    }
    
    /**
     * 查询用户的分类列表
     * @param userId 用户ID
     * @return 分类列表
     */
    @Override
    public List<Category> getCategoriesByUserId(Integer userId) {
        return categoryDao.findByUserId(userId);
    }

    @Override
    public boolean checkNameExists(String name, Integer userId) {
        return categoryDao.checkNameExist(name, userId) > 0;
    }
} 