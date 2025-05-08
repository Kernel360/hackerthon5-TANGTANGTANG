package org.kernel360.tang.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final MemberMapper memberMapper;

    @Autowired
    public AuthService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
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