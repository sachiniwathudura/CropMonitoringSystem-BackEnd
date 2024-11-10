package lk.ijse.gdse68.CropMonitoringSystem.service.Impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse68.CropMonitoringSystem.CustomObj.Impl.MonitoringLogErrorResponse;
import lk.ijse.gdse68.CropMonitoringSystem.CustomObj.MonitoringLogResponse;
import lk.ijse.gdse68.CropMonitoringSystem.dao.MonitoringLogDao;
import lk.ijse.gdse68.CropMonitoringSystem.dto.MonitoringLogDTO;
import lk.ijse.gdse68.CropMonitoringSystem.entity.MonitoringLogEntity;
import lk.ijse.gdse68.CropMonitoringSystem.exception.CropNotFoundException;
import lk.ijse.gdse68.CropMonitoringSystem.exception.DataPersistFailedException;
import lk.ijse.gdse68.CropMonitoringSystem.service.MonitoringLogService;
import lk.ijse.gdse68.CropMonitoringSystem.util.AppUtil;
import lk.ijse.gdse68.CropMonitoringSystem.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MonitoringLogServiceImpl implements MonitoringLogService {
    @Autowired
    private MonitoringLogDao monitoringLogDao;
    @Autowired
    private Mapping mapping;
    @Override
    public void saveMonitoringLog(MonitoringLogDTO monitoringLogDTO) {
        monitoringLogDTO.setLogCode(AppUtil.createMonitoringLogId());
        var MonitoringLogoEntity=mapping.convertToEntity(monitoringLogDTO);
        var saveMonitoringLog=monitoringLogDao.save(MonitoringLogoEntity);
        if(saveMonitoringLog==null){
            throw new DataPersistFailedException("cannot save monitoring log");
        }
    }

    @Override
    public void updateMonitoringLog(String logCode, MonitoringLogDTO monitoringLogDTO) {

    }

    @Override
    public void deleteMonitoringLog(String logCode) {
        Optional<MonitoringLogEntity> tmpMLogEntity = monitoringLogDao.findById(logCode);
        if(!tmpMLogEntity.isPresent()){
            throw new CropNotFoundException("log is not found");
        }else{
            monitoringLogDao.deleteById(logCode);
        }
    }

    @Override
    public MonitoringLogResponse getSelectedMonitoringLog(String logCode) {
        if(monitoringLogDao.existsById(logCode)){
            return mapping.convertMLogEntityToDto(monitoringLogDao.getReferenceById(logCode));
        }else{
            return new MonitoringLogErrorResponse(0,"log not found");
        }
    }

    @Override
    public List<MonitoringLogDTO> getAllMonitoringLogs() {
        return mapping.convertM_ToDTOList(monitoringLogDao.findAll());
    }
}
