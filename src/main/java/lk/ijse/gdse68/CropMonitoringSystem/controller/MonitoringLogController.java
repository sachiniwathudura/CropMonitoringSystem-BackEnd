package lk.ijse.gdse68.CropMonitoringSystem.controller;

import lk.ijse.gdse68.CropMonitoringSystem.CustomObj.CropResponse;
import lk.ijse.gdse68.CropMonitoringSystem.CustomObj.MonitoringLogResponse;
import lk.ijse.gdse68.CropMonitoringSystem.dto.CropDTO;
import lk.ijse.gdse68.CropMonitoringSystem.dto.MonitoringLogDTO;
import lk.ijse.gdse68.CropMonitoringSystem.exception.CropNotFoundException;
import lk.ijse.gdse68.CropMonitoringSystem.exception.DataPersistFailedException;
import lk.ijse.gdse68.CropMonitoringSystem.exception.MonitoringLogNotFoundException;
import lk.ijse.gdse68.CropMonitoringSystem.service.MonitoringLogService;
import lk.ijse.gdse68.CropMonitoringSystem.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/monitoring")
@RequiredArgsConstructor
public class MonitoringLogController {
    @Autowired
    private MonitoringLogService monitoringLogService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> saveLog(
            @RequestPart("logDate") Date logDate,
            @RequestPart("observationDetails")String observationDetails,
            @RequestPart("observedImage") MultipartFile observedImage
    ){
        try {
            String base64LogImg = AppUtil.toBase64LogImg(String.valueOf(observedImage));
            MonitoringLogDTO saveMonitoringLogDTO = new MonitoringLogDTO();
            saveMonitoringLogDTO.setLogDate(logDate);
            saveMonitoringLogDTO.setObservationDetails(observationDetails);
            saveMonitoringLogDTO.setObservedImage(String.valueOf(observedImage));
            saveMonitoringLogDTO.setObservedImage(base64LogImg);
            monitoringLogService.saveMonitoringLog(saveMonitoringLogDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistFailedException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PatchMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> upadetLog(
            @PathVariable("logCode") String logCode,
            @RequestPart("logDate") Date logDate,
            @RequestPart("observationDetails")String observationDetails,
            @RequestPart("observedImage") MultipartFile observedImage,
            @RequestPart("field")String field,
            @RequestPart("crop")String crop,
            @RequestPart("staff")String staff
    ){
        try {
            String base64LogImg = AppUtil.toBase64LogImg(String.valueOf(observedImage));
            MonitoringLogDTO updateMonitoringLogDTO = new MonitoringLogDTO();
            updateMonitoringLogDTO.setLogDate(logDate);
            updateMonitoringLogDTO.setObservationDetails(observationDetails);
            updateMonitoringLogDTO.setObservedImage(String.valueOf(observedImage));
            updateMonitoringLogDTO.setObservedImage(base64LogImg);
            monitoringLogService.updateMonitoringLog(logCode,updateMonitoringLogDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistFailedException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping(value = "/{logCode}")
    public ResponseEntity<Void> deleteLog(@PathVariable("logCode") String logCode) {
        try {
            monitoringLogService.deleteMonitoringLog(logCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (MonitoringLogNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value="/{logCode}",produces = MediaType.APPLICATION_JSON_VALUE)
    public MonitoringLogResponse getLog(@PathVariable ("logCode") String logCode){
        return monitoringLogService.getSelectedMonitoringLog(logCode);
    }
    @GetMapping(value = "allLogs", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MonitoringLogDTO> getAllCrops() {
        return monitoringLogService.getAllMonitoringLogs();
    }

}
