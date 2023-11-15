package com.fish.myspringboot.response;

public enum ResultCode {
    SUCCESS(1, "请求成功"),
    ERROR(1500, "服务内部错误"),
    BAD_PARAM(1400, "请求参数错误"),
    UNAUTHORIZED(1401, "未授权的"),
    FORBIDDEN(1403, "拒绝访问"),
    NOT_FOUND(1404, "请求未找到");
    private final int code;
    private final String message;

    ResultCode(int code) {
        this(code, "");
    }

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}