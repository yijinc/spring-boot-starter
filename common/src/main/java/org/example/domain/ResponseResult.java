package org.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.enums.StatusCode;

@Data
@AllArgsConstructor
public class ResponseResult<T> {
    private int code;
    private String message;
    private T data;

    public static <T> ResponseResult<T> ok(T data) {
        return new ResponseResult<T>(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), data);
    }

    public static ResponseResult<?> fail() {
        return new ResponseResult<>(StatusCode.ERROR.getCode(), StatusCode.ERROR.getMessage(), null);
    }

    public static ResponseResult<?> fail(String message) {
        return new ResponseResult<>(StatusCode.ERROR.getCode(), message, null);
    }

    public static ResponseResult<?> fail(int code, String message) {
        return new ResponseResult<>(code, message, null);
    }

    public static ResponseResult<?> fail(StatusCode status) {
        return new ResponseResult<>(status.getCode(), status.getMessage(), null);
    }

    public static <T> ResponseResult<T> fail(StatusCode status, T data) {
        return new ResponseResult<>(status.getCode(), status.getMessage(), data);
    }
}
