package org.kernel360.tang.seat;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/seats")
@RequiredArgsConstructor
public class SeatController {

    private final SeatService seatService;

    @GetMapping
    public ResponseEntity<List<SeatReservationResponse>> getUserReservations(
            @ModelAttribute UserReservationGetRequest request
    ) {
        List<SeatReservationDto> userReservations = seatService.getUserReservations(
                1,  // todo: 인가 방식 정해진 후 변경
                request.status(),
                request.startDate(),
                request.endDate()
        );

        List<SeatReservationResponse> response = userReservations.stream().map(SeatReservationResponse::from)
                .toList();

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
}
