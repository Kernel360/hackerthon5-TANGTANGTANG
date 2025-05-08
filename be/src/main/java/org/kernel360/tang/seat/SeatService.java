package org.kernel360.tang.seat;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.kernel360.tang.common.AppException;
import java.time.LocalDate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeatService {
    private static final int MAX_SELECT_TIME_HOUR = 3;
    public static final int MAX_RESERVATION_SELECT_TIME_DAY = 90;

    private final SeatMapper seatMapper;

    public List<AvailableSeatDto> getAvailableSeat(int seatId, LocalDate date) {
        return seatMapper.getAvailableSeat(seatId, date);
    }

    public Set<Integer> getAvailableSeatIds(LocalDateTime startAt, LocalDateTime endAt) {
        validateTimeRange(startAt, endAt);

        List<AvailableSeatDto> availableSeats = seatMapper.getAvailableSeatByPeriod(startAt, endAt);

        Map<Integer, List<AvailableSeatDto>> seatsBySeatId = availableSeats.stream()
                .collect(Collectors.groupingBy(AvailableSeatDto::seatId));

        // 정렬 및 연속성 필터링
        return seatsBySeatId.entrySet().stream()
                .peek(entry -> entry.getValue().sort(Comparator.comparing(AvailableSeatDto::startAt)))
                .filter(entry -> isContinuous(entry.getValue(), startAt, endAt))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }

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

    private void validateTimeRange(LocalDateTime startAt, LocalDateTime endAt) {
        if (startAt.isAfter(endAt)) {
            throw new AppException(SeatExceptionCode.INVALID_TIME_RANGE);
        }

        long hours = Duration.between(startAt, endAt).toHours();
        if (hours > MAX_SELECT_TIME_HOUR) {
            throw new AppException(SeatExceptionCode.EXCEEDS_MAX_SELECT_TIME);
        }
    }

    private boolean isContinuous(List<AvailableSeatDto> slots, LocalDateTime requiredStart, LocalDateTime requiredEnd) {
        if (slots.isEmpty()) return false;

        LocalDateTime expectedStart = requiredStart;

        for (AvailableSeatDto slot : slots) {
            if (!slot.startAt().isEqual(expectedStart)) {
                return false;
            }
            expectedStart = slot.endAt();
        }

        return expectedStart.isEqual(requiredEnd);
    }
}
