package org.kernel360.tang.auth;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.kernel360.tang.auth.dto.LoginRequest;
import org.kernel360.tang.auth.dto.LoginResponse;
import org.kernel360.tang.member.Member;
import org.kernel360.tang.common.Constants;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@RestController
public class AuthController {
    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request, HttpSession session) {
        Member member = authService.login(request);
        session.setAttribute(Constants.SESSION_MEMBER_ID, member.getMemberId());

        LoginResponse loginResponse = new LoginResponse(member.getUsername());
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<String> signup(@RequestBody MemberDto memberDto) {

        String userId = memberDto.getUsername();
        String password = memberDto.getPassword();

        //아이디의 길이는 4글자 이상, 12글자 이하여야 한다.
        if(userId == null || userId.length() <4 || userId.length() > 12) {
            return ResponseEntity.badRequest().body("아이디의 길이는 4~12글자로 작성해주세요.");
        }
        //비밀번호의 길이는 8글자 이상, 36글자 이하여야 한다.
        if(password == null || password.length() <8 || password.length() > 36) {
            return ResponseEntity.badRequest().body("비밀번호는 8~36글자로 작성해주세요.");
        }
        //같은 아이디로 중복 가입할 수 없다.
        boolean userCheck = authService.userCheck(userId);
        if(userCheck) {
            return ResponseEntity.badRequest().body("이미 존재하는 아이디입니다.");
        }
        //비밀번호는 암호화 해서 저장해야 한다. 암호화 미작성.
        MemberDto user = new MemberDto();
        user.setUsername(userId);
        user.setPassword(passwordEncoder.encode(password));
        authService.saveUser(user);


        return ResponseEntity.ok("회원가입 성공");
    }
}
