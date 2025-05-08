package org.kernel360.tang.seatReservation;

import lombok.RequiredArgsConstructor;
import org.kernel360.tang.seatReservation.dto.SeatReservationRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/reserve")
@RequiredArgsConstructor
public class SeatReservationController {
    private final SeatReservationService seatReservationService;

    @PostMapping
    public void reserveSeats(@RequestBody SeatReservationRequest request) {
        // TODO: 이 부분은 실제로는 인증된 사용자의 ID를 가져와야 합니다.
        var memberId = 1;

        seatReservationService.reserveSeats(memberId, request);
    }
}
