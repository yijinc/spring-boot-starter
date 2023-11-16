package com.fish.myspringboot.advice;

import com.fish.myspringboot.response.ResponseResult;
import com.fish.myspringboot.response.ResultCode;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandlerAdvice {

    /**
     * 处理参数校验不通过
     * */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseResult<Map<String, String>> handleException(MethodArgumentNotValidException e) {
        Map<String, String> errors = e.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        return ResponseResult.error(ResultCode.BAD_PARAM, errors);
    }

    /**
     * 处理 400
     * */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseResult handleException(HttpMessageNotReadableException e) {
        return ResponseResult.error(ResultCode.BAD_PARAM);
    }

    /**
     * 处理 404
     * 404错误是不经过Controller的，所以使用@ControllerAdvice无法获取到404错误
     * */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseResult handleException(NoHandlerFoundException e) {
        return ResponseResult.error(ResultCode.NOT_FOUND);
    }

    /**
     * 处理 405
     * */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseResult handleException(HttpRequestMethodNotSupportedException e) {
        return ResponseResult.error(ResultCode.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler
    public ResponseResult handleException(Exception e) {
        return ResponseResult.error(ResultCode.ERROR);
    }
}
