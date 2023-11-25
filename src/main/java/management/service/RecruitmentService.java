package management.service;

import javax.persistence.EntityTransaction;
import management.model.RecruitmentApplication;
import management.model.ClubMemberInformation;
import javax.persistence.EntityManager;
import java.util.List;

public class RecruitmentService {
    private final EntityManager entityManager;

    public RecruitmentService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<RecruitmentApplication> getAllApplications() {
        return entityManager.createQuery(
                        "SELECT r FROM RecruitmentApplication r", RecruitmentApplication.class)
                .getResultList();
    }

    public void acceptApplicant(long studentId) throws Exception {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            RecruitmentApplication application = entityManager.find(RecruitmentApplication.class, studentId);
            if (application == null) {
                throw new Exception("해당 학번의 지원자가 존재하지 않습니다.");
            }

            ClubMemberInformation existingMember = entityManager.find(ClubMemberInformation.class, studentId);
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

            entityManager.persist(newMember);

            entityManager.remove(application);

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }
}
