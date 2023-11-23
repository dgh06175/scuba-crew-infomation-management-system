package management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import management.controller.ActivityController;
import management.controller.MainController;
import management.controller.MemberController;
import management.controller.RecruitmentController;
import management.view.MainMenuView;

public class Application {
    public static void main(String[] args) {
        new MainController(
                new MemberController(),
                new ActivityController(),
                new RecruitmentController(),
                new MainMenuView()
        ).run();
    }
}
