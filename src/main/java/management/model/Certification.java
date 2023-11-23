package management.model;

public class Certification {
    private int certificationId;
    private String certificationName;
    private boolean instructorRequirement;
    private int openWaterLimit;
    private int restrictedWaterLimit;

    // 생성자
    public Certification(int certificationId, String certificationName, boolean instructorRequirement, int openWaterLimit, int restrictedWaterLimit) {
        this.certificationId = certificationId;
        this.certificationName = certificationName;
        this.instructorRequirement = instructorRequirement;
        this.openWaterLimit = openWaterLimit;
        this.restrictedWaterLimit = restrictedWaterLimit;
    }

    // Getters and Setters
    public int getCertificationId() {
        return certificationId;
    }

    public void setCertificationId(int certificationId) {
        this.certificationId = certificationId;
    }

    public String getCertificationName() {
        return certificationName;
    }

    public void setCertificationName(String certificationName) {
        this.certificationName = certificationName;
    }

    public boolean isInstructorRequirement() {
        return instructorRequirement;
    }

    public void setInstructorRequirement(boolean instructorRequirement) {
        this.instructorRequirement = instructorRequirement;
    }

    public int getOpenWaterLimit() {
        return openWaterLimit;
    }

    public void setOpenWaterLimit(int openWaterLimit) {
        this.openWaterLimit = openWaterLimit;
    }

    public int getRestrictedWaterLimit() {
        return restrictedWaterLimit;
    }

    public void setRestrictedWaterLimit(int restrictedWaterLimit) {
        this.restrictedWaterLimit = restrictedWaterLimit;
    }

    // toString 메서드
    @Override
    public String toString() {
        return "Certification{" +
                "certificationId=" + certificationId +
                ", certificationName='" + certificationName + '\'' +
                ", instructorRequirement=" + instructorRequirement +
                ", openWaterLimit=" + openWaterLimit +
                ", restrictedWaterLimit=" + restrictedWaterLimit +
                '}';
    }
}

