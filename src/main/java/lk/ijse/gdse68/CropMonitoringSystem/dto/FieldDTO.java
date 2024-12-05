package lk.ijse.gdse68.CropMonitoringSystem.dto;

import lk.ijse.gdse68.CropMonitoringSystem.CustomObj.FieldResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FieldDTO implements SuperDTO, FieldResponse {
    private String fieldCode;
    private String fieldName;
    private double extentSize;
    private Point fieldLocation;
    private String image1;
    private String image2;
    private String equipmentCode;
    private List<String> staffId;
}
