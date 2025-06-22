package com.notebook.entity;

import java.util.Date;

/**
 * 分类实体类
 */
public class Category {
    private Integer id;
    private String name;
    private Integer userId;
    private Date createTime;

    public Category() {
    }

    public Category(String name, Integer userId) {
        this.name = name;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userId=" + userId +
                ", createTime=" + createTime +
                '}';
    }
} 