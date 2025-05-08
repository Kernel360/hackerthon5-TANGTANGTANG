package org.kernel360.tang.auth;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MemberMapper {

    int isMemberExist(String userId);

    void saveUser(MemberDto user);
}
