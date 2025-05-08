package org.kernel360.tang.seat;

import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeatService {

    private final SeatMapper seatMapper;

    public List<AvailableSeatDto> getAvailableSeat(int seatId, LocalDate date) {
        return seatMapper.getAvailableSeat(seatId, date);
    }
}
