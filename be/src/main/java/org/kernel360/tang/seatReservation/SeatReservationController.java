package org.kernel360.tang.seatReservation;

import lombok.RequiredArgsConstructor;
import org.kernel360.tang.common.Constants;
import org.kernel360.tang.seatReservation.dto.SeatReservationRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
