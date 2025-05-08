package org.kernel360.tang.seat;

import lombok.Getter;
import org.kernel360.tang.common.ExceptionCode;
import org.kernel360.tang.common.LoggingLevel;
import org.springframework.http.HttpStatus;

@Getter
public enum SeatExceptionCode implements ExceptionCode {

    INVALID_TIME_RANGE("INVALID_TIME_RANGE", "시작 시간은 종료 시간보다 이후일 수 없습니다.", HttpStatus.BAD_REQUEST),
    EXCEEDS_MAX_SELECT_TIME("EXCEEDS_MAX_SELECT_TIME", "예약 가능 최대 시간(3시간)을 초과했습니다.", HttpStatus.BAD_REQUEST),
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
