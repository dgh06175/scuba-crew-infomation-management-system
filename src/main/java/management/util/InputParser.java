package management.util;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputParser {
    private static Scanner scanner = new Scanner(System.in);

    public static int getNaturalNumber() {
        String input = scanner.nextLine();
        if (!Pattern.matches("\\d+", input)) {
            throw new IllegalArgumentException("숫자를 입력해 주세요.");
        }
        return Integer.parseInt(input);
    }
}
