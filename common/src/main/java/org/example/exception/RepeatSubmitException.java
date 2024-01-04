package org.example.exception;

import org.example.enums.StatusCode;

public class RepeatSubmitException  extends CommonException {

    public RepeatSubmitException() {
        super(StatusCode.REPEAT_SUBMIT);
    }
    public RepeatSubmitException(String message) {
        super(StatusCode.REPEAT_SUBMIT.getCode(), message);
    }
}
