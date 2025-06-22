package com.notebook.service;

import java.util.List;

import com.notebook.entity.Category;

/**
 * 分类服务接口
 */
public interface CategoryService {
    
    /**
     * 获取用户所有分类
     * @param userId 用户ID
     * @return 分类列表
     */
    List<Category> getCategoriesByUserId(Integer userId);
    
    /**
     * 根据ID获取分类
     * @param id 分类ID
     * @return 分类信息
     */
    Category getCategoryById(Integer id);
    
    /**
     * 添加分类
     * @param category 分类信息
     * @return 添加成功返回true，失败返回false
     */
    boolean addCategory(Category category);
    
    /**
     * 更新分类
     * @param category 分类信息
     * @return 更新成功返回true，失败返回false
     */
    boolean updateCategory(Category category);
    
    /**
     * 删除分类
     * @param id 分类ID
     * @param userId 用户ID（确保只能删除自己的分类）
     * @return 删除成功返回true，失败返回false
     */
    boolean deleteCategory(Integer id, Integer userId);
    
    /**
     * 检查分类名称是否存在
     * @param name 分类名称
     * @param userId 用户ID
     * @return 存在返回true，不存在返回false
     */
    boolean checkNameExists(String name, Integer userId);
} 