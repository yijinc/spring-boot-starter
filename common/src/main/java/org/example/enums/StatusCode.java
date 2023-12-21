package org.example.enums;

/**
 * 业务响应状态码
 * **/
public enum StatusCode {
    SUCCESS(1, "操作成功"),
    NOT_FOUND(1404, "未找到资源"),
    ERROR(1500, "服务异常"),
    ;
    private final int code;
    private final String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    StatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
