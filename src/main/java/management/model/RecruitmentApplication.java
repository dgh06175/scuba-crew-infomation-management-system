package management.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "recruitment_application")
public class RecruitmentApplication {
    @Id
    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "name")
    private String name;

    @Column(name = "department")
    private String department;

    @Column(name = "grade")
    private Integer grade;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "purpose_of_joining")
    private String purposeOfJoining;

    @Column(name = "scuba_certification_name")
    private String scubaCertificationName;

    @Column(name = "reason_for_joining")
    private String reasonForJoining;
}
