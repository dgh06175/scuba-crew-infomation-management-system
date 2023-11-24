package management.view;

import static management.util.InputUtil.getNaturalNumber;
import static management.util.InputUtil.getString;

import java.util.Map;
import management.model.ClubMemberInformation;
import java.util.List;
import management.model.PhysicalInformation;
import management.model.ScubaExperienceInformation;

public class MemberInfoView {
    private static final String PRINT_FORMAT = "%-15s %-8s %-20s\n";
    private static final String SCUBA_PRINT_FORMAT = "%-8s %-12s %-20s %-10s\n";
    private static final String PHYSICAL_PRINT_FORMAT = "%-10s %-5s %-10s %-5s %-8s %-10s %-20s\n";

    public void displayMenu() {
        System.out.println();
        System.out.println("###### 회원 정보 화면 ######");
        System.out.println("1. 인적 사항 조회");
        System.out.println("2. 스쿠버 관련 정보 조회");
        System.out.println("3. 신체 정보 조회");
        System.out.println("4. 자격증 인원 조회");
        System.out.println("5. 일정 로그 개수 이상 인원 조회");
        System.out.println("6. 자격증별 인원수 조회");
        System.out.println("0. 메인 메뉴로 나가기");
    }

    public int readUserInput() {
        System.out.print("옵션을 선택하세요: ");
        return getNaturalNumber();
    }

    public void displayInvalidOption() {
        System.out.println("잘못된 옵션 입력입니다.");
    }

    public String readCertification() {
        System.out.print("조회할 자격증 이름을 입력하세요: ");
        return getString();
    }

    public int readLogCount() {
        System.out.print("조회할 로그 수를 입력하세요: ");
        return getNaturalNumber();
    }

    public void displayMembers(List<ClubMemberInformation> members) {
        System.out.printf(PRINT_FORMAT, "학번", "이름", "학과");
        for (ClubMemberInformation member : members) {
            System.out.printf(PRINT_FORMAT, member.getStudentId(), member.getName(), member.getDepartment());
        }
    }


    public void displayScubaExperience(List<ScubaExperienceInformation> members) {
        System.out.printf(SCUBA_PRINT_FORMAT, "이름", "자격증 이름", "제한수역 교육 횟수", "로그 수");
        for (ScubaExperienceInformation member : members) {
            String name = member.getClubMemberInformation() != null ? member.getClubMemberInformation().getName() : "N/A";
            String certName = member.getScubaCertificationName() != null ? member.getScubaCertificationName() : "N/A";
            String restrictedCount = member.getRestrictedWaterTrainingCount() != null ? member.getRestrictedWaterTrainingCount().toString() : "N/A";
            String logCount = member.getLogCount() != null ? member.getLogCount().toString() : "N/A";

            System.out.printf(SCUBA_PRINT_FORMAT, name, certName, restrictedCount, logCount);
        }
    }

    public void displayPhysicalInformation(List<PhysicalInformation> members) {
        System.out.printf(PHYSICAL_PRINT_FORMAT, "이름", "나이", "성별", "키", "몸무게", "발사이즈", "건강 유의사항");
        for (PhysicalInformation member : members) {
            String name = member.getClubMemberInformation() != null ? member.getClubMemberInformation().getName() : "N/A";
            String age = member.getAge() != null ? member.getAge().toString() : "N/A";
            String gender = member.getGender() != null ? member.getGender().toString() : "N/A";
            String height = member.getHeight() != null ? member.getHeight().toString() : "N/A";
            String weight = member.getWeight() != null ? member.getWeight().toString() : "N/A";
            String shoeSize = member.getShoeSize() != null ? member.getShoeSize().toString() : "N/A";
            String healthNotes = member.getHealthNotes() != null ? member.getHealthNotes() : "N/A";

            System.out.printf(PHYSICAL_PRINT_FORMAT, name, age, gender, height, weight, shoeSize, healthNotes);
        }
    }

    public void displayCertificationCounts(Map<String, Long> certificationCounts) {
        System.out.println("자격증 별 인원 수:");
        certificationCounts.forEach((certification, count) -> {
            System.out.println(certification + ": " + count);
        });
    }
}
