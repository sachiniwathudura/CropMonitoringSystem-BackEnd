package lk.ijse.gdse68.CropMonitoringSystem.CustomObj.Impl;

import lk.ijse.gdse68.CropMonitoringSystem.CustomObj.EquipmentServiceResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EquipmentServiceErrorResponse implements EquipmentServiceResponse, Serializable {
    private int errorCode;
    private String errorMessage;
}

