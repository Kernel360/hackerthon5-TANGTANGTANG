package org.kernel360.tang.test;

import org.kernel360.tang.common.AppException;
import org.kernel360.tang.common.ExceptionCode;
import org.kernel360.tang.common.LoggingLevel;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/test-app-warn")
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

            @Override
            public LoggingLevel getLoggingLevel() {
                return LoggingLevel.APP_WARN;
            }
        };

        throw new AppException(code);
    }

    @GetMapping("/test-app-error")
    public void testWithLogging() {
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

            @Override
            public LoggingLevel getLoggingLevel() {
                return LoggingLevel.APP_ERROR;
            }
        };

        throw new AppException(code);
    }

    @GetMapping("/test-internal")
    public void testInternal() {
        throw new RuntimeException("test exception");
    }
}
