package management.service;

import java.util.function.Consumer;
import management.database.DatabaseManager;
import management.model.ClubMemberInformation;
import management.model.PhysicalInformation;
import management.model.ScubaExperienceInformation;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class ActivityService {
    private final DatabaseManager databaseManager;

    public ActivityService(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }
    public List<ClubMemberInformation> getAllMembers() {
        // 모든 동아리원 정보를 조회하는 쿼리
        return databaseManager.getEntityManager().createQuery(
                        "SELECT m FROM ClubMemberInformation m",ClubMemberInformation.class)
                .getResultList();
    }

    public void updateMembersForCertification(String certification, List<ClubMemberInformation> members) {
        for (ClubMemberInformation member : members) {
            ScubaExperienceInformation scubaInfo = databaseManager.find(ScubaExperienceInformation.class, member.getStudentId());
            scubaInfo.setScubaCertificationName(certification);
            scubaInfo.setLogCount(scubaInfo.getLogCount() + 4);
            databaseManager.update(scubaInfo);
        }
    }

    public void updateMembersForFunDiving(List<ClubMemberInformation> members) {
        for (ClubMemberInformation member : members) {
            ScubaExperienceInformation scubaInfo = databaseManager.find(ScubaExperienceInformation.class, member.getStudentId());
            scubaInfo.setLogCount(scubaInfo.getLogCount() + 1); // 로그 수 증가
            databaseManager.update(scubaInfo);
        }
    }

    public void updateMembersForRestrictedWaterActivity(List<ClubMemberInformation> members) {
        for (ClubMemberInformation member : members) {
            ScubaExperienceInformation scubaInfo = databaseManager.find(ScubaExperienceInformation.class, member.getStudentId());
            scubaInfo.setRestrictedWaterTrainingCount(scubaInfo.getRestrictedWaterTrainingCount() + 1);
            databaseManager.update(scubaInfo);
        }
    }

    public Map<String, Integer> calculateRequiredItems(List<ClubMemberInformation> participatingMembers) {
        Map<String, Integer> requiredItems = new HashMap<>();
        Map<String, Integer> suitSizes = new HashMap<>();
        Map<String, Integer> finSizes = new HashMap<>();

        for (ClubMemberInformation member : participatingMembers) {
            PhysicalInformation physicalInfo = databaseManager.find(PhysicalInformation.class, member.getStudentId());
            if (physicalInfo != null) {
                updateEquipmentCount(suitSizes, calculateSuitSize(physicalInfo.getHeight(), physicalInfo.getWeight()));
                updateEquipmentCount(finSizes, calculateFinSize(physicalInfo.getShoeSize()));
            }
        }

        requiredItems.putAll(suitSizes);
        requiredItems.putAll(finSizes);
        return requiredItems;
    }

    private String calculateSuitSize(int height, int weight) {
        if (weight > 80 || height > 180) {
            return "XL 슈트";
        }
        if (weight > 70 || height > 170) {
            return "L 슈트";
        }
        if (weight > 60 || height > 160) {
            return "M 슈트";
        }
        return "S 슈트";
    }

    private String calculateFinSize(int shoeSize) {
        if (shoeSize > 280) {
            return "L 핀";
        }
        if (shoeSize > 260) {
            return "M 핀";
        }
        return "S 핀";
    }

    private void updateEquipmentCount(Map<String, Integer> equipmentMap, String size) {
        equipmentMap.put(size, equipmentMap.getOrDefault(size, 0) + 1);
    }

    public ScubaExperienceInformation getMemberScubaExperience(Long studentId) {
        return databaseManager.getEntityManager().find(ScubaExperienceInformation.class, studentId);
    }
}
