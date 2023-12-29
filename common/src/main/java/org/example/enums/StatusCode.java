package org.example.enums;

import lombok.Getter;

/**
 * 业务响应状态码，like HttpStatus
 * **/
@Getter
public enum StatusCode {
    SUCCESS(1, "请求成功"),
    ERROR(999, "请求失败"),
    BAD_PARAM(400, "请求参数错误"),
    UNAUTHORIZED(401, "认证失败"),
    ACCESS_FORBIDDEN(403, "拒绝访问"),
    NOT_FOUND(404, "路径未找到"),
    METHOD_NOT_ALLOWED(405, "请求方法不支持"),
    REQUEST_TIMEOUT(408, "请求超时"),
    INTERNAL_SERVER_ERROR(500, "内部服务错误"),
    NOT_IMPLEMENTED(501, "接口未实现"),
    BAD_GATEWAY(502, "服务暂无法响应"),
    ;
    private final int code;
    private final String message;

    StatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
