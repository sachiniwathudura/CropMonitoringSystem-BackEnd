package lk.ijse.gdse68.CropMonitoringSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "EquipmentService")
public class EquipmentEntity implements SuperEntity{
    @Id
    private String EquipmentId;
    private String name;
    private String type;
    private String status;

    @OneToOne
    @JoinColumn(name = "fieldCode")
    private FieldEntity field;

    @OneToOne
    @JoinColumn(name = "staffId")
    private StaffEntity staff;
}
