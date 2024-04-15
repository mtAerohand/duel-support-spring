package com.mtaerohand.duelsupportspring.controller.ExceptionHandler;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.AllArgsConstructor;

/**
 * 例外ハンドラクラス
 */
@ControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {
    private final Environment environment;

    /**
     * カスタムエラーのハンドラ
     *
     * @param ex カスタムエラー
     * @return エラーレスポンス
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleCustomException(Exception ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = environment.getProperty(ex.getMessage());
        ErrorResponse res = new ErrorResponse(status, message);
        return new ResponseEntity<ErrorResponse>(res, status);
    }

    /**
     * バリデーションエラーのハンドラ
     *
     * @param ex バリデーションエラー
     * @return エラーレスポンス
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = result.getAllErrors().get(0).getDefaultMessage();
        ErrorResponse res = new ErrorResponse(status, message);
        return new ResponseEntity<ErrorResponse>(res, status);
    }
}
