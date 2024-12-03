package lk.ijse.gdse68.CropMonitoringSystem.service.Impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse68.CropMonitoringSystem.CustomObj.Impl.EquipmentServiceErrorResponse;
import lk.ijse.gdse68.CropMonitoringSystem.CustomObj.EquipmentServiceResponse;
import lk.ijse.gdse68.CropMonitoringSystem.dao.EquipmentServiceDao;
import lk.ijse.gdse68.CropMonitoringSystem.dao.FieldDao;
import lk.ijse.gdse68.CropMonitoringSystem.dao.StaffDao;
import lk.ijse.gdse68.CropMonitoringSystem.dto.EquipmentDTO;
import lk.ijse.gdse68.CropMonitoringSystem.entity.EquipmentEntity;
import lk.ijse.gdse68.CropMonitoringSystem.entity.FieldEntity;
import lk.ijse.gdse68.CropMonitoringSystem.entity.StaffEntity;
import lk.ijse.gdse68.CropMonitoringSystem.enums.EquipmentType;
import lk.ijse.gdse68.CropMonitoringSystem.exception.CropNotFoundException;
import lk.ijse.gdse68.CropMonitoringSystem.exception.DataPersistFailedException;
import lk.ijse.gdse68.CropMonitoringSystem.exception.EquipmentNotFoundException;
import lk.ijse.gdse68.CropMonitoringSystem.service.EquipmentService;
import lk.ijse.gdse68.CropMonitoringSystem.util.AppUtil;
import lk.ijse.gdse68.CropMonitoringSystem.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    private EquipmentServiceDao equipmentDao;

    @Autowired
    private StaffDao staffDao;

    @Autowired
    private FieldDao fieldDao;

    @Autowired
    private Mapping mapping;

    @Override
    public void saveEquipment(EquipmentDTO equipmentDTO) {
        equipmentDTO.setEquipmentCode(equipmentDTO.getEquipmentCode());
        var equipmentEntity = mapping.convertToEntity(equipmentDTO);
        var saveEquipment= equipmentDao.save(equipmentEntity);
        if(saveEquipment==null){
            throw new DataPersistFailedException("cannot save equipment");
        }
    }

    @Override
    public void updateEquipment(String EquipmentId, EquipmentDTO equipmentDTO) {
        Optional<EquipmentEntity> tmpEquipEntity = equipmentDao.findById(EquipmentId);

        if (!tmpEquipEntity.isPresent()) {
            throw new EquipmentNotFoundException("Equipment not found");
        } else {
            EquipmentEntity equipment = tmpEquipEntity.get();

            // Update simple fields
            equipment.setEquipmentName(equipmentDTO.getEquipmentCode());
            equipment.setStatus(equipmentDTO.getStatus());
            equipment.setType(EquipmentType.valueOf(String.valueOf(equipmentDTO.getType())));

            equipmentDao.save(equipment);
        }

    }

    @Override
    public void deleteEquipment(String EquipmentId) {
        Optional<EquipmentEntity> tmpEquipEntity = equipmentDao.findById(EquipmentId);
        if(!tmpEquipEntity.isPresent()){
            throw new EquipmentNotFoundException("Equipment not found");
        }else{
            equipmentDao.deleteById(EquipmentId);
        }
    }

    @Override
    public EquipmentServiceResponse getSelectedEquipment(String EquipmentId) {
        if(equipmentDao.existsById(EquipmentId)){
            return mapping.convertToDTO(equipmentDao.getReferenceById(EquipmentId));
        }else{
            return new EquipmentServiceErrorResponse(0, "equipment not found");
        }
    } 

    @Override
    public List<EquipmentDTO> getAllEquipments() {
        return mapping.convertE_ToDTOList(equipmentDao.findAll());
    }
}
