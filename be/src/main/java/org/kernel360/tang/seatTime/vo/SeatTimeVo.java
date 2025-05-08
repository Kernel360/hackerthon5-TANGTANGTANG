package org.kernel360.tang.seatTime.vo;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class SeatTimeVo {
    Integer timeId;
    Integer seatId;
    LocalDateTime startDt;
    LocalDateTime endDt;
}
