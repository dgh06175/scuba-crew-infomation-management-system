package management.service;

import management.database.DatabaseManager;
import management.model.ClubMemberInformation;
import management.model.PhysicalInformation;
import management.model.ScubaExperienceInformation;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MemberService {
    private final DatabaseManager databaseManager;

    public MemberService(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public List<ClubMemberInformation> getAllMembers() {
        return databaseManager.getEntityManager().createQuery(
                        "SELECT m FROM ClubMemberInformation m", ClubMemberInformation.class)
                .getResultList();
    }

    public List<ScubaExperienceInformation> getAllScubaExperience() {
        return databaseManager.getEntityManager().createQuery(
                        "SELECT m FROM ScubaExperienceInformation m", ScubaExperienceInformation.class)
                .getResultList();
    }

    public List<PhysicalInformation> getAllPhysical() {
        return databaseManager.getEntityManager().createQuery(
                        "SELECT m FROM PhysicalInformation m", PhysicalInformation.class)
                .getResultList();
    }

    public List<ClubMemberInformation> getMembersWithLogCountGreaterThan(int logCount) {
        return databaseManager.getEntityManager().createQuery(
                        "SELECT s.clubMemberInformation FROM ScubaExperienceInformation s WHERE s.logCount > :logCount",
                        ClubMemberInformation.class)
                .setParameter("logCount", logCount)
                .getResultList();
    }

    public Map<String, Long> getMemberCountByCertification() {
        List<Object[]> results = databaseManager.getEntityManager().createQuery(
                        "SELECT s.scubaCertificationName, COUNT(s) FROM ScubaExperienceInformation s GROUP BY s.scubaCertificationName",
                        Object[].class)
                .getResultList();

        return results.stream()
                .collect(Collectors.toMap(
                        result -> (String) result[0],  // 자격증 이름
                        result -> (Long) result[1]    // 카운트
                ));
    }

    public List<ClubMemberInformation> getMembersWithCertificationAndTrainingCount(String certification, int logCount) {
        return databaseManager.getEntityManager().createQuery(
                        "SELECT c FROM ClubMemberInformation c WHERE c.studentId IN (" +
                                "SELECT s.studentId FROM ScubaExperienceInformation s " +
                                "WHERE s.scubaCertificationName = :certification AND " +
                                "s.logCount >= :logCount)",
                        ClubMemberInformation.class)
                .setParameter("certification", certification)
                .setParameter("logCount", logCount)
                .getResultList();
    }
}
