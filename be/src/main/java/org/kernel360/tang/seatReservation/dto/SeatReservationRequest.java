package org.kernel360.tang.seatReservation.dto;

import java.util.List;

public record SeatReservationRequest(
        List<Integer> timeIds
) {
}
