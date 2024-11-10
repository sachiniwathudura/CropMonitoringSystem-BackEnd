package lk.ijse.gdse68.CropMonitoringSystem.CustomObj.Impl;

import lk.ijse.gdse68.CropMonitoringSystem.CustomObj.VehicleResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehicleErrorResponse implements VehicleResponse, Serializable {
    private int errorCode;
    private String errorMessage;
}
