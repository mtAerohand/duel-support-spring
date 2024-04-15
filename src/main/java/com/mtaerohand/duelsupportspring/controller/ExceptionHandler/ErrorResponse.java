package com.mtaerohand.duelsupportspring.controller.ExceptionHandler;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * エラーレスポンス
 */
@Data
@AllArgsConstructor
public class ErrorResponse {
    private HttpStatus status;
    private String message;
}
