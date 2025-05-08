package org.kernel360.tang.common;

import java.time.LocalDateTime;

public class JavaBuiltinTimeProvider implements TimeProvider {
    @Override
    public LocalDateTime now() {
        return LocalDateTime.now();
    }
}
