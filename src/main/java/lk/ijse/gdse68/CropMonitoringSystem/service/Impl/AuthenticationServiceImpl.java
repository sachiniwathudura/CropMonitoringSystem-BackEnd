package lk.ijse.gdse68.CropMonitoringSystem.service.Impl;

import lk.ijse.gdse68.CropMonitoringSystem.JWTModels.JWTResponse;
import lk.ijse.gdse68.CropMonitoringSystem.JWTModels.SignIn;
import lk.ijse.gdse68.CropMonitoringSystem.dao.UserServiceDao;
import lk.ijse.gdse68.CropMonitoringSystem.dto.UserServiceDTO;
import lk.ijse.gdse68.CropMonitoringSystem.entity.UserServiceEntity;
import lk.ijse.gdse68.CropMonitoringSystem.service.AuthenticationService;
import lk.ijse.gdse68.CropMonitoringSystem.service.JWTService;
import lk.ijse.gdse68.CropMonitoringSystem.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserServiceDao userDao;
    private final JWTService jwtService;
    private final Mapping mapping;
    private final PasswordEncoder passwordEncoder;
    //utils
    private final AuthenticationManager authenticationManager;
    @Override
    public JWTResponse signIn(SignIn signIn) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signIn.getEmail(),signIn.getPassword()));
        var userByEmail = userDao.findByEmail(signIn.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var generatedToken = jwtService.generateToken(userByEmail);
        return JWTResponse.builder().token(generatedToken).build() ;


    }

    @Override
    public JWTResponse signUp(UserServiceDTO userDTO) {
        UserServiceEntity user = mapping.convertToUserEntity(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        var savedUser = userDao.save(user);
        var genToken = jwtService.generateToken(savedUser);
        return JWTResponse.builder().token(genToken).build();
    }

    @Override
    public JWTResponse refreshToken(String accessToken) {
        var userName = jwtService.extractUsername(accessToken);
        var userEntity =
                userDao.findByEmail(userName).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var refreshToken = jwtService.refreshToken(userEntity);
        return JWTResponse.builder().token(refreshToken).build();
    }
}
