package org.kernel360.tang.member;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    Member selectMemberByUsername(String username);
}
