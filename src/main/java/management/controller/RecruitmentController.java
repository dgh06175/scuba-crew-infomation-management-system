package management.controller;

import static management.util.InputUtil.getNaturalNumber;
import static management.util.RetryUtils.requestMenuNumber;

import management.service.RecruitmentService;
import management.view.RecruitmentView;
import management.model.RecruitmentApplication;
import management.model.ClubMemberInformation;

import java.util.List;

public class RecruitmentController {
    private final RecruitmentService recruitmentService;
    private final RecruitmentView recruitmentView;

    public RecruitmentController(RecruitmentService recruitmentService, RecruitmentView recruitmentView) {
        this.recruitmentService = recruitmentService;
        this.recruitmentView = recruitmentView;
    }

    public void run() {
        boolean running = true;
        while (running) {
            recruitmentView.displayRecruitmentMenu();
            int choice = requestMenuNumber(1, 2, 0);
            switch (choice) {
                case 1: // 지원 내역 출력
                    displayRecruitmentApplications();
                    break;
                case 2: // 합격 처리
                    processAcceptance();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    recruitmentView.displayInvalidOption();
            }
        }
    }

    private void displayRecruitmentApplications() {
        List<RecruitmentApplication> applications = recruitmentService.getAllApplications();
        recruitmentView.displayApplications(applications);
    }

    private void processAcceptance() {
        List<RecruitmentApplication> applications = recruitmentService.getAllApplications();
        long studentId = recruitmentView.selectApplicantForAcceptance(applications);
        try {
            recruitmentService.acceptApplicant(studentId);
            recruitmentView.displayAcceptanceSuccess(studentId);
        } catch (Exception e) {
            recruitmentView.displayError(e.getMessage());
        }
    }
}
