package lk.ijse.gdse68.CropMonitoringSystem.controller;

import lk.ijse.gdse68.CropMonitoringSystem.CustomObj.FieldResponse;
import lk.ijse.gdse68.CropMonitoringSystem.dto.FieldDTO;
import lk.ijse.gdse68.CropMonitoringSystem.exception.DataPersistFailedException;
import lk.ijse.gdse68.CropMonitoringSystem.exception.FieldNotFoundException;
import lk.ijse.gdse68.CropMonitoringSystem.service.FieldService;
import lk.ijse.gdse68.CropMonitoringSystem.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
//@CrossOrigin("*")
@CrossOrigin(origins = "http://127.0.0.1:5500", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH, RequestMethod.OPTIONS})
public class FieldController {
    static Logger logger = LoggerFactory.getLogger(FieldController.class);
    @Autowired
    private FieldService fieldService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> saveFields(
            @RequestParam("fieldCode")String fieldCode,
            @RequestParam("fieldName") String fieldName,
            @RequestParam("extentSize") Double extentSize,
            @RequestParam("latitude") double latitude,
            @RequestParam("longitude") double longitude,
            @RequestParam("image1") MultipartFile image1,
            @RequestParam("image2") MultipartFile image2,
            @RequestParam("equipmentCode") String equipmentCode
    ) {
        try {
//            String base64ProfilePic1 = AppUtil.toBase64ProfilePic(img1);
//            String base64ProfilePic2 = AppUtil.toBase64ProfilePic(img2);
            byte[] imageByteCollection1 = image1.getBytes();
            String base64ProfilePic1 = AppUtil.toBase64ProfilePic(imageByteCollection1);

            byte[] imageByteCollection2 = image2.getBytes();
            String base64ProfilePic2 = AppUtil.toBase64ProfilePic(imageByteCollection2);
            //
            FieldDTO fieldDTO = new FieldDTO();
            fieldDTO.setFieldCode(fieldCode);
            fieldDTO.setFieldName(fieldName);
            fieldDTO.setExtentSize(extentSize);
//            fieldDTO.setFieldLocation(fieldLocation);
            fieldDTO.setFieldLocation(new Point((int) latitude, (int) longitude));
            fieldDTO.setImage1(base64ProfilePic1);
            fieldDTO.setImage2(base64ProfilePic2);
            fieldDTO.setEquipmentCode(equipmentCode);

            fieldService.saveField(fieldDTO);
            logger.info("field save successfully");
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
            @RequestParam("image1") MultipartFile img1,
            @RequestParam("image2") MultipartFile img2,
            @RequestParam("equipmentCode") String equipmentCode,
            @RequestParam("staffId")List<String> staffId

    ) {
        try {
//            String base64ProfilePic1 = AppUtil.toBase64ProfilePic(img1);
//            String base64ProfilePic2 = AppUtil.toBase64ProfilePic(img2);
            // Convert images to Base64
            byte[] imageByteCollection1 = img1.getBytes();
            String base64ProfilePic1 = AppUtil.toBase64ProfilePic(imageByteCollection1);

            byte[] imageByteCollection2 = img2.getBytes();
            String base64ProfilePic2 = AppUtil.toBase64ProfilePic(imageByteCollection2);
            //
            FieldDTO updatefieldDTO = new FieldDTO();
            updatefieldDTO.setFieldName(fieldName);
            updatefieldDTO.setExtentSize(Double.valueOf(extentSize));
            updatefieldDTO.setFieldLocation(new Point((int) latitude, (int) longitude));
            updatefieldDTO.setImage1(base64ProfilePic1);
            updatefieldDTO.setImage2(base64ProfilePic2);
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
