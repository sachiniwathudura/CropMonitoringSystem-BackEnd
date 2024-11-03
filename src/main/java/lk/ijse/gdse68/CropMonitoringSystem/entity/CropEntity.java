package lk.ijse.gdse68.CropMonitoringSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Crop")
public class CropEntity implements SuperEntity{
    @Id
    private String cropCode;
    private String commonName;
    private String scientificName;

    @Column(columnDefinition = "LONGTEXT")
    private String image;
    private String category;
    private String cropSeason;

    @ManyToOne
    @JoinColumn(name = "fieldCode")
    private FieldEntity field;

    @OneToMany(mappedBy = "crop")
    private List<MonitoringLogEntity> cropDetailsEntities;
}
