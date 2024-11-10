package lk.ijse.gdse68.CropMonitoringSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Field")
public class FieldEntity implements SuperEntity{
    @Id
    private String fieldCode;
    private String fieldName;
    private Double extentSize;

    @Column(columnDefinition = "POINT")
    private String fieldLocation;

    @Column(columnDefinition = "LONGTEXT")
    private String image1;  // Renamed from "fieldImage1"

    @Column(columnDefinition = "LONGTEXT")
    private String image2;  // Renamed from "fieldImage2"

    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<CropEntity> crops;

    @ManyToOne
    @JoinColumn(name = "equipmentCode")
    private EquipmentEntity field;

    @ManyToMany
    @JoinTable(
            name = "field_staff_details",
            joinColumns = @JoinColumn(name = "fieldCode"),
            inverseJoinColumns = @JoinColumn(name = "staffId")
    )
    private List<StaffEntity> assignedStaff;

//    @ManyToMany
//    @JoinTable(
//            name = "field_monitoeingLog",
//            joinColumns = @JoinColumn(name = "fieldCode"),
//            inverseJoinColumns = @JoinColumn(name = "logCode")
//    )
//    private List<MonitoringLogEntity> monitoringLogEntities;
    @OneToMany(mappedBy = "field",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<MonitoringLogEntity>  monitoringLogEntity;

}
