package management.view;

import static management.util.InputUtil.getNaturalNumber;

import management.model.RecruitmentApplication;
import java.util.List;

public class RecruitmentView {
    public void displayRecruitmentMenu() {
        System.out.println("모집 관련 메뉴:");
        System.out.println("1: 지원 내역 출력");
        System.out.println("2: 합격 처리");
        System.out.println("0: 종료");
    }

    public void displayApplications(List<RecruitmentApplication> applications) {
        System.out.println("지원 내역:");
        for (RecruitmentApplication app : applications) {
            System.out.println("학번: " + app.getStudentId() + ", 이름: " + app.getName() +
                    ", 학과: " + app.getDepartment() + ", 학년: " + app.getGrade() +
                    ", 지원 목적: " + app.getPurposeOfJoining() +
                    ", 자격증 여부: " + app.getScubaCertificationName() +
                    ", 지원 이유: " + app.getReasonForJoining());
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
