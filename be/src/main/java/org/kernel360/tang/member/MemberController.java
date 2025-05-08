package org.kernel360.tang.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
@RestController
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/my")
    public ResponseEntity<MemberSummary> selectMyInfo(@RequestAttribute("memberId") Long memberId) {
        log.info("memberId: {}", memberId);
        MemberSummary summary = memberService.selectMyInfo(memberId);
        return ResponseEntity.ok(summary);
    }
}
