package lk.ijse.gdse68.CropMonitoringSystem.entity;

import jakarta.persistence.*;
import lk.ijse.gdse68.CropMonitoringSystem.enums.EquipmentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Equipment")
public class EquipmentEntity implements SuperEntity{
    @Id
    private String equipmentCode;
    private String equipmentName;
    private String status;
    @Enumerated(EnumType.STRING)
    private EquipmentType type;

    @OneToMany(mappedBy = "equipmentEntity",cascade = CascadeType.ALL,fetch = FetchType.LAZY )
    private List<StaffEntity> staff;

    @OneToMany(mappedBy = "equipment",cascade = CascadeType.ALL,fetch = FetchType.LAZY )
    private List<FieldEntity> fieldEntities;
}
