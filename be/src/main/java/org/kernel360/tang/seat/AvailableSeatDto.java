package org.kernel360.tang.seat;

import java.time.LocalDateTime;

public record AvailableSeatDto(

        int timeId,

        int seatId,

        LocalDateTime startAt,

        LocalDateTime endAt
) {
}
