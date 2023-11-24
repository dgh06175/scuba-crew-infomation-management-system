package management.service;

import java.util.Map;
import java.util.stream.Collectors;
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

    public List<ClubMemberInformation> getMembersWithCertification(String certification) {
        return entityManager.createQuery(
                        "SELECT s.clubMemberInformation FROM ScubaExperienceInformation s WHERE s.scubaCertificationName = :certification",
                        ClubMemberInformation.class)
                .setParameter("certification", certification)
                .getResultList();
    }

    public List<ClubMemberInformation> getMembersWithLogCountGreaterThan(int logCount) {
        return entityManager.createQuery(
                        "SELECT s.clubMemberInformation FROM ScubaExperienceInformation s WHERE s.logCount > :logCount",
                        ClubMemberInformation.class)
                .setParameter("logCount", logCount)
                .getResultList();
    }

    public Map<String, Long> getMemberCountByCertification() {
        List<Object[]> results = entityManager.createQuery(
                        "SELECT s.scubaCertificationName, COUNT(s) FROM ScubaExperienceInformation s GROUP BY s.scubaCertificationName",
                        Object[].class)
                .getResultList();

        return results.stream()
                .collect(Collectors.toMap(
                        result -> (String) result[0],  // 자격증 이름
                        result -> (Long) result[1]    // 카운트
                ));
    }

}
