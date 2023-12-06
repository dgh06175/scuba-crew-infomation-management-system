package management.view;

import static management.util.InputUtil.getNaturalNumber;

import management.model.RecruitmentApplication;
import java.util.List;

public class RecruitmentView {
    public void displayRecruitmentMenu() {
        System.out.println("###### 가두모집 관리 화면 ######");
        System.out.println("1: 지원 내역 출력");
        System.out.println("2: 합격 처리");
        System.out.println("0: 종료");
    }

    public void displayApplications(List<RecruitmentApplication> applications) {
        final String APPLICATION_PRINT_FORMAT = "| %-10s | %-8s | %-10s | %-6s | %-50s | %-10s | %-40s |%n";

        System.out.printf(APPLICATION_PRINT_FORMAT, "학번", "이름", "학과", "학년", "지원 목적", "자격증 여부", "지원 이유");
        for (RecruitmentApplication app : applications) {
            String studentId = Long.toString(app.getStudentId());
            String name = app.getName();
            String department = app.getDepartment();
            String grade = Integer.toString(app.getGrade());
            String purpose = app.getPurposeOfJoining();
            String certName = app.getScubaCertificationName() != null ? app.getScubaCertificationName() : "N/A";
            String reason = app.getReasonForJoining();

            System.out.printf(APPLICATION_PRINT_FORMAT, studentId, name, department, grade, purpose, certName, reason);
        }
    }


    public long selectApplicantForAcceptance(List<RecruitmentApplication> applications) {
        System.out.println("합격 처리할 지원자의 학번을 입력하세요:");
        long studentId;
        while (true) {
            studentId = getNaturalNumber();
            final long finalStudentId = studentId;
            if (applications.stream().anyMatch(app -> app.getStudentId() == finalStudentId)) {
                break;
            }
            System.out.println("해당 학번의 지원자가 없습니다. 다시 입력해주세요.");
        }
        return studentId;
    }

    public void displayAcceptanceSuccess(long studentId) {
        System.out.println("학번 " + studentId + "의 지원자 합격 처리가 완료되었습니다.");
    }

    public void displayError(String message) {
        System.out.println("에러: " + message);
    }

    public void displayInvalidOption() {
        System.out.println("잘못된 옵션 입력입니다.");
    }
}
