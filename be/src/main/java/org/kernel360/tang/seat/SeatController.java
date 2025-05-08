package org.kernel360.tang.seat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import java.time.LocalDate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ModelAttribute;
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
