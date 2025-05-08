package org.kernel360.tang.seatReservation.vo;

import lombok.Builder;
import lombok.Value;
import org.kernel360.tang.seatReservation.SeatReservationStatus;

@Value
@Builder
public class FindSeatReservation {
    Integer memberId;
    SeatReservationStatus status;
}
