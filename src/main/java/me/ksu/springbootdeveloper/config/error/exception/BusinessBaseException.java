package me.ksu.springbootdeveloper.config.error.exception;

import me.ksu.springbootdeveloper.config.error.ErrorCode;

//비즈니스 로직 작성 중 발생하는 예외를 모아둘 최상위 클래스
public class BusinessBaseException extends RuntimeException{
    private final ErrorCode errorCode;

    public BusinessBaseException(String message, ErrorCode errorCode) {
         super(message);
         this.errorCode = errorCode;
    }

    public BusinessBaseException(ErrorCode errorCode) {
         super(errorCode.getMessage());
         this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
