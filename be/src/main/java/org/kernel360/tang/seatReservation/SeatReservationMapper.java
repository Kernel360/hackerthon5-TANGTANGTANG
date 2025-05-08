package org.kernel360.tang.seatReservation;

import org.apache.ibatis.annotations.Mapper;
import org.kernel360.tang.seatReservation.vo.FindSeatReservation;
import org.kernel360.tang.seatReservation.vo.ReserveOneSeatVo;
import org.kernel360.tang.seatReservation.vo.SeatReservationVo;

import java.util.List;

@Mapper
public interface SeatReservationMapper {
    SeatReservationVo findById(Integer timeId);

    List<SeatReservationVo> findReservationOfMember(FindSeatReservation vo);

    void reserveOneSeat(ReserveOneSeatVo vo);

    int countByTimeIdAndStatusIn(Integer timeId, List<SeatReservationStatus> status);
}
