package org.kernel360.tang.seatReservation;

import org.apache.ibatis.annotations.Mapper;
import org.kernel360.tang.seatReservation.vo.ReserveOneSeatVo;

@Mapper
public interface SeatReservationMapper {
    void reserveOneSeat(ReserveOneSeatVo vo);
}
