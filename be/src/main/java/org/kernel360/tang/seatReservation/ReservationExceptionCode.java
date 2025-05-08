package org.kernel360.tang.seatReservation;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.kernel360.tang.common.ExceptionCode;
import org.kernel360.tang.common.LoggingLevel;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ReservationExceptionCode implements ExceptionCode {
    // @formatter:off
    RESERVATION_ALREADY_EXISTS("100", "예약이 이미 존재합니다.", HttpStatus.BAD_REQUEST, LoggingLevel.APP_WARN),
    RESERVATION_VALID_START_TIME_PASSED("101", "예약 가능 시간이 지났습니다.", HttpStatus.BAD_REQUEST, LoggingLevel.APP_WARN),
    ;
    // @formatter:on

    private static final String PREFIX = "RESV_";

    private final String code;
    private final String message;
    private final HttpStatusCode httpStatusCode;
    private final LoggingLevel loggingLevel;

    public String getPrefix() {
        return PREFIX;
    }
}
