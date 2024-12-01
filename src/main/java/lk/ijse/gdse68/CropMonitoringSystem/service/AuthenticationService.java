package lk.ijse.gdse68.CropMonitoringSystem.service;

import lk.ijse.gdse68.CropMonitoringSystem.JWTModels.JWTResponse;
import lk.ijse.gdse68.CropMonitoringSystem.JWTModels.SignIn;
import lk.ijse.gdse68.CropMonitoringSystem.dto.UserServiceDTO;

public interface AuthenticationService {
    JWTResponse signIn(SignIn signIn);
    JWTResponse signUp(UserServiceDTO userDTO);
    JWTResponse refreshToken(String accessToken);
}
