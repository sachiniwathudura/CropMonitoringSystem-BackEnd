package lk.ijse.gdse68.CropMonitoringSystem.service;

import lk.ijse.gdse68.CropMonitoringSystem.CustomObj.CropResponse;
import lk.ijse.gdse68.CropMonitoringSystem.dto.CropDTO;

import java.util.List;

public interface CropService {
    void saveCrop(CropDTO cropDTO);
    void updateCrop(String cropCode, CropDTO cropDTO);
    void deleteCrop(String cropCode);
    CropResponse getSelectedCrop(String cropCode);
    List<CropDTO> getAllCrops();
}
