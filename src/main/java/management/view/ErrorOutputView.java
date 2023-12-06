package management.view;

public class ErrorOutputView {
    private ErrorOutputView(){}
    public static void printErrorMessage(Exception e) {
        System.out.println(e.getMessage());
    }
}
