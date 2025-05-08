package org.kernel360.tang.seat;

import java.time.LocalDateTime;
import java.util.List;
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
    public ResponseEntity<List<AvailableSeatResponse>> getAvailableSeats(
            @RequestParam("start_at")
            @DateTimeFormat(iso = ISO.DATE_TIME)
            LocalDateTime startAt,

            @RequestParam("end_at")
            @DateTimeFormat(iso = ISO.DATE_TIME)
            LocalDateTime endAt
    ) {
        List<AvailableSeatDto> availableSeatDtos = seatService.getAvailableSeat(startAt, endAt);
        List<AvailableSeatResponse> responses = availableSeatDtos.stream()
                .map(AvailableSeatResponse::from)
                .toList();

        return ResponseEntity.ok(responses);
    }
}
