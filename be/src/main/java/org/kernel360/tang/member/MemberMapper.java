package org.kernel360.tang.member;

import org.apache.ibatis.annotations.Mapper;
import org.kernel360.tang.auth.MemberDto;

@Mapper
public interface MemberMapper {
    MemberSummary selectMemberByMemberId(Long id);
    Member selectMemberByUsername(String username);
    int isMemberExist(String userId);
    void saveUser(MemberDto user);
}
