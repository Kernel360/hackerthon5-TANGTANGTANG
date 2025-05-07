package org.kernel360.tang.common;

import org.springframework.http.HttpStatusCode;

public interface ExceptionCode {
    String getPrefix();

    String getCode();

    default String getFullCode() {
        return getPrefix() + getCode();
    }

    String getMessage();

    HttpStatusCode getHttpStatusCode();

    LoggingLevel getLoggingLevel();
}
