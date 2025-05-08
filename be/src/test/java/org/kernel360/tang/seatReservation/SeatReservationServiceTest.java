package org.kernel360.tang.seatReservation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kernel360.tang.seatReservation.dto.SeatReservationRequest;
import org.kernel360.tang.test.config.BaseIntegrationTest;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(SpringExtension.class)
@MybatisTest
@DisplayName("[통합 테스트] 좌석 예약")
class SeatReservationServiceTest extends BaseIntegrationTest {
    @Autowired
    SeatReservationMapper seatReservationMapper;

    SeatReservationService seatReservationService;

    @BeforeEach
    void setUp() {
        seatReservationService = new SeatReservationService(
                seatReservationMapper
        );
    }

    @AfterEach
    void tearDown() {
        seatReservationService = null;
    }

    @Test
    @DisplayName("단일 예약: TimeId가 올바른 경우 예약에 성공해야 한다.")
    void reserveSeats() {
        // given
        var memberId = 1;
        var timeIds = List.of(1);
        var request = new SeatReservationRequest(timeIds);

        // when
        assertDoesNotThrow(() -> {
            seatReservationService.reserveSeats(memberId, request);
        });
    }
}