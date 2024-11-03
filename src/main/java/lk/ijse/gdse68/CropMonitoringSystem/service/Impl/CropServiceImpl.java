package lk.ijse.gdse68.CropMonitoringSystem.service.Impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse68.CropMonitoringSystem.CustomObj.CropErrorResponse;
import lk.ijse.gdse68.CropMonitoringSystem.CustomObj.CropResponse;
import lk.ijse.gdse68.CropMonitoringSystem.dao.CropDao;
import lk.ijse.gdse68.CropMonitoringSystem.dto.CropDTO;
import lk.ijse.gdse68.CropMonitoringSystem.entity.CropEntity;
import lk.ijse.gdse68.CropMonitoringSystem.exception.CropNotFoundException;
import lk.ijse.gdse68.CropMonitoringSystem.exception.DataPersistFailedException;
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
        Optional<CropEntity> tmpcropEntity = cropDao.findById(cropCode);
        if(!tmpcropEntity.isPresent()){
            throw new CropNotFoundException("crop not found");
        }else{
            tmpcropEntity.get().setCommonName(cropDTO.getCommonName());
            tmpcropEntity.get().setScientificName(cropDTO.getScientificName());
            tmpcropEntity.get().setImage(tmpcropEntity.get().getImage());
            tmpcropEntity.get().setCropSeason(tmpcropEntity.get().getCropSeason());
            tmpcropEntity.get().setField(tmpcropEntity.get().getField());
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
