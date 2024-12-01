package lk.ijse.gdse68.CropMonitoringSystem.service.Impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse68.CropMonitoringSystem.CustomObj.Impl.UserErrorResponse;
import lk.ijse.gdse68.CropMonitoringSystem.CustomObj.UserResponse;
import lk.ijse.gdse68.CropMonitoringSystem.dao.UserServiceDao;
import lk.ijse.gdse68.CropMonitoringSystem.dto.UserServiceDTO;
import lk.ijse.gdse68.CropMonitoringSystem.entity.UserServiceEntity;
import lk.ijse.gdse68.CropMonitoringSystem.enums.Role;
import lk.ijse.gdse68.CropMonitoringSystem.exception.DataPersistFailedException;
import lk.ijse.gdse68.CropMonitoringSystem.exception.UserNotFoundException;
import lk.ijse.gdse68.CropMonitoringSystem.service.UserService;
import lk.ijse.gdse68.CropMonitoringSystem.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class UserServiceImpl implements UserService {
    @Autowired
    private UserServiceDao userServiceDao;
    @Autowired
    private Mapping mapping;
    @Override
    public void saveUser(UserServiceDTO userServiceDTO) {
        var userEntity = mapping.convertToUserEntity( userServiceDTO);
        var saveUser = userServiceDao.save(userEntity);
        if (saveUser==null){
            throw new DataPersistFailedException("can not save user");
        }
    }

    @Override
    public void updateUser(String email, UserServiceDTO userServiceDTO) {
        UserServiceEntity existingUser = userServiceDao.findById(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));

        UserServiceEntity updatedUser = new UserServiceEntity();
        updatedUser.setEmail(userServiceDTO.getEmail() != null ? userServiceDTO.getEmail() : existingUser.getEmail());
        updatedUser.setPassword(userServiceDTO.getPassword() != null ? userServiceDTO.getPassword() : existingUser.getPassword());

        if (userServiceDTO.getRole() != null) {
            updatedUser.setRole(Role.valueOf(String.valueOf(userServiceDTO.getRole())));
        } else {
            updatedUser.setRole(existingUser.getRole());
        }

        userServiceDao.delete(existingUser);

        userServiceDao.save(updatedUser);

    }

    @Override
    public void deleteUser(String email) {
        Optional<UserServiceEntity> findId = userServiceDao.findById(email);
        if (!findId.isPresent()){
            throw new UserNotFoundException("User not Found");
        }else {
            userServiceDao.deleteById(email);
        }
    }

    @Override
    public UserResponse getSelectedUser(String email) {
        if (userServiceDao.existsById(email)) {
            UserServiceEntity userEntityByEmail = userServiceDao.getReferenceById(email);
            return mapping.convertUserEntityToDTO(userEntityByEmail);
        } else {
            return new UserErrorResponse(0, "User not Found");
        }
    }

    @Override
    public List<UserServiceDTO> getAllUsers() {
        List<UserServiceEntity> getAllUsers = userServiceDao.findAll();
        return mapping.convertTOUserDTOList(getAllUsers);
    }

    @Override
    public UserDetailsService userDetailsService() {
        return email ->
                userServiceDao.findByEmail(email)
                        .orElseThrow(()-> new UserNotFoundException("User Not found"));
    }
}
