package org.kernel360.tang.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Member {
    private Long memberId;
    private String username;
    private String password;
}
