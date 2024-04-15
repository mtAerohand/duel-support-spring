package com.mtaerohand.duelsupportspring.controller.ExceptionHandler;

/**
 * カスタムエラー
 */
public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}
