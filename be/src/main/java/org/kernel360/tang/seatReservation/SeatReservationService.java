package org.kernel360.tang.seatReservation;

import lombok.RequiredArgsConstructor;
import org.kernel360.tang.seatReservation.dto.SeatReservationRequest;
import org.kernel360.tang.seatReservation.vo.ReserveOneSeatVo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SeatReservationService {
    private final SeatReservationMapper seatReservationMapper;

    public void reserveSeats(Integer memberId, SeatReservationRequest request) {
        var now = LocalDateTime.now();
        var vo = ReserveOneSeatVo.from(memberId, request, now);
        seatReservationMapper.reserveOneSeat(vo);
    }
}
