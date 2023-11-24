package management.controller;

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
                    System.out.println("1번 선택");
                    memberController.handleRequest();
                    break;
                case 2:
                    System.out.println("2번 선택");
                    //activityController.handleRequest();
                    break;
                case 3:
                    System.out.println("3번 선택");
                    //recruitmentController.handleRequest();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    mainMenuView.displayInvalidOption();
            }
        }
    }

    private int requestMenuNumber(int...menuNumbers) {
        return RetryUtils.executeWithRetry(() -> {
            int userInput = mainMenuView.readUserInput();
            validateMenuInput(menuNumbers, userInput);
            return userInput;
        });
    }

    private void validateMenuInput(int[] menuNumbers, int userInput) {
        if (Arrays.stream(menuNumbers).noneMatch(num -> num == userInput)) {
            throw new IllegalArgumentException("잘못된 메뉴 번호가 입력되었습니다.");
        }
    }
}

