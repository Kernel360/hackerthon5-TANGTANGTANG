package org.kernel360.tang.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@RestControllerAdvice
public class AppExceptionAdvice {
    @ExceptionHandler(AppException.class)
    public ResponseEntity<AppErrorResponse> handleAppException(AppException e) {
        var code = e.getCode();
        var body = AppErrorResponse.from(code);

        doLogging(code);

        return ResponseEntity
                .status(code.getHttpStatusCode())
                .body(body);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<AppErrorResponse> handleNoHandlerFoundException(NoHandlerFoundException e) {
        var code = CommonExceptionCode.NOT_FOUND;
        var body = AppErrorResponse.from(code);

        doLogging(code);

        return ResponseEntity
                .status(code.getHttpStatusCode())
                .body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<AppErrorResponse> handleException(Exception e) {
        var code = CommonExceptionCode.INTERNAL_SERVER_ERROR;
        var body = AppErrorResponse.from(code);

        doLogging(code);

        return ResponseEntity
                .status(code.getHttpStatusCode())
                .body(body);
    }

    private void doLogging(ExceptionCode code) {
        switch (code.getLoggingLevel()) {
            case DEBUG -> {
                log.debug(code.getMessage());
            }
            case APP_WARN -> {
                log.warn(code.getMessage());
            }
            case APP_ERROR, INTERNAL_ERROR -> {
                log.error(code.getMessage());
            }
        }
    }
}
