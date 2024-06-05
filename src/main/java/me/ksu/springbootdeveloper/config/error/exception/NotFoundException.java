package me.ksu.springbootdeveloper.config.error.exception;

import me.ksu.springbootdeveloper.config.error.ErrorCode;

public class NotFoundException extends BusinessBaseException{
    public NotFoundException(ErrorCode errorCode) {
        super(errorCode.getMessage(), errorCode);
    }

    public NotFoundException() {
        super(ErrorCode.NOT_FOUND);
    }
}
