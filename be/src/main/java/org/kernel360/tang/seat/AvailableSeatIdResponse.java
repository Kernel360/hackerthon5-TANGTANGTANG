package org.kernel360.tang.seat;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record AvailableSeatIdResponse(

        int seatId
) {

    public static AvailableSeatIdResponse of(int seatId) {
        return new AvailableSeatIdResponse(seatId);
    }
}
