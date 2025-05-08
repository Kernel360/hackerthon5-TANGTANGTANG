package org.kernel360.tang.common;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kernel360.tang.test.TestController;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(
        controllers = {TestController.class},
        useDefaultFilters = false
)
@ExtendWith({OutputCaptureExtension.class})
public class AppExceptionAdviceTest {
    MockMvc mvc;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders
                .standaloneSetup(new TestController())
                .setControllerAdvice(new AppExceptionAdvice())
                .build();
    }

    @AfterEach
    void tearDown() {
        mvc = null;
    }

    @Test
    @DisplayName("상태코드가 ExceptionCode의 HttpStatusCode와 같아야 한다")
    void testHttpStatusCode() throws Exception {
        mvc.perform(get("/test-app-warn"))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    @DisplayName("$.error.code에 PREFIX를 포함한 예외 코드가 포함되어야 한다")
    void testErrorCode() throws Exception {
        mvc.perform(get("/test-app-warn"))
                .andExpect(jsonPath("$.error.code").value("TEST001"));
    }

    @Test
    @DisplayName("$.error.message에 예외 메시지가 포함되어야 한다")
    void testErrorMessage() throws Exception {
        mvc.perform(get("/test-app-warn"))
                .andExpect(jsonPath("$.error.message").value("test exception"));
    }

    @Test
    @DisplayName("로그 레벨이 ERROR인 경우 표준 출력에 StackTrace가 포함되어야 한다")
    void testErrorLoggingWithStackTrace(CapturedOutput output) throws Exception {
        mvc.perform(get("/test-app-error"))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));

        assertThat(output).contains("org.kernel360.tang.common.AppException: test exception");
    }

    @Test
    @DisplayName("알 수 없는 예외 발생 시 500 에러가 발생해야 한다")
    void testUnknownException() throws Exception {
        mvc.perform(get("/test-internal"))
                .andExpect(status().is(HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }

    @Test
    @DisplayName("알 수 없는 예외 발생 시 표준 출력에 StackTrace가 포함되어야 한다")
    void testUnknownExceptionWithStackTrace(CapturedOutput output) throws Exception {
        mvc.perform(get("/test-internal"))
                .andExpect(status().is(HttpStatus.INTERNAL_SERVER_ERROR.value()));

        assertThat(output).contains("java.lang.RuntimeException: test exception");
    }

    @Test
    @DisplayName("존재하지 않는 endpoint에 대해서 404 에러가 발생해야 한다")
    void testUnknownEndpoint() throws Exception {
        mvc.perform(get("/unknown-endpoint"))
                .andExpect(status().is(HttpStatus.NOT_FOUND.value()));
    }
}
