package org.kernel360.tang.seatReservation;

import lombok.RequiredArgsConstructor;
import org.kernel360.tang.common.Constants;
import org.kernel360.tang.seatReservation.dto.MultipleSeatRangeReservationRequest;
import org.kernel360.tang.seatReservation.dto.SeatReservationRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reserve")
@RequiredArgsConstructor
public class SeatReservationController {
    private final SeatReservationService seatReservationService;

    @PostMapping
    public void reserveSeats(
            @RequestBody SeatReservationRequest request,
            @RequestAttribute(Constants.SESSION_MEMBER_ID) Integer memberId
    ) {
        seatReservationService.reserveSeats(memberId, request);
    }

    @PostMapping("/multi-range")
    public void reserveMultiSeatRange(
            @RequestBody MultipleSeatRangeReservationRequest request,
            @RequestAttribute(Constants.SESSION_MEMBER_ID) Integer memberId
    ) {
        seatReservationService.reserveMultiSeatRange(memberId, request);
    }
}
