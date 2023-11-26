package management.controller;

import static management.util.RetryUtils.requestMenuNumber;

import java.util.Arrays;
import management.util.RetryUtils;
import management.view.MainMenuView;

public class MainController {
    private final MemberController memberController;
    private final ActivityController activityController;
    private final RecruitmentController recruitmentController;
    private final MainMenuView mainMenuView;

    public MainController(MemberController memberController, ActivityController activityController, RecruitmentController recruitmentController, MainMenuView mainMenuView) {
        this.memberController = memberController;
        this.activityController = activityController;
        this.recruitmentController = recruitmentController;
        this.mainMenuView = mainMenuView;
    }

    public void run() {
        boolean running = true;
        while (running) {
            mainMenuView.displayMenu();
            int choice = requestMenuNumber(1, 2, 3, 0);
            switch (choice) {
                case 1:
                    memberController.run();
                    break;
                case 2:
                    activityController.run();
                    break;
                case 3:
                    recruitmentController.run();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    mainMenuView.displayInvalidOption();
            }
        }
    }
}

