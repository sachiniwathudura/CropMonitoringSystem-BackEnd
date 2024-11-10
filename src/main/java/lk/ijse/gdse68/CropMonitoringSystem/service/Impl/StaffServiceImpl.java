package lk.ijse.gdse68.CropMonitoringSystem.service.Impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse68.CropMonitoringSystem.CustomObj.Impl.StaffErrorResponse;
import lk.ijse.gdse68.CropMonitoringSystem.CustomObj.StaffResponse;
import lk.ijse.gdse68.CropMonitoringSystem.dao.StaffDao;
import lk.ijse.gdse68.CropMonitoringSystem.dto.StaffDTO;
import lk.ijse.gdse68.CropMonitoringSystem.entity.StaffEntity;
import lk.ijse.gdse68.CropMonitoringSystem.enums.Gender;
import lk.ijse.gdse68.CropMonitoringSystem.exception.CropNotFoundException;
import lk.ijse.gdse68.CropMonitoringSystem.exception.DataPersistFailedException;
import lk.ijse.gdse68.CropMonitoringSystem.exception.StaffNotFoundException;
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
    private Mapping mapping;
    @Override
    public void saveStaff(StaffDTO staffDTO) {
        staffDTO.setStaffId(AppUtil.createStaffId());
        var StaffEntity=mapping.convertToEntity(staffDTO);
        var saveStaff=staffDao.save(StaffEntity);
        if(saveStaff==null){
            throw new DataPersistFailedException("cannot add staff");
        }
    }

    @Override
    public void updateStaff(String staffId, StaffDTO staffDTO) {
        Optional<StaffEntity>tmpStaff=staffDao.findById(staffId);
        if(!tmpStaff.isPresent()){
            throw new StaffNotFoundException("Staff not found");
        }else{
            // tmpStaff.get().setId(staffDto.getId());
            tmpStaff.get().setFirstName(staffDTO.getFirstName());
            tmpStaff.get().setLastName(staffDTO.getLastName());
            tmpStaff.get().setDesignation(staffDTO.getDesignation());
            tmpStaff.get().setGender(String.valueOf(Gender.valueOf(staffDTO.getGender())));
            tmpStaff.get().setJoinDate(staffDTO.getJoinDate());
            tmpStaff.get().setDOB(staffDTO.getDOB());
            tmpStaff.get().setBuildingNo(staffDTO.getBuildingNo());
            tmpStaff.get().setLane(staffDTO.getLane());
            tmpStaff.get().setCity(staffDTO.getCity());
            tmpStaff.get().setState(staffDTO.getState());
            tmpStaff.get().setPostalCode(staffDTO.getPostalCode());
            tmpStaff.get().setContactNo(staffDTO.getContactNo());
            tmpStaff.get().setEmail(staffDTO.getEmail());

            staffDao.save(tmpStaff.get());
        }
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
