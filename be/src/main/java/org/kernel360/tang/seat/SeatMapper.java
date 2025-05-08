package org.kernel360.tang.seat;

import java.time.LocalDate;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SeatMapper {

    List<AvailableSeatDto> getAvailableSeat(int seatId, LocalDate date);
}
