package lk.ijse.gdse68.CropMonitoringSystem.CustomObj.Impl;

import lk.ijse.gdse68.CropMonitoringSystem.CustomObj.CropResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CropErrorResponse implements CropResponse, Serializable {
    private int errorCode;
    private String errorMessage;
}
