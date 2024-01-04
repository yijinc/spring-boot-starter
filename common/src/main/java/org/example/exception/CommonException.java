package org.example.exception;

import org.example.enums.StatusCode;

/***
 * 自定义公共异常类
 * 默认错误信息 StatusCode.ERROR
 * { code: 999, message: "请求失败" }
 * */
public class CommonException extends RuntimeException {
    private final int code;

    public CommonException() {
        super(StatusCode.ERROR.getMessage());
        this.code = StatusCode.ERROR.getCode();
    }


    public CommonException(String message) {
        super(message);
        this.code = StatusCode.ERROR.getCode();
    }

    public CommonException(int code, String message) {
        super(message);
        this.code = code;
    }

    public CommonException(StatusCode statusCode) {
        super(statusCode.getMessage());
        this.code = statusCode.getCode();
    }

    public int getCode() {
        return code;
    }
}
