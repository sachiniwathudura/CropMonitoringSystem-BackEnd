package lk.ijse.gdse68.CropMonitoringSystem.service;

import lk.ijse.gdse68.CropMonitoringSystem.CustomObj.FieldResponse;
import lk.ijse.gdse68.CropMonitoringSystem.dto.CropDTO;
import lk.ijse.gdse68.CropMonitoringSystem.dto.FieldDTO;

import java.util.List;

public interface FieldService {
    void saveField(FieldDTO fieldDTO);
    void updateField(String fieldCode, FieldDTO fieldDTO);
    void deleteField(String fieldCode);
    FieldResponse getSelectedField(String fieldCode);
    List<FieldDTO> getAllFields();
}
