package org.kernel360.tang.seat;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum SeatReservationStatus {
    PENDING(1),     // 예약중
    CONFIRMED(2),   // 예약됨
    CANCELED(3);    // 취소됨

    private final int code;

    public static SeatReservationStatus fromCode(int code) {
        return switch (code) {
            case 1 -> PENDING;
            case 2 -> CONFIRMED;
            case 3 -> CANCELED;
            default -> throw new IllegalArgumentException("Unknown status code: " + code);
        };
    }
}
