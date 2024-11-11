package lk.ijse.gdse68.CropMonitoringSystem.dto;

import lk.ijse.gdse68.CropMonitoringSystem.CustomObj.UserResponse;
import lk.ijse.gdse68.CropMonitoringSystem.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserServiceDTO implements SuperDTO, UserResponse {
    private String email;
    private String password;
    private Role role;
}
