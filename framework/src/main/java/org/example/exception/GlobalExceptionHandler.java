package org.example.exception;

import org.example.domain.ResponseResult;
import org.example.enums.StatusCode;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * 只有 400、500 错误调用 ResponseResult.fail(StatusCode, Data)，详细错误信息在 data 字段中，
 * 其他错误信息都在 message 中
 * */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理参数校验不通过
     * */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseResult<Map<String, String>> handleException(MethodArgumentNotValidException e) {
        Map<String, String> errors = e.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        return ResponseResult.fail(StatusCode.BAD_PARAM, errors);
    }

    /**
     * 处理 400
     * */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseResult<?> handleException(HttpMessageNotReadableException e) {
        return ResponseResult.fail(StatusCode.BAD_PARAM, e);
    }

    /**
     * 处理 401
     * */
    @ExceptionHandler(AuthenticationException.class)
    public ResponseResult<?> handleException(AuthenticationException e) {
        return ResponseResult.fail(StatusCode.UNAUTHORIZED.getCode(), e.getMessage());
    }

    /**
     * 处理 403
     * */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseResult<?> handleException(AccessDeniedException e) {
        return ResponseResult.fail(StatusCode.ACCESS_FORBIDDEN.getCode(), e.getMessage());
    }

    /**
     * 处理 404
     * 404错误是不经过Controller的，所以使用@ControllerAdvice无法获取到404错误
     * */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseResult<?> handleException(NoHandlerFoundException e) {
        return ResponseResult.fail(StatusCode.NOT_FOUND);
    }

    /**
     * 处理 405
     * */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseResult<?> handleException(HttpRequestMethodNotSupportedException e) {
        return ResponseResult.fail(StatusCode.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler
    public ResponseResult<String> handleException(Exception e) {
        if (e instanceof ArgumentNotValidException) {
            return ResponseResult.fail(StatusCode.BAD_PARAM, e.getMessage());
        }
        if (e instanceof CommonException) {
            ResponseResult.fail(StatusCode.ERROR.getCode(), e.getMessage());
        }
        return ResponseResult.fail(StatusCode.INTERNAL_SERVER_ERROR, e.getMessage());
    }
}
