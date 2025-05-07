package org.kernel360.tang.common;

import lombok.Getter;

@Getter
public class AppException extends RuntimeException {
    private final ExceptionCode code;

    AppException(ExceptionCode code) {
        super(code.getMessage());
        this.code = code;
    }
}
