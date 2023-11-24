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
@Table(name = "dive_site")
public class DiveSite {
    @Id

    @Column(name = "site_id")
    private Long siteId;

    @Column(name = "site_name")
    private String siteName;

    @Column(name = "area_status")
    @Enumerated(EnumType.STRING)
    private AreaStatus areaStatus;
    public enum AreaStatus {
        OPEN, RESTRICTED
    }
}
