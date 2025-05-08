package org.kernel360.tang.seat;

import java.time.LocalDateTime;

public record SeatReservationResponse(
        int reservationId,
        int seatId,
        int memberId,
        SeatReservationStatus status,
        LocalDateTime reservedAt,
        LocalDateTime canceledAt
) {

    public static SeatReservationResponse from(SeatReservationDto seatReservationDto) {
        return new SeatReservationResponse(
                seatReservationDto.reservationId(),
                seatReservationDto.seatId(),
                seatReservationDto.memberId(),
                SeatReservationStatus.fromCode(seatReservationDto.statusCode()),
                seatReservationDto.reservedAt(),
                seatReservationDto.canceledAt()
        );
    }
}
