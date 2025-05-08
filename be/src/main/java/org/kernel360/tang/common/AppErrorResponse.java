package org.kernel360.tang.common;

public record AppErrorResponse(
        ErrorData error
) {
    public record ErrorData(
            String code,
            String message
    ) {
    }

    public static AppErrorResponse from(ExceptionCode code) {
        var data = new ErrorData(code.getFullCode(), code.getMessage());
        return new AppErrorResponse(data);
    }
}
