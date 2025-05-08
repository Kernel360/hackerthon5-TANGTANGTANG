package org.kernel360.tang.seatReservation.vo;

import lombok.Value;
import org.kernel360.tang.seatReservation.SeatReservationStatus;
import org.kernel360.tang.seatReservation.dto.SeatReservationRequest;

import java.time.LocalDateTime;

@Value
public class ReserveOneSeatVo {
    Integer timeId;
    Integer memberId;
    SeatReservationStatus status;
    LocalDateTime reservedAt;

    public static ReserveOneSeatVo from(Integer memberId, SeatReservationRequest request, LocalDateTime reservedAt) {
        return new ReserveOneSeatVo(
                request.timeId(),
                memberId,
                SeatReservationStatus.RESERVED,
                reservedAt
        );
    }
}
