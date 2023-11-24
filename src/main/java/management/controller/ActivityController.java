package management.controller;

import management.service.ActivityService;
import management.view.ActivityView;
import management.model.DiveSite;
import management.model.Certification;
import management.model.ClubMemberInformation;

import java.util.List;
import java.util.Map;

public class ActivityController {
    private final ActivityService activityService;
    private final ActivityView activityView;

    public ActivityController(ActivityService activityService, ActivityView activityView) {
        this.activityService = activityService;
        this.activityView = activityView;
    }

    public void run() {
        boolean running = true;
        while (running) {
            activityView.displayActivityMenu();
            int activityType = activityView.chooseActivityType(); // 개방수역, 제한수역 선택
            switch (activityType) {
                case 1: // 개방수역
                    activityView.displayOpenWaterActivityMenu();
                    handleOpenWaterActivity();
                    break;
                case 2: // 제한수역
                    handleRestrictedWaterActivity();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    activityView.displayInvalidOption();
            }
        }
    }

    private void handleOpenWaterActivity() {
        int openWaterActivityType = activityView.chooseOpenWaterActivity(); // 자격증 취득, 펀다이빙 선택
        switch (openWaterActivityType) {
            case 1: // 자격증 취득
                conductCertificationActivity();
                break;
            case 2: // 펀다이빙
                conductFunDiving();
                break;
            // ... 기타 옵션
        }
    }

    private void handleRestrictedWaterActivity() {
        List<ClubMemberInformation> allMembers = activityService.getAllMembers();
        List<ClubMemberInformation> participatingMembers = activityView.selectParticipatingMembers(allMembers);
        activityService.updateMembersForRestrictedWaterActivity(participatingMembers);
        activityView.displayRestrictedWaterActivity(participatingMembers);
        displayRequiredEquipment(participatingMembers);
    }

    private void conductCertificationActivity() {
        String certification = activityView.selectCertification();
        // 전체 동아리원 목록을 가져온다
        List<ClubMemberInformation> allMembers = activityService.getAllMembers();
        // 참여할 멤버들을 선택한다
        List<ClubMemberInformation> participatingMembers = activityView.selectParticipatingMembers(allMembers);
        // 선택된 멤버들의 자격증을 업데이트한다
        activityService.updateMembersForCertification(certification, participatingMembers);
        activityView.displayMembers(allMembers);
        displayRequiredEquipment(participatingMembers);
    }

    private void conductFunDiving() {
        // 전체 동아리원 목록을 가져온다
        List<ClubMemberInformation> allMembers = activityService.getAllMembers();
        // 참여할 멤버들을 선택한다
        List<ClubMemberInformation> participatingMembers = activityView.selectParticipatingMembers(allMembers);
        // 선택된 멤버들의 펀다이빙 활동을 업데이트한다
        activityService.updateMembersForFunDiving(participatingMembers);
        activityView.displayMembersLogs(participatingMembers);
        displayRequiredEquipment(participatingMembers);
    }

    private void displayRequiredEquipment(List<ClubMemberInformation> participatingMembers) {
        Map<String, Integer> requiredItems = activityService.calculateRequiredItems(participatingMembers);
        activityView.displayRequiredItems(requiredItems);
    }
}
