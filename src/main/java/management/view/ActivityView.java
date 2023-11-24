package management.view;

import static management.util.InputUtil.getNaturalNumber;
import static management.util.InputUtil.getString;

import management.model.ClubMemberInformation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ActivityView {
    public void displayActivityMenu() {
        System.out.println("활동 유형을 선택하세요:");
        System.out.println("1: 개방수역");
        System.out.println("2: 제한수역");
        System.out.println("0: 종료");
    }

    public int chooseActivityType() {
        // 개방수역, 제한수역 선택 인터페이스
        return getNaturalNumber();
    }

    public void displayOpenWaterActivityMenu() {
        System.out.println("개방수역 활동을 선택하세요:");
        System.out.println("1: 자격증 취득");
        System.out.println("2: 펀다이빙");
        System.out.println("0: 이전 메뉴로 돌아가기");
    }

    public int chooseOpenWaterActivity() {
        // 자격증 취득, 펀다이빙 선택 인터페이스
        return getNaturalNumber();
    }

    public String selectCertification() {
        // 자격증 선택 인터페이스
        System.out.print("취득할 자격증 이름을 입력하세요: ");
        return getString();
    }

    public void displayMembers(List<ClubMemberInformation> members) {
        System.out.println("동아리원 목록:");
        for (ClubMemberInformation member : members) {
            System.out.println(member.getStudentId() + ": " + member.getName());
        }
    }

    public List<ClubMemberInformation> selectParticipatingMembers(List<ClubMemberInformation> allMembers) {
        displayMembers(allMembers);
        System.out.println("활동에 참여할 멤버의 학번을 입력하세요 (완료 시 0 입력):");
        List<ClubMemberInformation> selectedMembers = new ArrayList<>();
        while (true) {
            long studentId = getNaturalNumber();
            if (studentId == 0) break;
            allMembers.stream()
                    .filter(member -> member.getStudentId() == studentId)
                    .findFirst()
                    .ifPresent(selectedMembers::add);
        }
        return selectedMembers;
    }

    public void displayRestrictedWaterActivity(List<ClubMemberInformation> members) {
        System.out.println("제한수역 활동 완료:");
        for (ClubMemberInformation member : members) {
            System.out.println(member.getName() + " (" + member.getStudentId() + ") - 수영장 교육 횟수 증가");
        }
    }

    public void displayRequiredItems(Map<String, Integer> items) {
        // 필요한 장비 출력 인터페이스
        System.out.println("필요한 장비 목록:");
        items.forEach((item, count) ->
                System.out.println(item + ": " + count + "개"));
    }

    public void displayInvalidOption() {
        System.out.println("잘못된 옵션 입력입니다.");
    }

    public void displayUpdatedCertification(String certification, List<ClubMemberInformation> members) {
        System.out.println("자격증 업데이트 완료: " + certification);
        for (ClubMemberInformation member : members) {
            System.out.println(member.getName() + " (" + member.getStudentId() + ")");
        }
    }

    public void displayMembersLogs(List<ClubMemberInformation> members) {
        System.out.println("펀다이빙 활동 로그:");
        for (ClubMemberInformation member : members) {
            System.out.println(member.getName() + " (" + member.getStudentId() + ") - 로그 추가됨");
        }
    }
}
