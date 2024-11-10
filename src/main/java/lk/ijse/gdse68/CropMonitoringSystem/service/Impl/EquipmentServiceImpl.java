package lk.ijse.gdse68.CropMonitoringSystem.service.Impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse68.CropMonitoringSystem.CustomObj.Impl.EquipmentServiceErrorResponse;
import lk.ijse.gdse68.CropMonitoringSystem.CustomObj.EquipmentServiceResponse;
import lk.ijse.gdse68.CropMonitoringSystem.dao.EquipmentServiceDao;
import lk.ijse.gdse68.CropMonitoringSystem.dao.FieldDao;
import lk.ijse.gdse68.CropMonitoringSystem.dao.StaffDao;
import lk.ijse.gdse68.CropMonitoringSystem.dto.EquipmentDTO;
import lk.ijse.gdse68.CropMonitoringSystem.entity.EquipmentEntity;
import lk.ijse.gdse68.CropMonitoringSystem.exception.CropNotFoundException;
import lk.ijse.gdse68.CropMonitoringSystem.exception.DataPersistFailedException;
import lk.ijse.gdse68.CropMonitoringSystem.service.EquipmentService;
import lk.ijse.gdse68.CropMonitoringSystem.util.AppUtil;
import lk.ijse.gdse68.CropMonitoringSystem.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        equipmentDTO.setEquipmentCode(AppUtil.createEquipmentId());
        var equipmentEntity = mapping.convertToEntity(equipmentDTO);
        var saveEquipment= equipmentDao.save(equipmentEntity);
        if(saveEquipment==null){
            throw new DataPersistFailedException("cannot save equipment");
        }
    }

    @Override
    public void updateEquipment(String EquipmentId, EquipmentDTO equipmentDTO) {
//        Optional<EquipmentEntity> tmpEquipEntity = equipmentDao.findById(EquipmentId);
//
//        if (!tmpEquipEntity.isPresent()) {
//            throw new CropNotFoundException("Equipment not found");
//        } else {
//            EquipmentEntity equipment = tmpEquipEntity.get();
//
//            // Update simple fields
//            equipment.setEquipmentName(equipmentDTO.getName());
//            equipment.setType(String.valueOf(EquipmentType.valueOf(equipmentDTO.getType())));
//            equipment.setStatus(equipmentDTO.getStatus());
//
//            // Update staff association
//            List<StaffEntity> staffEntities = new ArrayList<>();
//            for (String staffId : equipmentDTO.getStaffIds()) {
//                StaffEntity staff = staffDao.findById(staffId)
//                        .orElseThrow(() -> new CropNotFoundException("Staff not found: " + staffId));
//                staffEntities.add(staff);
//            }
//            equipment.setAssignedStaff(staffEntities);
//
//            // Update field association
//            List<FieldEntity> fieldEntities = new ArrayList<>();
//            for (String fieldCode : equipmentDTO.getFieldCodes()) {
//                FieldEntity field = fieldDao.findById(fieldCode)
//                        .orElseThrow(() -> new CropNotFoundException("Field not found: " + fieldCode));
//                fieldEntities.add(field);
//            }
//            equipment.((FieldEntity) fieldEntities);
//            equipmentDao.save(equipment);
//        }
    }

    @Override
    public void deleteEquipment(String EquipmentId) {
        Optional<EquipmentEntity> tmpEquipEntity = equipmentDao.findById(EquipmentId);
        if(!tmpEquipEntity.isPresent()){
            throw new CropNotFoundException("Equipment not found");
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
