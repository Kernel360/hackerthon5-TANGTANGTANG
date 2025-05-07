package org.kernel360.tang.auth;

import lombok.AllArgsConstructor;
import org.kernel360.tang.auth.dto.LoginRequest;
import org.kernel360.tang.member.Member;
import org.kernel360.tang.member.MemberMapper;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthService {
    private final MemberMapper memberMapper;

    public Member login(LoginRequest request) {
        Member member = memberMapper.selectMemberByUsername(request.getUsername());
        // TODO: 비밀번호 검증 기능 추가 필요
        if (member == null) {
            throw new RuntimeException("로그인 정보가 올바르지 않습니다.");
        }
        return member;
    }
}
