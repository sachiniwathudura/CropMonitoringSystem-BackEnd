package lk.ijse.gdse68.CropMonitoringSystem.service.Impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse68.CropMonitoringSystem.CustomObj.Impl.StaffErrorResponse;
import lk.ijse.gdse68.CropMonitoringSystem.CustomObj.StaffResponse;
import lk.ijse.gdse68.CropMonitoringSystem.dao.EquipmentServiceDao;
import lk.ijse.gdse68.CropMonitoringSystem.dao.MonitoringLogDao;
import lk.ijse.gdse68.CropMonitoringSystem.dao.StaffDao;
import lk.ijse.gdse68.CropMonitoringSystem.dao.VehicleDao;
import lk.ijse.gdse68.CropMonitoringSystem.dto.StaffDTO;
import lk.ijse.gdse68.CropMonitoringSystem.entity.EquipmentEntity;
import lk.ijse.gdse68.CropMonitoringSystem.entity.MonitoringLogEntity;
import lk.ijse.gdse68.CropMonitoringSystem.entity.StaffEntity;
import lk.ijse.gdse68.CropMonitoringSystem.entity.VehicleEntity;
import lk.ijse.gdse68.CropMonitoringSystem.enums.Gender;
import lk.ijse.gdse68.CropMonitoringSystem.exception.*;
import lk.ijse.gdse68.CropMonitoringSystem.service.StaffService;
import lk.ijse.gdse68.CropMonitoringSystem.util.AppUtil;
import lk.ijse.gdse68.CropMonitoringSystem.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffDao staffDao;
    @Autowired
    private EquipmentServiceDao equipmentDao;
    @Autowired
    private VehicleDao vehicleDao;
    @Autowired
    private MonitoringLogDao monitoringLogDao;
    @Autowired
    private Mapping mapping;
    @Override
    public void saveStaff(StaffDTO staffDTO) {
        staffDTO.setStaffId(AppUtil.createStaffId());
        StaffEntity staffEntity=mapping.convertToEntity(staffDTO);

        // Fetch and set the EquipmentEntity if equipmentCode is provided
        if (staffDTO.getEquipmentCode() != null) {
            EquipmentEntity equipmentEntity = equipmentDao.findById(staffDTO.getEquipmentCode())
                    .orElseThrow(() -> new EquipmentNotFoundException("Equipment with code " + staffDTO.getEquipmentCode() + " not found!"));
            staffEntity.setEquipmentEntity(equipmentEntity);
        }

        // Fetch and set the VehicleEntity if vehicleCode is provided
        if (staffDTO.getVehicleCode() != null) {
            VehicleEntity vehicleEntity = vehicleDao.findById(staffDTO.getVehicleCode())
                    .orElseThrow(() -> new DataPersistFailedException("Vehicle with code " + staffDTO.getVehicleCode() + " not found!"));
            staffEntity.setVehicleEntity(vehicleEntity);
        }
        // Save the StaffEntity
        StaffEntity savedEntity = staffDao.save(staffEntity);

        // Check if saving was successful
        if (savedEntity == null || savedEntity.getStaffId() == null) {
            throw new DataPersistFailedException("Error occurred while staff persistent!");
        }
    }

    @Override
    public void updateStaff(String staffId, StaffDTO staffDTO) {
        // Fetch the staff entity by ID
        StaffEntity staffEntity = staffDao.findById(staffId)
                .orElseThrow(() -> new StaffNotFoundException("Staff by this ID does not exist!"));

        // Update basic fields from DTO to Entity
        staffEntity.setFirstName(staffDTO.getFirstName());
        staffEntity.setLastName(staffDTO.getLastName());
        staffEntity.setDesignation(staffDTO.getDesignation());
        staffEntity.setGender(staffDTO.getGender());
        staffEntity.setJoinDate(staffDTO.getJoinDate());
        staffEntity.setDob(staffDTO.getDob());
        staffEntity.setBuildingNo(staffDTO.getBuildingNo());
        staffEntity.setLane(staffDTO.getLane());
        staffEntity.setCity(staffDTO.getCity());
        staffEntity.setState(staffDTO.getState());
        staffEntity.setPostalCode(staffDTO.getPostalCode());
        staffEntity.setContactNo(staffDTO.getContactNo());
        staffEntity.setEmail(staffDTO.getEmail());

        // Check if equipmentCode is present and set it
        if (staffDTO.getEquipmentCode() != null) {
            EquipmentEntity equipmentEntity = equipmentDao.findById(staffDTO.getEquipmentCode())
                    .orElseThrow(() -> new EquipmentNotFoundException("Equipment not found for equipmentCode: " + staffDTO.getEquipmentCode()));
            staffEntity.setEquipmentEntity(equipmentEntity);
        }

        // Check if vehicleCode is present and set it
        if (staffDTO.getVehicleCode() != null) {
            VehicleEntity vehicleEntity = vehicleDao.findById(staffDTO.getVehicleCode())
                    .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found for vehicleCode: " + staffDTO.getVehicleCode()));
            staffEntity.setVehicleEntity(vehicleEntity);
        }

        // Check if fieldCode is present and set it
        if (staffDTO.getLogCode() != null) {
            // If you want to update multiple fields, you would need to use findAllById
            List<MonitoringLogEntity> logEntities =monitoringLogDao .findAllById(staffDTO.getLogCode());
            staffEntity.setStaffLogEntities(logEntities);
        }
        staffDao.save(staffEntity);
    }

    @Override
    public void deleteStaff(String staffId) {
        Optional<StaffEntity> tmpStaffEntity = staffDao.findById(staffId);
        if(!tmpStaffEntity.isPresent()){
            throw new CropNotFoundException("staff member not found");
        }else{
            staffDao.delete(tmpStaffEntity.get());
        }
    }

    @Override
    public StaffResponse getSelectedStaff(String staffId) {
        if(staffDao.existsById(staffId)){
            return mapping.convertStaffEntityToDTO(staffDao.getReferenceById(staffId));
        }else{
            return new StaffErrorResponse(0, " staff member not found");
            }
    }

    @Override
    public List<StaffDTO> getAllStaff() {
        return mapping.convertS_ToDTOList(staffDao.findAll());
    }
}
