package management.controller;

import java.util.Arrays;
import java.util.Map;
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
            int choice = requestMenuNumber(1, 2, 3, 4, 5, 6, 0);
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
                case 4: // 자격증 인원 조회
                    displayMembersWithCertification();
                    break;
                case 5: // 일정 로그 개수 이상 인원 조회
                    displayMembersLogCount();
                    break;
                case 6: // 자격증별 인원수 조회
                    displayMembersByCertificationCount();
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

    private void displayMembersWithCertification() {
        String certification = memberInfoView.readCertification();
        List<ClubMemberInformation> members = memberService.getMembersWithCertification(certification);
        memberInfoView.displayMembers(members);
    }

    private void displayMembersLogCount() {
        int logCount = memberInfoView.readLogCount();
        List<ClubMemberInformation> members = memberService.getMembersWithLogCountGreaterThan(logCount);
        memberInfoView.displayMembers(members);
    }

    private void displayMembersByCertificationCount() {
        Map<String, Long> certificationCounts = memberService.getMemberCountByCertification();
        memberInfoView.displayCertificationCounts(certificationCounts);
    }
}
