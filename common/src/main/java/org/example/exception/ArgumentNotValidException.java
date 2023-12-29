package org.example.exception;

// 自定义处理401 参数异常
public class ArgumentNotValidException extends RuntimeException {
    public ArgumentNotValidException(String message) {
        super(message);
    }
}
