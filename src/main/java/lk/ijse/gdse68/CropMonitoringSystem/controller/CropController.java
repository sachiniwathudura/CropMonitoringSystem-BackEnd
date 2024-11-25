package lk.ijse.gdse68.CropMonitoringSystem.controller;

import lk.ijse.gdse68.CropMonitoringSystem.CustomObj.CropResponse;
import lk.ijse.gdse68.CropMonitoringSystem.dto.CropDTO;
import lk.ijse.gdse68.CropMonitoringSystem.exception.CropNotFoundException;
import lk.ijse.gdse68.CropMonitoringSystem.exception.DataPersistFailedException;
import lk.ijse.gdse68.CropMonitoringSystem.service.CropService;
import lk.ijse.gdse68.CropMonitoringSystem.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/crops")
@RequiredArgsConstructor
public class CropController {

    @Autowired
    private CropService cropService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> saveCrops(
            @RequestPart("commonName")String commonName,
            @RequestPart("scientificName")String scientificName,
            @RequestPart("image") MultipartFile image,
            @RequestPart("category")String category,
            @RequestPart("cropSeason")String cropSeason,
            @RequestPart("fieldCode")String fieldCode
    ){
        try {
            String base64ProfilePic = AppUtil.toBase64ProfilePic(image);
            // build the user object
            CropDTO buildCropDTO = new CropDTO();
            buildCropDTO.setCommonName(commonName);
            buildCropDTO.setScientificName(scientificName);
            buildCropDTO.setImage(base64ProfilePic);
            buildCropDTO.setCategory(category);
            buildCropDTO.setCropSeason(cropSeason);
            buildCropDTO.setFieldCode(fieldCode);
            cropService.saveCrop(buildCropDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistFailedException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping(value = "/{cropCode}", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateCrop(@PathVariable("cropCode") String cropCode,
                                           @RequestParam("commonName")String commonName,
                                           @RequestParam("scientificName")String scientificName,
                                           @RequestParam("image") MultipartFile image,
                                           @RequestParam("category")String category,
                                           @RequestParam("cropSeason")String cropSeason,
                                           @RequestParam("fieldCode")String fieldCode,
                                           @RequestParam("logCode")List<String> logCode
    ){
        try {
            String base64ProfilePic = AppUtil.toBase64ProfilePic(image);
            CropDTO updateCropDTO = new CropDTO();
            updateCropDTO.setCommonName(commonName);
            updateCropDTO.setScientificName(scientificName);
            updateCropDTO.setImage(base64ProfilePic);
            updateCropDTO.setCategory(category);
            updateCropDTO.setCropSeason(cropSeason);
            updateCropDTO.setFieldCode(fieldCode);
            updateCropDTO.setLogCode(logCode);
            cropService.updateCrop(cropCode, updateCropDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CropNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{cropCode}")
    public ResponseEntity<Void> deleteCrop(@PathVariable("cropCode") String cropCode) {
        try {
            cropService.deleteCrop(cropCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CropNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value="/{cropCode}",produces = MediaType.APPLICATION_JSON_VALUE)
    public CropResponse getCrop(@PathVariable ("cropCode") String cropCode){
        return cropService.getSelectedCrop(cropCode);
    }
    @GetMapping(value = "allcrops", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CropDTO> getAllCrops() {
        return cropService.getAllCrops();
    }
}
