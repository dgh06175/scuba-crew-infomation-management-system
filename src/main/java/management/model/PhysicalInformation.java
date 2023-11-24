package management.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "physical_information")
public class PhysicalInformation {
    @Id
    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "age")
    private Integer age;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "height")
    private Integer height;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "shoe_size")
    private Integer shoeSize;

    @Column(name = "health_notes")
    private String healthNotes;

    public enum Gender {
        MALE, FEMALE
    }
}
