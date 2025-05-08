package org.kernel360.tang.common;

import java.time.LocalDateTime;

public interface TimeProvider {
    LocalDateTime now();
}
