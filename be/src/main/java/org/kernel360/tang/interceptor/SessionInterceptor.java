package org.kernel360.tang.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.kernel360.tang.common.AppException;
import org.kernel360.tang.common.AuthExceptionCode;
import org.kernel360.tang.common.Constants;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class SessionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        log.debug("session > {} ", session);

        Long memberId = session == null ? null : (Long) session.getAttribute(Constants.SESSION_MEMBER_ID);
        if (memberId == null) {
            throw new AppException(AuthExceptionCode.INVALID_AUTHORIZED);
        }

        request.setAttribute(Constants.SESSION_MEMBER_ID, memberId);
        return true;
    }
}
