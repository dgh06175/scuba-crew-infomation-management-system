package management.util;

import java.util.function.Supplier;
import management.view.OutputView;

public class RetryUtils {
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
