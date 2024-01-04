package org.example.exception;

import org.example.enums.StatusCode;

// 自定义处理401 参数异常
public class ArgumentNotValidException extends CommonException {
    public ArgumentNotValidException() {
        super(StatusCode.UNAUTHORIZED);
    }
    public ArgumentNotValidException(String message) {
        super(StatusCode.UNAUTHORIZED.getCode(), message);
    }
}
