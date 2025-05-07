package org.kernel360.tang.common;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum CommonExceptionCode implements ExceptionCode {
    INTERNAL_SERVER_ERROR("SERVER_ERROR", "서버 오류", HttpStatus.INTERNAL_SERVER_ERROR),
    ;

    public static final String PREFIX = "COM";

    private final String code;
    private final String message;
    private final HttpStatus httpStatusCode;

    @Override
    public String getPrefix() {
        return PREFIX;
    }
}
