package lk.ijse.gdse68.CropMonitoringSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "vehicle")
@Entity
public class VehicleEntity implements SuperEntity{
    @Id
    private String vehicleCode;
    private String licensePlate;
    private String category;
    private String fuelType;
    private String status;
    private String remarks;

    @OneToMany(mappedBy = "vehicleEntity")
    private List<StaffEntity> assignedDriver;
}
