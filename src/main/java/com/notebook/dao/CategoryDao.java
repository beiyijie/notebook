package com.notebook.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.notebook.entity.Category;

/**
 * 分类Dao接口
 */
public interface CategoryDao {
    /**
     * 通过用户ID查询该用户的所有分类
     * @param userId 用户ID
     * @return 分类列表
     */
    List<Category> findByUserId(Integer userId);
    
    /**
     * 通过ID查询分类
     * @param id 分类ID
     * @return 分类对象
     */
    Category findById(Integer id);
    
    /**
     * 添加分类
     * @param category 分类对象
     * @return 影响的行数
     */
    int insert(Category category);
    
    /**
     * 修改分类
     * @param category 分类对象
     * @return 影响的行数
     */
    int update(Category category);
    
    /**
     * 删除分类
     * @param id 分类ID
     * @param userId 用户ID（确保只能删除自己的分类）
     * @return 影响的行数
     */
    int delete(@Param("id") Integer id, @Param("userId") Integer userId);
    
    /**
     * 检查分类名称是否存在
     * @param name 分类名称
     * @param userId 用户ID
     * @return 数量
     */
    int checkNameExist(@Param("name") String name, @Param("userId") Integer userId);
    /**
     *  通过用户ID和分类名称查询分类ID
     *  @param userId 用户ID
     *  @param name 分类名称
     *  @return 分类ID
     */
    int selectByUserIdAndName(@Param("userId") Integer userId, @Param("name") String name);
    /**
     * 通过分类ID查询该分类下的事件数量
     * @param categoryId 分类ID
     * @return 事件数量
     */
    int countEventByCategoryId(@Param("categoryId") Integer categoryId);

} 