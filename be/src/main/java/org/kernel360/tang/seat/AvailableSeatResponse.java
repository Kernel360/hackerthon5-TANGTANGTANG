package org.kernel360.tang.seat;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.time.LocalDateTime;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record AvailableSeatResponse(

        int seatId,

        int timeId,

        LocalDateTime startAt,

        LocalDateTime endAt
) {

    public static AvailableSeatResponse from(AvailableSeatDto availableSeatDto) {
        return new AvailableSeatResponse(
                availableSeatDto.seatId(),
                availableSeatDto.timeId(),
                availableSeatDto.startAt(),
                availableSeatDto.endAt()
        );
    }
}
