package org.kernel360.tang.seatReservation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kernel360.tang.seatReservation.dto.SeatReservationRequest;
import org.kernel360.tang.test.config.BaseIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SeatReservationControllerTest extends BaseIntegrationTest {
    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("단일 예약: TimeId가 올바른 경우 예약에 성공해야 한다.")
    void reserveSingleSeat() throws Exception {
        // given
        var timeId = 6;
        var request = new SeatReservationRequest(timeId);
        var body = objectMapper.writeValueAsString(request);

        // when
        var req = post("/api/v1/reserve")
                .contentType("application/json")
                .content(body);
        mvc.perform(req)
                .andExpect(status().isOk());
    }
}