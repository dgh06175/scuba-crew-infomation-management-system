package management.view;

import static management.util.InputUtil.getNaturalNumber;

public class MainMenuView {
    public void displayMenu() {
        System.out.println();
        System.out.println("###### 메인 화면 ######");
        System.out.println("1. 회원 정보 조회");
        System.out.println("2. 스쿠버 활동 계획");
        System.out.println("3. 가두모집 관리");
        System.out.println("0. 종료");
    }

    public int readUserInput() {
        System.out.print("옵션을 선택하세요: ");
        return getNaturalNumber();
    }

    public void displayInvalidOption() {
        System.out.println("잘못된 옵션 입력입니다.");
    }
}
