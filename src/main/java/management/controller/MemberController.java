package management.controller;

import management.model.ClubMemberInformation;
import management.service.MemberService; // 서비스 클래스 (별도로 구현 필요)
import management.view.MemberInfoView; // 뷰 클래스 (별도로 구현 필요)

import java.util.List;

public class MemberController {
    private final MemberService memberService;
    private final MemberInfoView memberInfoView;

    public MemberController(MemberService memberService, MemberInfoView memberInfoView) {
        this.memberService = memberService;
        this.memberInfoView = memberInfoView;
    }

    public void handleRequest() {
        List<ClubMemberInformation> members = memberService.getAllMembers();
        memberInfoView.displayMembers(members);
    }
}
