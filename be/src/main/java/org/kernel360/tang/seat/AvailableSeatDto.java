package org.kernel360.tang.seat;

import java.time.LocalDateTime;

public record AvailableSeatDto(

        int seatId,

        int timeId,

        LocalDateTime startAt,

        LocalDateTime endAt,

        int statusCode,

        LocalDateTime reservedAt,

        LocalDateTime canceledAt

) {
    public SeatReservationStatus getStatus() {
        return SeatReservationStatus.fromCode(statusCode);
    }
}
