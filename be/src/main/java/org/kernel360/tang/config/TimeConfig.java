package org.kernel360.tang.config;

import org.kernel360.tang.common.JavaBuiltinTimeProvider;
import org.kernel360.tang.common.TimeProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TimeConfig {
    @Bean
    TimeProvider timeProvider() {
        return new JavaBuiltinTimeProvider();
    }
}
