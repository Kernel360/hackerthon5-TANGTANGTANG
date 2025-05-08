package org.kernel360.tang.seat;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.kernel360.tang.common.AppException;
import org.kernel360.tang.common.CommonExceptionCode;

@RequiredArgsConstructor
@Getter
public enum SeatReservationStatus {

    PENDING(0),
    CONFIRMED(1),
    CANCELED(2)
    ;

    private final int code;

    public static SeatReservationStatus fromCode(int code) {
        return switch (code) {
            case 0 -> PENDING;
            case 1 -> CONFIRMED;
            case 2 -> CANCELED;
            default -> throw new AppException(CommonExceptionCode.INTERNAL_SERVER_ERROR);
        };
    }
}
