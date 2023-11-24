package management.controller;

import java.util.Arrays;
import management.model.ClubMemberInformation;
import management.model.PhysicalInformation;
import management.model.ScubaExperienceInformation;
import management.service.MemberService; // 서비스 클래스 (별도로 구현 필요)
import management.util.RetryUtils;
import management.view.MemberInfoView; // 뷰 클래스 (별도로 구현 필요)

import java.util.List;

public class MemberController {
    private final MemberService memberService;
    private final MemberInfoView memberInfoView;

    public MemberController(MemberService memberService, MemberInfoView memberInfoView) {
        this.memberService = memberService;
        this.memberInfoView = memberInfoView;
    }

    public void run() {
        boolean running = true;
        while (running) {
            memberInfoView.displayMenu();
            int choice = requestMenuNumber(1, 2, 3, 0);
            switch (choice) {
                case 1: // 인적 사항 조회
                    displayMemberInformation();
                    break;
                case 2: // 스쿠버 관련 정보 조회
                    displayScubaExperienceInformation();
                    break;
                case 3: // 신체 정보 조회
                    displayPhysicalInformation();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    memberInfoView.displayInvalidOption();
            }
        }
    }

    private int requestMenuNumber(int...menuNumbers) {
        return RetryUtils.executeWithRetry(() -> {
            int userInput = memberInfoView.readUserInput();
            validateMenuInput(menuNumbers, userInput);
            return userInput;
        });
    }

    private void validateMenuInput(int[] menuNumbers, int userInput) {
        if (Arrays.stream(menuNumbers).noneMatch(num -> num == userInput)) {
            throw new IllegalArgumentException("잘못된 메뉴 번호가 입력되었습니다.");
        }
    }

    private void displayMemberInformation() {
        List<ClubMemberInformation> members = memberService.getAllMembers();
        memberInfoView.displayMembers(members);
    }

    private void displayScubaExperienceInformation() {
        List<ScubaExperienceInformation> scubaExperience = memberService.getAllScubaExperience();
        memberInfoView.displayScubaExperience(scubaExperience);
    }

    private void displayPhysicalInformation() {
        List<PhysicalInformation> physicalInformation = memberService.getAllPhysical();
        memberInfoView.displayPhysicalInformation(physicalInformation);
    }
}
