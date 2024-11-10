package lk.ijse.gdse68.CropMonitoringSystem.service;

import lk.ijse.gdse68.CropMonitoringSystem.CustomObj.EquipmentServiceResponse;
import lk.ijse.gdse68.CropMonitoringSystem.dto.EquipmentDTO;

import java.util.List;

public interface EquipmentService {
    void saveEquipment(EquipmentDTO equipmentDTO) ;
    void updateEquipment(String  EquipmentId,EquipmentDTO equipmentDTO);
    void deleteEquipment(String  EquipmentId);
    EquipmentServiceResponse getSelectedEquipment(String  EquipmentId);
    List<EquipmentDTO> getAllEquipments();
}
