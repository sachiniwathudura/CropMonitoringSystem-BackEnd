package lk.ijse.gdse68.CropMonitoringSystem.service.Impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse68.CropMonitoringSystem.CustomObj.Impl.FieldErrorResponse;
import lk.ijse.gdse68.CropMonitoringSystem.CustomObj.FieldResponse;
import lk.ijse.gdse68.CropMonitoringSystem.dao.FieldDao;
import lk.ijse.gdse68.CropMonitoringSystem.dto.FieldDTO;
import lk.ijse.gdse68.CropMonitoringSystem.entity.FieldEntity;
import lk.ijse.gdse68.CropMonitoringSystem.exception.CropNotFoundException;
import lk.ijse.gdse68.CropMonitoringSystem.exception.DataPersistFailedException;
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
