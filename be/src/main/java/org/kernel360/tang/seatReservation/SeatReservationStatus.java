package org.kernel360.tang.seatReservation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.kernel360.tang.common.IntCodeEnum;

@Getter
@AllArgsConstructor
public enum SeatReservationStatus implements IntCodeEnum {
    // @formatter:off
    IDLE      (0, "예약 가능"),
    RESERVING (1, "예약 중"  ),
    RESERVED  (2, "예약 완료"),
    ;
    // @formatter:on

    private final int code;
    private final String description;
}
