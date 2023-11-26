package management.util;

import static management.util.InputUtil.getNaturalNumber;

import java.util.Arrays;
import java.util.function.Supplier;
import management.view.OutputView;

public class RetryUtils {
    public static int requestMenuNumber(int...menuNumbers) {
        return RetryUtils.executeWithRetry(() -> {
            int userInput = readUserInput();
            validateMenuInput(menuNumbers, userInput);
            return userInput;
        });
    }

    private static int readUserInput() {
        System.out.print("옵션을 선택하세요: ");
        return getNaturalNumber();
    }

    private static void validateMenuInput(int[] menuNumbers, int userInput) {
        if (Arrays.stream(menuNumbers).noneMatch(num -> num == userInput)) {
            throw new IllegalArgumentException("잘못된 메뉴 번호가 입력되었습니다.");
        }
    }

    public static <T> T executeWithRetry(Supplier<T> action) {
        while (true) {
            try {
                return action.get();
            } catch (Exception e) {
                OutputView.printErrorMessage(e);
            }
        }
    }
}
