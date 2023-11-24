package management.service;

import management.model.ClubMemberInformation;
import javax.persistence.EntityManager;
import java.util.List;
import management.model.PhysicalInformation;
import management.model.ScubaExperienceInformation;

public class MemberService {
    private final EntityManager entityManager;

    public MemberService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<ClubMemberInformation> getAllMembers() {
        return entityManager.createQuery(
                "SELECT m FROM ClubMemberInformation m",
                ClubMemberInformation.class)
                .getResultList();
    }

    public List<ScubaExperienceInformation> getAllScubaExperience() {
        return entityManager.createQuery(
                        "SELECT m FROM ScubaExperienceInformation m",
                        ScubaExperienceInformation.class)
                .getResultList();
    }

    public List<PhysicalInformation> getAllPhysical() {
        return entityManager.createQuery(
                        "SELECT m FROM PhysicalInformation m",
                        PhysicalInformation.class)
                .getResultList();
    }
}
