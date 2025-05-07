package org.kernel360.tang.common;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionAdvice {
    @ExceptionHandler
    public ResponseEntity<AppErrorResponse> handleAppException(AppException e) {
        var code = e.getCode();
        var body = AppErrorResponse.from(code);
        return ResponseEntity
                .status(code.getHttpStatusCode())
                .body(body);
    }
}
