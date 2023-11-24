package management.service;

import management.model.ClubMemberInformation;
import javax.persistence.EntityManager;
import java.util.List;

public class MemberService {
    private final EntityManager entityManager;

    public MemberService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<ClubMemberInformation> getAllMembers() {
        return entityManager.createQuery("SELECT m FROM ClubMemberInformation m", ClubMemberInformation.class).getResultList();
    }
}
