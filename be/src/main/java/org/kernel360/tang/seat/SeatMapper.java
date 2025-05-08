package org.kernel360.tang.seat;

import java.time.LocalDateTime;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SeatMapper {

    List<AvailableSeatDto> getAvailableSeatByPeriod(LocalDateTime startAt, LocalDateTime endAt);
}
