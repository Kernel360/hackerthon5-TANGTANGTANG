package org.kernel360.tang.seat;

import java.time.LocalDateTime;

public record UserReservationGetRequest(

        SeatReservationStatus status,
        LocalDateTime startDate,
        LocalDateTime endDate
) {
}
