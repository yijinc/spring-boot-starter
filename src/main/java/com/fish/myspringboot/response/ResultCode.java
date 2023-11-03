package com.fish.myspringboot.response;

public enum ResultCode {
    SUCCESS(1, "请求成功"),
    ERROR(0, "请求失败");
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