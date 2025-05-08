package org.kernel360.tang.seatReservation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kernel360.tang.common.AppException;
import org.kernel360.tang.seatReservation.dto.SeatReservationRequest;
import org.kernel360.tang.test.config.BaseIntegrationTest;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@MybatisTest
@DisplayName("[통합 테스트] 좌석 예약")
class SeatReservationServiceTest extends BaseIntegrationTest {
    @Autowired
    SeatReservationMapper seatReservationMapper;

    SeatReservationService seatReservationService;

    final int VALID_TIME_ID = 6;

    final int INVALID_TIME_ID = 1;

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
    @DisplayName("사용자의 예약 내역을 조회할 수 있어야 한다.")
    void findReservationOfMember() {
        // given
        var memberId = 1;

        // when
        var res = seatReservationService.findReservationOfMember(memberId);

        // then
        assertThat(res).isNotNull();
    }

    @Test
    @DisplayName("다른 사용자의 예약 내역을 조회할 수 있으면 안된다.")
    void findReservationOfOtherMember() {
        // given
        var memberId = 2;

        // when
        var res = seatReservationService.findReservationOfMember(memberId);

        // then
        res.forEach((r) -> {
            assertThat(r.getMemberId()).isEqualTo(memberId);
        });
    }

    @Test
    @DisplayName("단일 예약: TimeId가 올바른 경우 예약에 성공해야 한다.")
    void reserveSeats() {
        // given
        var memberId = 1;
        var timeIds = List.of(VALID_TIME_ID);
        var request = new SeatReservationRequest(timeIds);

        // when
        assertDoesNotThrow(() -> {
            seatReservationService.reserveSeats(memberId, request);
        });
    }

    @Test
    @DisplayName("단일 예약: 예약에 성공한 경우 예약 내역을 조회할 수 있어야 한다.")
    void findReservationOfMemberAfterReserve() {
        // given
        var memberId = 1;
        var timeIds = List.of(VALID_TIME_ID);
        var request = new SeatReservationRequest(timeIds);

        // when
        var before = seatReservationService.findReservationOfMember(memberId);
        seatReservationService.reserveSeats(memberId, request);
        var after = seatReservationService.findReservationOfMember(memberId);

        // then
        // before에 없는 after 요소 찾기
        var diff = after.stream()
                .filter(a -> before.stream()
                        .noneMatch(b -> b.getResvId() == a.getResvId())
                )
                .toList();

        assertThat(diff.size()).isEqualTo(timeIds.size());
        assertThat(diff.get(0).getTimeId()).isNotNull();
        assertThat(after.size()).isEqualTo(before.size() + timeIds.size());
    }

    @Test
    @DisplayName("단일 예약: 예약 가능 좌석이 아닐 경우 예약에 실패해야 한다.")
    void reserveSeatsNotAvailable() {
        // given
        var memberId = 1;
        var timeIds = List.of(INVALID_TIME_ID);
        var request = new SeatReservationRequest(timeIds);

        // when
        assertThrows(AppException.class, () -> {
            seatReservationService.reserveSeats(memberId, request);
        });
    }
}