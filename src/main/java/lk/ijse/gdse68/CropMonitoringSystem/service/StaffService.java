package lk.ijse.gdse68.CropMonitoringSystem.service;

import lk.ijse.gdse68.CropMonitoringSystem.CustomObj.StaffResponse;
import lk.ijse.gdse68.CropMonitoringSystem.dto.StaffDTO;

import java.util.List;

public interface StaffService {
    void saveStaff(StaffDTO staffDTO)  ;
    void updateStaff(String staffId,StaffDTO staffDTO);
    void deleteStaff(String staffId);
    StaffResponse getSelectedStaff(String staffId);
    List<StaffDTO> getAllStaff();

}
