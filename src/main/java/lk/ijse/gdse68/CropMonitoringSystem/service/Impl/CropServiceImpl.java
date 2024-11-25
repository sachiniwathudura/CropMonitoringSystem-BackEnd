package lk.ijse.gdse68.CropMonitoringSystem.service.Impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse68.CropMonitoringSystem.CustomObj.Impl.CropErrorResponse;
import lk.ijse.gdse68.CropMonitoringSystem.CustomObj.CropResponse;
import lk.ijse.gdse68.CropMonitoringSystem.dao.CropDao;
import lk.ijse.gdse68.CropMonitoringSystem.dao.FieldDao;
import lk.ijse.gdse68.CropMonitoringSystem.dao.MonitoringLogDao;
import lk.ijse.gdse68.CropMonitoringSystem.dto.CropDTO;
import lk.ijse.gdse68.CropMonitoringSystem.entity.CropEntity;
import lk.ijse.gdse68.CropMonitoringSystem.entity.FieldEntity;
import lk.ijse.gdse68.CropMonitoringSystem.entity.MonitoringLogEntity;
import lk.ijse.gdse68.CropMonitoringSystem.exception.CropNotFoundException;
import lk.ijse.gdse68.CropMonitoringSystem.exception.DataPersistFailedException;
import lk.ijse.gdse68.CropMonitoringSystem.exception.FieldNotFoundException;
import lk.ijse.gdse68.CropMonitoringSystem.service.CropService;
import lk.ijse.gdse68.CropMonitoringSystem.util.AppUtil;
import lk.ijse.gdse68.CropMonitoringSystem.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CropServiceImpl implements CropService {
    @Autowired
    private CropDao cropDao;
    @Autowired
    private FieldDao fieldDao;
    @Autowired
    private MonitoringLogDao monitoringLogDao;

    @Autowired
    private Mapping mapping;

    @Override
    public void saveCrop(CropDTO cropDTO) {
        cropDTO.setCropCode(AppUtil.createCropId());
        var cropEntity = mapping.convertToEntity(cropDTO);
        var saveCrop= cropDao.save(cropEntity);
        if(saveCrop==null){
            throw new DataPersistFailedException("cannot save note");
        }
    }

    @Override
    public void updateCrop(String cropCode, CropDTO cropDTO) {
        Optional<CropEntity> byId = cropDao.findById(cropCode);
        if(!byId.isPresent()){
            throw new CropNotFoundException("crop not found");
        }else {
            CropEntity cropEntity = byId.get();
            cropEntity.setCommonName(cropDTO.getCommonName());
            cropEntity.setScientificName(cropDTO.getScientificName());
            cropEntity.setImage(cropDTO.getImage());
            cropEntity.setCategory(cropDTO.getCategory());
            cropEntity.setCropSeason(cropDTO.getCropSeason());

            // Fetch the FieldEntity based on fieldCode
            Optional<FieldEntity> fieldEntityOpt = fieldDao.findById(cropDTO.getFieldCode());
            if (!fieldEntityOpt.isPresent()) {
                throw new FieldNotFoundException("Field not found for fieldCode: " + cropDTO.getFieldCode());
            }
            cropEntity.setField(fieldEntityOpt.get());

            if (cropDTO.getLogCode() != null) {
                // If you want to update multiple fields, you would need to use findAllById
                List<MonitoringLogEntity> logEntities = monitoringLogDao.findAllById(cropDTO.getLogCode());
                cropEntity.setLogEntities(logEntities);
            }

            // Save the updated cropEntity back to the database
            cropDao.save(cropEntity);
        }
    }

    @Override
    public void deleteCrop(String cropCode) {
        Optional<CropEntity> tmpcropEntity = cropDao.findById(cropCode);
        if(!tmpcropEntity.isPresent()){
            throw new CropNotFoundException("crop not found");
        }else{
            cropDao.deleteById(cropCode);
        }
    }

    @Override
    public CropResponse getSelectedCrop(String cropCode) {
        if(cropDao.existsById(cropCode)){
            return mapping.convertToDTO(cropDao.getReferenceById(cropCode));
        }else{
            return new CropErrorResponse(0,"crop not found");
        }
    }

    @Override
    public List<CropDTO> getAllCrops() {
        return mapping.convertToDTOList(cropDao.findAll());
    }
}
