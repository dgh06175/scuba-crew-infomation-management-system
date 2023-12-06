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
import management.service.ActivityService;
import management.service.MemberService;
import management.service.RecruitmentService;
import management.view.ActivityView;
import management.view.MainMenuView;
import management.view.MemberInfoView;
import management.view.RecruitmentView;

public class Application {
    public static void main(String[] args) {
        DatabaseManager dbManager = new DatabaseManager();

        MemberService memberService = new MemberService(dbManager);
        ActivityService activityService = new ActivityService(dbManager);
        RecruitmentService recruitmentService = new RecruitmentService(dbManager);

        new MainController(
                new MemberController(memberService, new MemberInfoView()),
                new ActivityController(activityService, new ActivityView()),
                new RecruitmentController(recruitmentService, new RecruitmentView()),
                new MainMenuView()
        ).run();
    }
}
