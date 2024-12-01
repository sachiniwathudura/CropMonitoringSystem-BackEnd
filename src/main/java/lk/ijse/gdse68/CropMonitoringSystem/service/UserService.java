package lk.ijse.gdse68.CropMonitoringSystem.service;

import lk.ijse.gdse68.CropMonitoringSystem.CustomObj.FieldResponse;
import lk.ijse.gdse68.CropMonitoringSystem.CustomObj.UserResponse;
import lk.ijse.gdse68.CropMonitoringSystem.dto.FieldDTO;
import lk.ijse.gdse68.CropMonitoringSystem.dto.UserServiceDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    void saveUser(UserServiceDTO userServiceDTO);
    void updateUser(String email, UserServiceDTO userServiceDTO);
    void deleteUser(String email);
    UserResponse getSelectedUser(String email);
    List<UserServiceDTO> getAllUsers();
    UserDetailsService userDetailsService();
}
