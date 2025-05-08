package org.kernel360.tang.seat;

import java.time.LocalDateTime;

public record SeatReservationDto(
        int reservationId,
        int memberId,
        int seatId,
        LocalDateTime startAt,
        LocalDateTime endAt,
        int statusCode,
        LocalDateTime reservedAt,
        LocalDateTime canceledAt
) {
}
