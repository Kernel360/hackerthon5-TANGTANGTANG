package org.kernel360.tang.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum AuthExceptionCode implements ExceptionCode {
    INVALID_LOGIN("INVALID_LOGIN", "로그인 정보가 올바르지 않습니다.", HttpStatus.UNAUTHORIZED),
    INVALID_AUTHORIZED("INVALID_AUTHORIZED", "인증 정보가 올바르지 않습니다.", HttpStatus.UNAUTHORIZED),
    ;

    public static final String PREFIX = "AUTH";

    private final String code;
    private final String message;
    private final HttpStatus httpStatusCode;
    private final LoggingLevel loggingLevel;

    @Override
    public String getPrefix() {
        return PREFIX;
    }

    AuthExceptionCode(String code, String message, HttpStatus httpStatusCode) {
        this.code = code;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
        this.loggingLevel = LoggingLevel.INTERNAL_ERROR;
    }
}
