package org.kernel360.tang.seatReservation.vo;

import lombok.Value;
import org.kernel360.tang.seatReservation.SeatReservationStatus;

import java.time.LocalDateTime;

@Value
public class SeatReservationVo {
    int resvId;
    int timeId;
    int memberId;
    SeatReservationStatus status;
    LocalDateTime reservedAt;
}
