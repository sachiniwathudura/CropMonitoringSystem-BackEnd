package lk.ijse.gdse68.CropMonitoringSystem.dto;

import lk.ijse.gdse68.CropMonitoringSystem.CustomObj.FieldResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FieldDTO implements SuperDTO, FieldResponse {
    private String fieldCode;
    private String fieldName;
    private Double extentSize;
    private String fieldLocation;
    private List<CropDTO> crops;
    private List<StaffDTO> staff;
}
