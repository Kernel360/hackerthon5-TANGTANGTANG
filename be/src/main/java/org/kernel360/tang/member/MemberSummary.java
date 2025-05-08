package org.kernel360.tang.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MemberSummary {
    private final Long memberId;
    private final String memberName;
}
