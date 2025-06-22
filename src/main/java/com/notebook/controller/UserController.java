package com.notebook.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.notebook.entity.User;
import com.notebook.service.UserService;
import com.notebook.util.Result;

/**
 * 用户控制器
 */
@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    /**
     * 用户登录
     */
    @PostMapping("/login")
    public Result<User> login(@RequestParam String username, 
                            @RequestParam String password,
                            HttpSession session) {
        User user = userService.login(username, password);
        if (user != null) {
            // 登录成功，将用户信息存入session
            session.setAttribute("user", user);
            return Result.success(user);
        } else {
            return Result.failure("用户名或密码错误");
        }
    }
    
    /**
     * 用户注册
     */
    @PostMapping("/register")
    public Result<Void> register(@RequestBody User user) {
        // 检查用户名是否存在
        if (userService.checkUsernameExists(user.getUsername())) {
            return Result.failure("用户名已存在");
        }
        
        if (userService.register(user)) {
            return Result.success("注册成功");
        } else {
            return Result.failure("注册失败");
        }
    }
    
    /**
     * 检查用户名是否存在
     */
    @GetMapping("/checkUsername")
    public Result<Boolean> checkUsername(@RequestParam String username) {
        boolean exists = userService.checkUsernameExists(username);
        return Result.success(exists);
    }
    
    /**
     * 获取当前登录用户信息
     */
    @GetMapping("/info")
    public Result<User> getUserInfo(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            return Result.success(user);
        } else {
            return Result.failure(401, "未登录");
        }
    }
    
    /**
     * 用户退出
     */
    @GetMapping("/logout")
    public Result<Void> logout(HttpSession session) {
        session.invalidate();
        return Result.success("退出成功");
    }
    
    /**
     * 修改用户信息
     */
    @PutMapping("/update")
    public Result<Void> updateUser(@RequestBody User user, HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return Result.failure(401, "未登录");
        }
        
        user.setId(currentUser.getId());
        if (userService.updateUser(user)) {
            // 更新session中的用户信息
            currentUser.setEmail(user.getEmail());
            session.setAttribute("user", currentUser);
            return Result.success("更新成功");
        } else {
            return Result.failure("更新失败");
        }
    }
    
    /**
     * 修改密码
     */
    @PutMapping("/password")
    public Result<Void> updatePassword(@RequestParam String oldPassword,
                                    @RequestParam String newPassword,
                                    HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return Result.failure(401, "未登录");
        }
        
        if (userService.updatePassword(currentUser.getId(), oldPassword, newPassword)) {
            // 清除session，重新登录
            session.invalidate();
            return Result.success("密码修改成功，请重新登录");
        } else {
            return Result.failure("原密码错误");
        }
    }
    
    /**
     * 重置密码（管理员功能）
     */
    @PutMapping("/resetPassword")
    public Result<Void> resetPassword(@RequestParam Integer userId,
                                   @RequestParam String newPassword,
                                   HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return Result.failure(401, "未登录");
        }
        
        // 这里简单处理，后续可以添加管理员权限验证
        if (userService.resetPassword(userId, newPassword)) {
            return Result.success("密码重置成功");
        } else {
            return Result.failure("密码重置失败");
        }
    }
} 