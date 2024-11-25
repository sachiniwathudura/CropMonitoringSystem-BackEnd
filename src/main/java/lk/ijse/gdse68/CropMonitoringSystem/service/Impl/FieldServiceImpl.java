package lk.ijse.gdse68.CropMonitoringSystem.service.Impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse68.CropMonitoringSystem.CustomObj.Impl.FieldErrorResponse;
import lk.ijse.gdse68.CropMonitoringSystem.CustomObj.FieldResponse;
import lk.ijse.gdse68.CropMonitoringSystem.dao.EquipmentServiceDao;
import lk.ijse.gdse68.CropMonitoringSystem.dao.FieldDao;
import lk.ijse.gdse68.CropMonitoringSystem.dao.StaffDao;
import lk.ijse.gdse68.CropMonitoringSystem.dto.FieldDTO;
import lk.ijse.gdse68.CropMonitoringSystem.entity.CropEntity;
import lk.ijse.gdse68.CropMonitoringSystem.entity.EquipmentEntity;
import lk.ijse.gdse68.CropMonitoringSystem.entity.FieldEntity;
import lk.ijse.gdse68.CropMonitoringSystem.entity.StaffEntity;
import lk.ijse.gdse68.CropMonitoringSystem.exception.CropNotFoundException;
import lk.ijse.gdse68.CropMonitoringSystem.exception.DataPersistFailedException;
import lk.ijse.gdse68.CropMonitoringSystem.exception.FieldNotFoundException;
import lk.ijse.gdse68.CropMonitoringSystem.service.FieldService;
import lk.ijse.gdse68.CropMonitoringSystem.util.AppUtil;
import lk.ijse.gdse68.CropMonitoringSystem.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FieldServiceImpl implements FieldService {

    @Autowired
    private FieldDao fieldDao;
    @Autowired
    private EquipmentServiceDao equipmentServiceDao;

    @Autowired
    private StaffDao staffDao;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveField(FieldDTO fieldDTO) {
        fieldDTO.setFieldCode(AppUtil.createFieldCode());
        var fieldEntity = mapping.convertToEntity(fieldDTO);
        var saveField= fieldDao.save(fieldEntity);
        if(saveField==null){
            throw new DataPersistFailedException("can not save field");
        }
    }

    @Override
    public void updateField(String fieldCode, FieldDTO fieldDTO) {
        Optional<FieldEntity> byId = fieldDao.findById(fieldCode);
        if(!byId.isPresent()){
            throw new FieldNotFoundException("Couldn't find the field");
        }else{
            FieldEntity field = byId.get();
            // Set other fields
            field.setFieldName(fieldDTO.getFieldName());
            field.setExtentSize(fieldDTO.getExtentSize());
            field.setFieldLocation(fieldDTO.getFieldLocation());
            field.setImage1(fieldDTO.getImg1());
            field.setImage2(fieldDTO.getImg2());

            // Fetch the FieldEntity based on fieldCode
            Optional<EquipmentEntity>equipmentEntity = equipmentServiceDao.findById(fieldDTO.getEquipmentCode());
            if (!equipmentEntity.isPresent()) {
                throw new FieldNotFoundException("Field not found for EquipmentCode: " + fieldDTO.getEquipmentCode());
            }
            field.setEquipment(equipmentEntity.get());

            if (fieldDTO.getStaffId() != null) {
                // If you want to update multiple fields, you would need to use findAllById
                List<StaffEntity> staffEntities =staffDao .findAllById(fieldDTO.getStaffId());
                field.setAssignedStaff(staffEntities);
            }
            // Save the updated cropEntity back to the database
            fieldDao.save(field);

        }

    }

    @Override
    public void deleteField(String fieldCode) {
        Optional<FieldEntity> tmpFieldEntity = fieldDao.findById(fieldCode);
        if(!tmpFieldEntity.isPresent()){
            throw new CropNotFoundException("Field deleted successfully");
        }else {
            fieldDao.deleteById(fieldCode);
        }
    }

    @Override
    public FieldResponse getSelectedField(String fieldCode) {
        if(fieldDao.existsById(fieldCode)){
            return mapping.convertFieldEntityToDTO(fieldDao.getReferenceById(fieldCode));
        }else{
            return new FieldErrorResponse(0,"field not found");
        }
    }

    @Override
    public List<FieldDTO> getAllFields() {
        return mapping.convertF_EntityListToDTOList(fieldDao.findAll());
    }
}
