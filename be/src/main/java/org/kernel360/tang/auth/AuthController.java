package org.kernel360.tang.auth;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.kernel360.tang.auth.dto.LoginRequest;
import org.kernel360.tang.auth.dto.LoginResponse;
import org.kernel360.tang.common.AppErrorResponse;
import org.kernel360.tang.common.AppException;
import org.kernel360.tang.member.Member;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@RestController
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request, HttpSession session) {
        try {
            Member member = authService.login(request);
            session.setAttribute("memberId", member.getMemberId());

            LoginResponse loginResponse = new LoginResponse(member.getUsername());
            return ResponseEntity.ok(loginResponse);
        } catch (AppException e) {
            return new ResponseEntity<>(AppErrorResponse.from(e.getCode()), HttpStatus.UNAUTHORIZED);
        }
    }
}
