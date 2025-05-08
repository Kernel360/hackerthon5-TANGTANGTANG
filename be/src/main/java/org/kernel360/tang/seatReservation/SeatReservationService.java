package org.kernel360.tang.seatReservation;

import lombok.RequiredArgsConstructor;
import org.kernel360.tang.seatReservation.dto.SeatReservationRequest;
import org.kernel360.tang.seatReservation.vo.FindSeatReservation;
import org.kernel360.tang.seatReservation.vo.ReserveOneSeatVo;
import org.kernel360.tang.seatReservation.vo.SeatReservationVo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatReservationService {
    private final SeatReservationMapper seatReservationMapper;

    public List<SeatReservationVo> findReservationOfMember(Integer memberId) {
        var vo = FindSeatReservation.builder()
                .memberId(memberId)
                .status(SeatReservationStatus.RESERVED)
                .build();
        return seatReservationMapper.findReservationOfMember(vo);
    }

    public void reserveSeats(Integer memberId, SeatReservationRequest request) {
        var now = LocalDateTime.now();
        var vo = ReserveOneSeatVo.from(memberId, request, now);
        seatReservationMapper.reserveOneSeat(vo);
    }
}
