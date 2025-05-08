package org.kernel360.tang.seat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/seats")
@RequiredArgsConstructor
public class SeatController {

    private final SeatService seatService;

    @GetMapping("/available-times")
    public ResponseEntity<List<AvailableSeatIdResponse>> getAvailableSeats(
            @RequestParam("start_at")
            @DateTimeFormat(iso = ISO.DATE_TIME)
            LocalDateTime startAt,

            @RequestParam("end_at")
            @DateTimeFormat(iso = ISO.DATE_TIME)
            LocalDateTime endAt
    ) {
        Set<Integer> seatIds = seatService.getAvailableSeatIds(startAt, endAt);
        List<AvailableSeatIdResponse> responses = seatIds.stream()
                .map(AvailableSeatIdResponse::of)
                .toList();

        return ResponseEntity.ok(responses);
    }
}
