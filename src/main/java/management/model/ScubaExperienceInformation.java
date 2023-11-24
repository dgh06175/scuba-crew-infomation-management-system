package management.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "scuba_experience_information")
public class ScubaExperienceInformation {
    @Id
    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "scuba_certification_name")
    private String scubaCertificationName;

    @Column(name = "restricted_water_training_count")
    private Integer restrictedWaterTrainingCount;

    @Column(name = "log_count")
    private Integer logCount;

    @OneToOne
    @JoinColumn(name = "student_id", referencedColumnName = "student_id", foreignKey = @ForeignKey(name = "FK_club_member_information"))
    private ClubMemberInformation clubMemberInformation;
}
