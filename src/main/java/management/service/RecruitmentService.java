package management.service;

import java.util.function.Consumer;
import management.database.DatabaseManager;
import management.model.RecruitmentApplication;
import management.model.ClubMemberInformation;
import javax.persistence.EntityManager;
import java.util.List;

public class RecruitmentService {
    private final DatabaseManager databaseManager;

    public RecruitmentService(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public List<RecruitmentApplication> getAllApplications() {
        return databaseManager.getEntityManager().createQuery(
                        "SELECT r FROM RecruitmentApplication r", RecruitmentApplication.class)
                .getResultList();
    }

    public void acceptApplicant(long studentId) throws Exception {
        RecruitmentApplication application = databaseManager.find(RecruitmentApplication.class, studentId);
        if (application == null) {
            throw new Exception("해당 학번의 지원자가 존재하지 않습니다.");
        }

        ClubMemberInformation existingMember = databaseManager.find(ClubMemberInformation.class, studentId);
        if (existingMember != null) {
            throw new Exception("이미 동아리원으로 등록된 학번입니다.");
        }

        ClubMemberInformation newMember = new ClubMemberInformation();
        newMember.setStudentId(application.getStudentId());
        newMember.setName(application.getName());
        newMember.setDepartment(application.getDepartment());
        newMember.setGrade(application.getGrade());
        newMember.setPhoneNumber(application.getPhoneNumber());
        newMember.setAddress(application.getAddress());

        databaseManager.save(newMember);
        databaseManager.delete(application);
    }
}
