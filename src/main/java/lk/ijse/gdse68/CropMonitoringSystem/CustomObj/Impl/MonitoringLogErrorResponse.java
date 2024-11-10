package lk.ijse.gdse68.CropMonitoringSystem.CustomObj.Impl;

import lk.ijse.gdse68.CropMonitoringSystem.CustomObj.MonitoringLogResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class MonitoringLogErrorResponse implements MonitoringLogResponse {
    private int errorCode;
    private String errorMessage;
}
