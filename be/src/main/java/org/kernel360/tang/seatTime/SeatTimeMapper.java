package org.kernel360.tang.seatTime;

import org.apache.ibatis.annotations.Mapper;
import org.kernel360.tang.seatTime.vo.SeatTimeVo;

@Mapper
public interface SeatTimeMapper {
    SeatTimeVo findById(Integer timeId);
}
