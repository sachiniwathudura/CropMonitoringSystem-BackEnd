package lk.ijse.gdse68.CropMonitoringSystem.dto;

import lk.ijse.gdse68.CropMonitoringSystem.CustomObj.StaffResponse;
import lk.ijse.gdse68.CropMonitoringSystem.entity.FieldEntity;
import lk.ijse.gdse68.CropMonitoringSystem.entity.VehicleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class StaffDTO implements SuperDTO, StaffResponse {
    private String staffId;
    private String firstName;
    private String lastName;
    private String designation;
    private LocalDate joinDate;
    private LocalDate DOB;
    private String buildingNo;
    private String lane;
    private String city;
    private String state;
    private String postalCode;
    private String contactNo;
    private String email;
    private List<FieldEntity> fields;
    private VehicleEntity vehicle;

}
