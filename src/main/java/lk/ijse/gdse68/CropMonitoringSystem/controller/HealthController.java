package lk.ijse.gdse68.CropMonitoringSystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/healthTest")
public class HealthController {
    @GetMapping
    public String healthTest(){
        return "app run successfully!";
    }

}
