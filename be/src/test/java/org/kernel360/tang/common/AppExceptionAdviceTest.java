package org.kernel360.tang.common;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
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
        mvc.perform(get("/test"))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    @DisplayName("$.error.code에 PREFIX를 포함한 예외 코드가 포함되어야 한다")
    void testErrorCode() throws Exception {
        mvc.perform(get("/test"))
                .andExpect(jsonPath("$.error.code").value("TEST001"));
    }

    @Test
    @DisplayName("$.error.message에 예외 메시지가 포함되어야 한다")
    void testErrorMessage() throws Exception {
        mvc.perform(get("/test"))
                .andExpect(jsonPath("$.error.message").value("test exception"));
    }

    @RestController
    public static class TestController {
        @GetMapping("/test")
        public void test() {
            ExceptionCode code = new ExceptionCode() {

                @Override
                public String getPrefix() {
                    return "TEST";
                }

                @Override
                public String getCode() {
                    return "001";
                }

                @Override
                public String getMessage() {
                    return "test exception";
                }

                @Override
                public HttpStatusCode getHttpStatusCode() {
                    return HttpStatus.BAD_REQUEST;
                }
            };

            throw new AppException(code);
        }
    }
}
