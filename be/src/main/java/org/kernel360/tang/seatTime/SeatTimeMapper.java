package org.kernel360.tang.seatTime;

import org.apache.ibatis.annotations.Mapper;
import org.kernel360.tang.seatTime.vo.SeatTimeVo;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface SeatTimeMapper {
    SeatTimeVo findById(Integer timeId);

    List<SeatTimeVo> findBySeatIdAndDateBetween(Integer seatId, LocalDateTime startDt, LocalDateTime endDt);
}
