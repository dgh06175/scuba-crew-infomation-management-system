package management.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "certification")
public class Certification {
    @Id
    @Column(name = "certification_id")
    private Long certificationId;

    @Column(name = "certification_name")
    private String certificationName;

    @Column(name = "instructor_requirement")
    private Boolean instructorRequirement;

    @Column(name = "open_water_limit")
    private Integer openWaterLimit;

    @Column(name = "restricted_water_limit")
    private Integer restrictedWaterLimit;

    // ... 필요한 경우 추가 메서드나 필드
}
