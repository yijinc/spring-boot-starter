package org.example.exception;

public class RepeatSubmitException  extends RuntimeException {
    public RepeatSubmitException(String message) {
        super(message);
    }
}
