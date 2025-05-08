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

    private static final int MAX_SELECT_TIME_HOUR = 3;

    private final SeatMapper seatMapper;

    public List<AvailableSeatDto> getAvailableSeat(LocalDateTime startAt, LocalDateTime endAt) {
        if (startAt.isAfter(endAt)) {
            throw new AppException(SeatExceptionCode.INVALID_TIME_RANGE);
        }

        long hours = Duration.between(startAt, endAt)
                .toHours();
        if (hours > MAX_SELECT_TIME_HOUR) {
            throw new AppException(SeatExceptionCode.EXCEEDS_MAX_SELECT_TIME);
        }

        return seatMapper.getAvailableSeatByPeriod(startAt, endAt);
    }
}
