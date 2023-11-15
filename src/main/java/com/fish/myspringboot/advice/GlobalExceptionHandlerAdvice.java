package com. fish. myspringboot. advice;

import com. fish. myspringboot. response. ResponseResult;
import com. fish. myspringboot. response. ResultCode;
import org. springframework. validation. FieldError;
import org. springframework. web. bind. MethodArgumentNotValidException;
import org. springframework. web. bind. annotation. ExceptionHandler;
import org. springframework. web. bind. annotation. RestControllerAdvice;

import java. util. Map;
import java. util. stream. Collectors;

@RestControllerAdvice
public class GlobalExceptionHandlerAdvice {

    @ExceptionHandler
    public ResponseResult<Map<String, String>> handleException(MethodArgumentNotValidException e) {
        Map<String, String> errors = e.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        return ResponseResult.error(ResultCode.BAD_PARAM, errors);
    }
}
