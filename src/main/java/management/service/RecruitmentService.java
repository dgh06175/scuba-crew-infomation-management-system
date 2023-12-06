package management.service;

import java.util.function.Consumer;
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
        RecruitmentApplication application = entityManager.find(RecruitmentApplication.class, studentId);
        if (application == null) {
            throw new Exception("해당 학번의 지원자가 존재하지 않습니다.");
        }

        ClubMemberInformation existingMember = entityManager.find(ClubMemberInformation.class, studentId);
        if (existingMember != null) {
            throw new Exception("이미 동아리원으로 등록된 학번입니다.");
        }
        withTransaction(em -> {
            ClubMemberInformation newMember = new ClubMemberInformation();
            newMember.setStudentId(application.getStudentId());
            newMember.setName(application.getName());
            newMember.setDepartment(application.getDepartment());
            newMember.setGrade(application.getGrade());
            newMember.setPhoneNumber(application.getPhoneNumber());
            newMember.setAddress(application.getAddress());

            entityManager.persist(newMember);
            entityManager.remove(application);
        });
    }

    private void withTransaction(Consumer<EntityManager> action) {
        try {
            entityManager.getTransaction().begin();
            action.accept(entityManager);
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }
}
