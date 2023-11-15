package com.fish.myspringboot.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseResult<T> {
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

    public static ResponseResult error(ResultCode resultCode) {
        return new ResponseResult(resultCode.getCode(), false, resultCode.getMessage(), null);
    }

    public static <T> ResponseResult<T> error(ResultCode resultCode, T data) {
        return new ResponseResult(resultCode.getCode(), false, resultCode.getMessage(), data);
    }
}