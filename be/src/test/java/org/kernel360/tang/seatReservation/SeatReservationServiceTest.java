package org.kernel360.tang.seatReservation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kernel360.tang.common.AppException;
import org.kernel360.tang.common.TimeProvider;
import org.kernel360.tang.seatReservation.dto.MultipleSeatRangeReservationRequest;
import org.kernel360.tang.seatReservation.dto.SeatReservationRequest;
import org.kernel360.tang.seatTime.SeatTimeMapper;
import org.kernel360.tang.test.config.BaseIntegrationTest;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@MybatisTest
@DisplayName("[통합 테스트] 좌석 예약")
class SeatReservationServiceTest extends BaseIntegrationTest {
    @Autowired
    SeatTimeMapper seatTimeMapper;

    @Autowired
    SeatReservationMapper seatReservationMapper;

    @Mock
    TimeProvider timeProvider;

    SeatReservationService seatReservationService;

    final int VALID_TIME_ID = 6;

    final int INVALID_TIME_ID = 1;

    @BeforeEach
    void setUp() {
        timeProvider = Mockito.mock(TimeProvider.class);
        Mockito.when(timeProvider.now()).thenReturn(LocalDateTime.now());

        seatReservationService = new SeatReservationService(
                timeProvider,
                seatTimeMapper,
                seatReservationMapper
        );
    }

    @AfterEach
    void tearDown() {
        timeProvider = null;
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
        var timeId = VALID_TIME_ID;
        var request = new SeatReservationRequest(timeId);

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
        var timeId = VALID_TIME_ID;
        var request = new SeatReservationRequest(timeId);

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

        assertThat(diff.get(0).getTimeId()).isNotNull();
        assertThat(after.size()).isEqualTo(before.size() + 1);
    }

    @Test
    @DisplayName("단일 예약: 예약 가능 좌석이 아닐 경우 예약에 실패해야 한다.")
    void reserveSeatsNotAvailable() {
        // given
        var memberId = 1;
        var timeId = INVALID_TIME_ID;
        var request = new SeatReservationRequest(timeId);

        // when
        assertThrows(AppException.class, () -> {
            seatReservationService.reserveSeats(memberId, request);
        });
    }

    @Test
    @DisplayName("단일 예약: 시작 시간 10분 이내에는 예약할 수 없다.")
    void reserve_should_failed_before_start_time_threshold() {
        // given
        var memberId = 1;
        var invalidTimeId = 7;
        var req = new SeatReservationRequest(invalidTimeId);
        var now = LocalDateTime.of(2024, 10, 1, 9, 55);
        Mockito.when(timeProvider.now()).thenReturn(now);

        // when
        assertThrows(AppException.class, () -> {
            seatReservationService.reserveSeats(memberId, req);
        });
    }

    @Test
    @DisplayName("다중 예약: 좌석과 시간대를 지정하여 여러 좌석을 동시에 예약할 수 있어야 한다.")
    void reserveMultiSeatRange() {
        // given
        var memberId = 1;
        var startDt = LocalDateTime.of(2024, 10, 1, 10, 0);
        var endDt = LocalDateTime.of(2024, 10, 1, 11, 0);
        var items = List.of(
                new MultipleSeatRangeReservationRequest.SeatTimeRangeItem(6, startDt, endDt),
                new MultipleSeatRangeReservationRequest.SeatTimeRangeItem(7, startDt, endDt)
        );

        var request = new MultipleSeatRangeReservationRequest(items);

        // when
        var before = seatReservationService.findReservationOfMember(memberId);
        assertDoesNotThrow(() -> {
            seatReservationService.reserveMultiSeatRange(memberId, request);
        });
        var after = seatReservationService.findReservationOfMember(memberId);

        // then
        assertThat(after.size()).isGreaterThan(before.size());
    }
}