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
import org.springframework.web.bind.annotation.RestController;

import com.notebook.entity.Category;
import com.notebook.entity.User;
import com.notebook.service.CategoryService;
import com.notebook.util.Result;

/**
 * 分类控制器
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;
    
    /**
     * 获取当前用户的所有分类
     */
    @GetMapping("/list")
    public Result<List<Category>> getCategoryList(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return Result.failure(401, "未登录");
        }
        List<Category> categories = categoryService.getCategoriesByUserId(user.getId());
        return Result.success(categories);
    }
    
    /**
     * 添加分类
     */
    @PostMapping("/add")
    public Result<Category> addCategory(@RequestBody Category category, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return Result.failure(401, "未登录");
        }
        
        category.setUserId(user.getId());
        if (categoryService.addCategory(category)) {
            return Result.success(category);
        } else {
            return Result.failure("添加失败，分类名称可能已存在");
        }
    }
    
    /**
     * 更新分类
     */
    @PutMapping("/update")
    public Result<Void> updateCategory(@RequestBody Category category, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return Result.failure(401, "未登录");
        }
        
        category.setUserId(user.getId());
        if (categoryService.updateCategory(category)) {
            return Result.success("更新成功");
        } else {
            return Result.failure("更新失败，分类名称可能已存在");
        }
    }
    
    /**
     * 删除分类
     */
    @DeleteMapping("/delete/{id}")
    public Result<Void> deleteCategory(@PathVariable Integer id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return Result.failure(401, "未登录");
        }
        
        if (categoryService.deleteCategory(id, user.getId())) {
            return Result.success("删除成功");
        } else {
            return Result.failure("删除失败");
        }
    }
    
    /**
     * 获取分类详情
     */
    @GetMapping("/info/{id}")
    public Result<Category> getCategoryInfo(@PathVariable Integer id) {
        Category category = categoryService.getCategoryById(id);
        if (category != null) {
            return Result.success(category);
        } else {
            return Result.failure("分类不存在");
        }
    }
} 