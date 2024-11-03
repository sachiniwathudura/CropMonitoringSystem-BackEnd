package lk.ijse.gdse68.CropMonitoringSystem.util;

import lk.ijse.gdse68.CropMonitoringSystem.dto.*;
import lk.ijse.gdse68.CropMonitoringSystem.entity.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;

    //crop
    public CropDTO convertToDTO(CropEntity entity) {
        return modelMapper.map(entity, CropDTO.class);
    }

    public CropEntity convertToEntity(CropDTO dto) {
        return modelMapper.map(dto, CropEntity.class);
    }

    public List<CropDTO> convertToDTOList(List<CropEntity> notes) {
        return modelMapper.map(notes, new TypeToken<List<CropDTO>>() {
        }.getType());
    }

    //field
    public FieldDTO convertFieldEntityToDTO(FieldEntity entity) {
        return modelMapper.map(entity, FieldDTO.class);
    }

    public FieldEntity convertToEntity(FieldDTO dto) {
        return modelMapper.map(dto, FieldEntity.class);
    }

    public List<FieldDTO> convertF_EntityListToDTOList(List<FieldEntity> fields) {return modelMapper.map(fields, new TypeToken<List<FieldDTO>>() {}.getType());}

    //staff
    public StaffDTO convertStaffEntityToDTO(StaffEntity entity) {
        return modelMapper.map(entity,StaffDTO.class);
    }
    public StaffEntity convertToEntity(StaffDTO dto) {
        return modelMapper.map(dto, StaffEntity.class);
    }
    public List<StaffDTO> convertS_ToDTOList(List<StaffEntity> staffs) {
        return modelMapper.map(staffs, new TypeToken<List<StaffDTO>>(){}.getType());
    }

    //equipment
    public EquipmentDTO convertToDTO(EquipmentEntity entity) {
        return modelMapper.map(entity,EquipmentDTO.class);
    }
    public EquipmentEntity convertToEntity(EquipmentDTO dto) {
        return modelMapper.map(dto, EquipmentEntity.class);
    }
    public List<EquipmentDTO> convertE_ToDTOList(List<EquipmentEntity> equipment) {
        return modelMapper.map(equipment, new TypeToken<List<EquipmentDTO>>(){}.getType());
    }

    //monitoringLog
    public MonitoringLogDTO convertMLogEntityToDto(MonitoringLogEntity entity) {
        return modelMapper.map(entity,MonitoringLogDTO.class);
    }
    public MonitoringLogEntity convertToEntity(MonitoringLogDTO dto) {
        return modelMapper.map(dto, MonitoringLogEntity.class);
    }
    public List<MonitoringLogDTO> convertM_ToDTOList(List<MonitoringLogEntity> logs) {
        return modelMapper.map(logs, new TypeToken<List<MonitoringLogDTO>>(){}.getType());
    }

    //vehicle
    public VehicleDTO convertVehicleEntityToDTO(VehicleEntity entity){
        return modelMapper.map(entity, VehicleDTO.class);
    }
    public VehicleEntity convertToVehicleEntity(List<VehicleEntity> entityList){
        return modelMapper.map(entityList, VehicleEntity.class);
    }
    public List<VehicleDTO> convertToVehicleDTOList(List<VehicleEntity> list){
        return modelMapper.map(list, new TypeToken<List<VehicleDTO>>(){}.getType());
    }
    //user
    public UserServiceDTO convertUserEntityToDTO(UserServiceEntity entity){
        return modelMapper.map(entity,UserServiceDTO.class);
    }
    public UserServiceEntity convertToUserEntity(List<UserServiceEntity> entityList){
        return modelMapper.map(entityList, UserServiceEntity.class);
    }
    public List<UserServiceDTO> convertTOUserDTOList(List<UserServiceEntity> list){
        return modelMapper.map(list, new TypeToken<List<UserServiceDTO>>(){}.getType());
    }

}
