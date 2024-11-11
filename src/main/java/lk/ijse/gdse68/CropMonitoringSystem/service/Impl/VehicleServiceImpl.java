package lk.ijse.gdse68.CropMonitoringSystem.service.Impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse68.CropMonitoringSystem.CustomObj.Impl.VehicleErrorResponse;
import lk.ijse.gdse68.CropMonitoringSystem.CustomObj.VehicleResponse;
import lk.ijse.gdse68.CropMonitoringSystem.dao.VehicleDao;
import lk.ijse.gdse68.CropMonitoringSystem.dto.VehicleDTO;
import lk.ijse.gdse68.CropMonitoringSystem.entity.VehicleEntity;
import lk.ijse.gdse68.CropMonitoringSystem.exception.DataPersistFailedException;
import lk.ijse.gdse68.CropMonitoringSystem.exception.VehicleNotFoundException;
import lk.ijse.gdse68.CropMonitoringSystem.service.VehicleService;
import lk.ijse.gdse68.CropMonitoringSystem.util.AppUtil;
import lk.ijse.gdse68.CropMonitoringSystem.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleDao vehicleDao;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveVehicle(VehicleDTO vehicleDTO) {
        vehicleDTO.setVehicleCode(AppUtil.createVehicleCode());
        var VehicleEntity=mapping.convertToVehicleEntity(vehicleDTO);
        var saveVehicle=vehicleDao.save(VehicleEntity);
        if(saveVehicle==null){
            throw new DataPersistFailedException("cannot add vehicle");
        }
    }

    @Override
    public void updateVehicle(String vehicleCode, VehicleDTO vehicleDTO) {
        Optional<VehicleEntity> tmpVehicleEntity= vehicleDao.findById(vehicleCode);
        if(!tmpVehicleEntity.isPresent()){
            throw new VehicleNotFoundException("Vehicle not found");
        }else {
//            tmpVehicleEntity.get().setVehicleCode(vehicleDTO.getVehicleCode());
            tmpVehicleEntity.get().setCategory(vehicleDTO.getCategory());
            tmpVehicleEntity.get().setFuelType(vehicleDTO.getFuelType());
            tmpVehicleEntity.get().setLicensePlate(vehicleDTO.getLicensePlate());
            tmpVehicleEntity.get().setRemarks(vehicleDTO.getRemarks());
            tmpVehicleEntity.get().setStatus(vehicleDTO.getStatus());
        }
    }

    @Override
    public void deleteVehicle(String vehicleCode) {
        Optional<VehicleEntity> tmpVehicleEntity = vehicleDao.findById(vehicleCode);
        if(!tmpVehicleEntity.isPresent()){
            throw new VehicleNotFoundException("vehicle not found");
        }else{
            vehicleDao.deleteById(vehicleCode);
        }
    }

    @Override
    public VehicleResponse getSelectedVehicle(String vehicleCode) {
        if(vehicleDao.existsById(vehicleCode)){
            return mapping.convertVehicleEntityToDTO(vehicleDao.getReferenceById(vehicleCode));
        }else{
            return new VehicleErrorResponse(0,"vehicle not found");
        }
    }

    @Override
    public List<VehicleDTO> getAllVehicle() {
        return mapping.convertToVehicleDTOList(vehicleDao.findAll());
    }
}
