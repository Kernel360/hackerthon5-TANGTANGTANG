package org.kernel360.tang.seatReservation;

import lombok.RequiredArgsConstructor;
import org.kernel360.tang.common.AppException;
import org.kernel360.tang.common.TimeProvider;
import org.kernel360.tang.seatReservation.dto.SeatReservationRequest;
import org.kernel360.tang.seatReservation.vo.FindSeatReservation;
import org.kernel360.tang.seatReservation.vo.ReserveOneSeatVo;
import org.kernel360.tang.seatReservation.vo.SeatReservationVo;
import org.kernel360.tang.seatTime.SeatTimeMapper;
import org.kernel360.tang.seatTime.vo.SeatTimeVo;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatReservationService {
    private final TimeProvider timeProvider;

    private final SeatTimeMapper seatTimeMapper;
    private final SeatReservationMapper seatReservationMapper;

    public List<SeatReservationVo> findReservationOfMember(Integer memberId) {
        var vo = FindSeatReservation.builder()
                .memberId(memberId)
                .status(SeatReservationStatus.RESERVED)
                .build();
        return seatReservationMapper.findReservationOfMember(vo);
    }

    public void reserveSeats(Integer memberId, SeatReservationRequest request) {
        var now = timeProvider.now();

        var seatTime = seatTimeMapper.findById(request.timeId());
        validateTimeId(request.timeId());
        validateTimeStartDt(seatTime, now);

        var vo = ReserveOneSeatVo.from(memberId, request, now);
        seatReservationMapper.reserveOneSeat(vo);
    }

    private void validateTimeId(Integer timeId) {
        var invalidStatusList = List.of(
                SeatReservationStatus.RESERVING,
                SeatReservationStatus.RESERVED
        );
        var cnt = seatReservationMapper.countByTimeIdAndStatusIn(timeId, invalidStatusList);

        if (cnt > 0) {
            throw new AppException(ReservationExceptionCode.RESERVATION_ALREADY_EXISTS);
        }
    }

    private void validateTimeStartDt(SeatTimeVo seatTime, LocalDateTime now) {
        var startDt = seatTime.getStartDt();
        if (startDt.isAfter(now) && startDt.minus(Constants.RESERVATION_TIME_LIMIT).isBefore(now)) {
            throw new AppException(ReservationExceptionCode.RESERVATION_VALID_START_TIME_PASSED);
        }
    }

    static class Constants {
        static final Duration RESERVATION_TIME_LIMIT = Duration.ofMinutes(10);
    }
}
