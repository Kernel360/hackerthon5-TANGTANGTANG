package org.kernel360.tang.seatReservation.dto;

import java.time.LocalDateTime;
import java.util.List;

public record MultipleSeatRangeReservationRequest(
        List<SeatTimeRangeItem> items
) {
    public record SeatTimeRangeItem(
            Integer seatId,
            LocalDateTime startDt,
            LocalDateTime endDt
    ) {
    }
}
