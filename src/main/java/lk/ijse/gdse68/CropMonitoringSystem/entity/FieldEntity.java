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
    private String fieldLocation;

    @OneToMany(mappedBy = "field")
    private List<CropEntity> crops;

    @ManyToMany
    @JoinTable(name = "fieldStaffDetails",
            joinColumns = @JoinColumn(name = "fieldCode"),
            inverseJoinColumns = @JoinColumn(name = "staffId"))
    private List<StaffEntity> staff;

}
