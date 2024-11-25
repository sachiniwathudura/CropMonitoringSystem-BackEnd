package lk.ijse.gdse68.CropMonitoringSystem.controller;

import lk.ijse.gdse68.CropMonitoringSystem.CustomObj.FieldResponse;
import lk.ijse.gdse68.CropMonitoringSystem.dto.FieldDTO;
import lk.ijse.gdse68.CropMonitoringSystem.exception.DataPersistFailedException;
import lk.ijse.gdse68.CropMonitoringSystem.exception.FieldNotFoundException;
import lk.ijse.gdse68.CropMonitoringSystem.service.FieldService;
import lk.ijse.gdse68.CropMonitoringSystem.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("api/v1/fields")
@RequiredArgsConstructor
public class FieldController {
    @Autowired
    private FieldService fieldService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> saveFields(
            @RequestParam("fieldName") String fieldName,
            @RequestParam("extentSize") Double extentSize,
            @RequestParam("latitude") double latitude,
            @RequestParam("longitude") double longitude,
            @RequestParam("img1") MultipartFile img1,
            @RequestParam("img2") MultipartFile img2,
            @RequestParam("equipmentCode") String equipmentCode
    ) {
        try {
            String base64ProfilePic1 = AppUtil.toBase64ProfilePic(img1);
            String base64ProfilePic2 = AppUtil.toBase64ProfilePic(img2);
            //
            FieldDTO fieldDTO = new FieldDTO();
            fieldDTO.setFieldName(fieldName);
            fieldDTO.setExtentSize(extentSize);
//            fieldDTO.setFieldLocation(fieldLocation);
            fieldDTO.setFieldLocation(new Point((int) latitude, (int) longitude));
            fieldDTO.setImg1(base64ProfilePic1);
            fieldDTO.setImg2(base64ProfilePic2);
            fieldDTO.setEquipmentCode(equipmentCode);

            fieldService.saveField(fieldDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistFailedException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping(value = "/{fieldCode}", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateFields(
            @PathVariable("fieldCode") String fieldCode,
            @RequestParam("fieldName") String fieldName,
            @RequestParam("extentSize") String extentSize,
//            @RequestPart("fieldLocation") String fieldLocation,
            @RequestParam("latitude") double latitude,
            @RequestParam("longitude") double longitude,
            @RequestParam("img1") MultipartFile img1,
            @RequestParam("img2") MultipartFile img2,
            @RequestParam("equipmentCode") String equipmentCode,
            @RequestParam("staffId")List<String> staffId

    ) {
        try {
            String base64ProfilePic1 = AppUtil.toBase64ProfilePic(img1);
            String base64ProfilePic2 = AppUtil.toBase64ProfilePic(img2);
            //
            FieldDTO updatefieldDTO = new FieldDTO();
            updatefieldDTO.setFieldName(fieldName);
            updatefieldDTO.setExtentSize(Double.valueOf(extentSize));
            updatefieldDTO.setFieldLocation(new Point((int) latitude, (int) longitude));
            updatefieldDTO.setImg1(base64ProfilePic1);
            updatefieldDTO.setImg2(base64ProfilePic2);
            updatefieldDTO.setEquipmentCode(equipmentCode);
            updatefieldDTO.setStaffId(staffId);
            fieldService.updateField(fieldCode, updatefieldDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistFailedException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping(value = "/{fieldCode}")
    public ResponseEntity<Void> deleteField(@PathVariable("fieldCode") String fieldCode) {
        try {
            fieldService.deleteField(fieldCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (FieldNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "allfields", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FieldDTO> getAllFields() {
        return fieldService.getAllFields();
    }

    @GetMapping(value="/{fieldCode}",produces = MediaType.APPLICATION_JSON_VALUE)
    public FieldResponse getSelectedFiled(@PathVariable ("fieldCode") String fieldCode){
        return fieldService.getSelectedField(fieldCode);
    }


}
