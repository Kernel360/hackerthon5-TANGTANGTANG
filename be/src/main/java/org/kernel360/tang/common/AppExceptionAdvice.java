package org.kernel360.tang.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class AppExceptionAdvice {
    @ExceptionHandler
    public ResponseEntity<AppErrorResponse> handleAppException(AppException e) {
        var code = e.getCode();
        var body = AppErrorResponse.from(code);

        if (e.getCode().shouldBeLogged()) {
            log.error("AppException: ", e);
        } else {
            log.debug("AppException: ", e);
        }

        return ResponseEntity
                .status(code.getHttpStatusCode())
                .body(body);
    }

    @ExceptionHandler
    public ResponseEntity<AppErrorResponse> handleException(Exception e) {
        log.error("Internal server error occurred", e);

        var code = CommonExceptionCode.INTERNAL_SERVER_ERROR;
        var body = AppErrorResponse.from(code);
        return ResponseEntity
                .status(code.getHttpStatusCode())
                .body(body);
    }
}
