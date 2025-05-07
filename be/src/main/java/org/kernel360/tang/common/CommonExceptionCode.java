package org.kernel360.tang.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum CommonExceptionCode implements ExceptionCode {
    NOT_FOUND("NOT_FOUND", "리소스를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    INTERNAL_SERVER_ERROR("SERVER_ERROR", "서버 오류", HttpStatus.INTERNAL_SERVER_ERROR),
    ;

    public static final String PREFIX = "COM";

    private final String code;
    private final String message;
    private final HttpStatus httpStatusCode;
    private final LoggingLevel loggingLevel;

    @Override
    public String getPrefix() {
        return PREFIX;
    }

    CommonExceptionCode(String code, String message, HttpStatus httpStatusCode) {
        this.code = code;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
        this.loggingLevel = LoggingLevel.INTERNAL_ERROR;
    }
}
