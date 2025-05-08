package org.kernel360.tang.seat;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.kernel360.tang.common.AppException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeatService {

    public static final int MAX_RESERVATION_SELECT_TIME_DAY = 90;
    private final SeatMapper seatMapper;

    List<SeatReservationDto> getUserReservations(
            Integer memberId,
            SeatReservationStatus status,
            LocalDateTime startDate,
            LocalDateTime endDate
    ) {
        if (Duration.between(startDate, endDate).toDays() > MAX_RESERVATION_SELECT_TIME_DAY) {
            throw new AppException(SeatExceptionCode.INVALID_RESERVATION_TIME_RANGE);
        }

        return seatMapper.getUserReservations(memberId, status.getCode(), startDate, endDate);
    }
}
