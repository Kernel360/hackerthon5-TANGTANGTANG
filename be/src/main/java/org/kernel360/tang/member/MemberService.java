package org.kernel360.tang.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberMapper memberMapper;

    public MemberSummary selectMyInfo(Long memberId) {
        return memberMapper.selectMemberByMemberId(memberId);
    }
}
