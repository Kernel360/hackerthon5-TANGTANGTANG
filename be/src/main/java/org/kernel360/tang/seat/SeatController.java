package org.kernel360.tang.seat;

import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/seats")
@RequiredArgsConstructor
public class SeatController {

    private final SeatService seatService;

    @GetMapping("/{seatId}/available-times")
    public ResponseEntity<List<AvailableSeatResponse>> getAvailableSeats(
            @PathVariable int seatId,

            @RequestParam("date")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date
    ) {
        List<AvailableSeatDto> availableSeatDtos = seatService.getAvailableSeat(seatId, date);
        List<AvailableSeatResponse> responses = availableSeatDtos.stream()
                .map(AvailableSeatResponse::from)
                .toList();

        return ResponseEntity.ok(responses);
    }
}
