package management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.persistence.EntityManager;
import management.controller.ActivityController;
import management.controller.MainController;
import management.controller.MemberController;
import management.controller.RecruitmentController;
import management.database.DatabaseManager;
import management.service.MemberService;
import management.view.MainMenuView;
import management.view.MemberInfoView;

public class Application {
    public static void main(String[] args) {
        DatabaseManager dbManager = new DatabaseManager();
        MemberService memberService = new MemberService(dbManager.getEntityManager());
        new MainController(
                new MemberController(memberService, new MemberInfoView()),
                new ActivityController(),
                new RecruitmentController(),
                new MainMenuView()
        ).run();
    }
}
