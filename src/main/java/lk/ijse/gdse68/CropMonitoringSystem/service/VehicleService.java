package lk.ijse.gdse68.CropMonitoringSystem.service;

import lk.ijse.gdse68.CropMonitoringSystem.CustomObj.StaffResponse;
import lk.ijse.gdse68.CropMonitoringSystem.CustomObj.VehicleResponse;
import lk.ijse.gdse68.CropMonitoringSystem.dto.StaffDTO;
import lk.ijse.gdse68.CropMonitoringSystem.dto.VehicleDTO;

import java.util.List;

public interface VehicleService {
    void saveVehicle(VehicleDTO vehicleDTO)  ;
    void updateVehicle(String vehicleCode,VehicleDTO vehicleDTO);
    void deleteVehicle(String vehicleCode);
    VehicleResponse getSelectedVehicle(String vehicleCode);
    List<VehicleDTO> getAllVehicle();
}
