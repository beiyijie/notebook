package com.notebook.util;

import java.io.Serializable;

/**
 * 统一响应结果类
 */
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Integer code;    // 状态码，200成功，其他失败
    private String message;  // 提示信息
    private T data;          // 响应数据
    
    private Result() {
    }
    
    private Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    
    private Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    
    /**
     * 成功返回结果
     */
    public static <T> Result<T> success() {
        return new Result<>(200, "操作成功");
    }
    
    /**
     * 成功返回结果
     * @param message 提示信息
     */
    public static <T> Result<T> success(String message) {
        return new Result<>(200, message);
    }
    
    /**
     * 成功返回结果
     * @param data 返回数据
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(200, "操作成功", data);
    }
    
    /**
     * 成功返回结果
     * @param message 提示信息
     * @param data 返回数据
     */
    public static <T> Result<T> success(String message, T data) {
        return new Result<>(200, message, data);
    }
    
    /**
     * 失败返回结果
     */
    public static <T> Result<T> failure() {
        return new Result<>(500, "操作失败");
    }
    
    /**
     * 失败返回结果
     * @param message 提示信息
     */
    public static <T> Result<T> failure(String message) {
        return new Result<>(500, message);
    }
    
    /**
     * 失败返回结果
     * @param code 状态码
     * @param message 提示信息
     */
    public static <T> Result<T> failure(Integer code, String message) {
        return new Result<>(code, message);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
} 