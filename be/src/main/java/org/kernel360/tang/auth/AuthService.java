package org.kernel360.tang.auth;

import lombok.RequiredArgsConstructor;
import org.kernel360.tang.auth.dto.LoginRequest;
import org.kernel360.tang.common.AppException;
import org.kernel360.tang.common.AuthExceptionCode;
import org.kernel360.tang.member.Member;
import org.kernel360.tang.member.MemberMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;

    public Member login(LoginRequest request) {
        Member member = memberMapper.selectMemberByUsername(request.getUsername());
        if (member == null || !passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new AppException(AuthExceptionCode.INVALID_LOGIN);
        }
        return member;
    }

    // DB에 유저 데이터가 있는지 없는지 체크
    public boolean userCheck(String userId) {
        return memberMapper.isMemberExist(userId) > 0;
    }

    // 유저의 데이터를 저장
    public void saveUser(MemberDto user) {
        memberMapper.saveUser(user);
    }
}
