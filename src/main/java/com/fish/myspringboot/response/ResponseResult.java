package com.fish.myspringboot.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ResponseResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private int code;
    private boolean success;
    private String message;
    private T data;

    public static ResponseResult success() {
        return new ResponseResult(ResultCode.SUCCESS.getCode(), true, ResultCode.SUCCESS.getMessage(), null);
    }
    public static ResponseResult success(String message) {
        return new ResponseResult(ResultCode.SUCCESS.getCode(), true, message, null);
    }
    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult(ResultCode.SUCCESS.getCode(), true, ResultCode.SUCCESS.getMessage(), data);
    }

    public static ResponseResult error() {
        return new ResponseResult(ResultCode.ERROR.getCode(), false, ResultCode.ERROR.getMessage(), null);
    }

    public static ResponseResult error(String message) {
        return new ResponseResult(ResultCode.ERROR.getCode(), false, message, null);
    }

    public static ResponseResult error(int code, String message) {
        return new ResponseResult(code, false, message, null);
    }

    public static <T> ResponseResult<T> error(String message, T data) {
        return new ResponseResult(ResultCode.ERROR.getCode(), false, message, data);
    }
}