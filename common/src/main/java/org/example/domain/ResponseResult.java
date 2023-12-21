package org.example.domain;

import org.example.enums.StatusCode;

public class ResponseResult<T> {
    private int code;
    private String message;
    private T data;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResponseResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ResponseResult<T> ok(T data) {
        return new ResponseResult<T>(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), data);
    }

    public static ResponseResult<?> fail() {
        return new ResponseResult<>(StatusCode.ERROR.getCode(), StatusCode.ERROR.getMessage(), null);
    }

    public static ResponseResult<?> fail(String message) {
        return new ResponseResult<>(StatusCode.ERROR.getCode(), message, null);
    }

    public static ResponseResult<?> fail(int code, String message) {
        return new ResponseResult<>(code, message, null);
    }

    public static ResponseResult<?> fail(StatusCode status) {
        return new ResponseResult<>(status.getCode(), status.getMessage(), null);
    }
}
