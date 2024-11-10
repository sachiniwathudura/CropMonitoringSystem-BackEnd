package lk.ijse.gdse68.CropMonitoringSystem.service;

import lk.ijse.gdse68.CropMonitoringSystem.CustomObj.MonitoringLogResponse;
import lk.ijse.gdse68.CropMonitoringSystem.dto.MonitoringLogDTO;

import java.util.List;

public interface MonitoringLogService {
    void saveMonitoringLog(MonitoringLogDTO monitoringLogDTO)  ;
    void updateMonitoringLog(String logCode,MonitoringLogDTO monitoringLogDTO);
    void deleteMonitoringLog(String logCode);
    MonitoringLogResponse getSelectedMonitoringLog(String logCode);
    List<MonitoringLogDTO> getAllMonitoringLogs();

}
