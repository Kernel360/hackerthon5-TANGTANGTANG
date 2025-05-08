package org.kernel360.tang.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Member {
    private final Long memberId;
    private final String username;
    private final String password;
}
