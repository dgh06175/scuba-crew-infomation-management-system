package management.util;

import java.util.Scanner;
import java.util.regex.Pattern;

public class InputUtil {
    private static Scanner scanner = new Scanner(System.in);

    public static int getNaturalNumber() {
        String input = scanner.nextLine();
        if (!Pattern.matches("\\d+", input)) {
            throw new IllegalArgumentException("숫자를 입력해 주세요.");
        }
        return Integer.parseInt(input);
    }

    public static String getString() {
        String input = scanner.nextLine();
        if (input.isEmpty() || input.isBlank()) {
            throw new IllegalArgumentException("올바른 문자열을 입력해 주세요.");
        }
        return input;
    }
}
