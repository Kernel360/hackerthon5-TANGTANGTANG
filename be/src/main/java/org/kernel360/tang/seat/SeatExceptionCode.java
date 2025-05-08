package org.kernel360.tang.seat;

import lombok.Getter;
import org.kernel360.tang.common.ExceptionCode;
import org.kernel360.tang.common.LoggingLevel;
import org.springframework.http.HttpStatus;

@Getter
public enum SeatExceptionCode implements ExceptionCode {

    INVALID_RESERVATION_TIME_RANGE("INVALID_RESERVATION_TIME_RANGE", "3개월 이상의 기간을 조회할 수 없습니다.", HttpStatus.BAD_REQUEST),
    ;

    public static final String PREFIX = "SEAT";

    private final String code;
    private final String message;
    private final HttpStatus httpStatusCode;
    private final LoggingLevel loggingLevel;

    @Override
    public String getPrefix() {
        return PREFIX;
    }

    SeatExceptionCode(String code, String message, HttpStatus httpStatusCode) {
        this.code = code;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
        this.loggingLevel = LoggingLevel.APP_ERROR;
    }
}
