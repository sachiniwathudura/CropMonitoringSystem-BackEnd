package lk.ijse.gdse68.CropMonitoringSystem.service.Impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse68.CropMonitoringSystem.CustomObj.UserResponse;
import lk.ijse.gdse68.CropMonitoringSystem.dao.UserServiceDao;
import lk.ijse.gdse68.CropMonitoringSystem.dto.UserServiceDTO;
import lk.ijse.gdse68.CropMonitoringSystem.entity.UserServiceEntity;
import lk.ijse.gdse68.CropMonitoringSystem.exception.DataPersistFailedException;
import lk.ijse.gdse68.CropMonitoringSystem.service.UserService;
import lk.ijse.gdse68.CropMonitoringSystem.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional

public class UserServiceImpl implements UserService {
    @Autowired
    private UserServiceDao userServiceDao;
    @Autowired
    private Mapping mapping;
    @Override
    public void saveUser(UserServiceDTO userServiceDTO) {
        var userEntity = mapping.convertToUserEntity((List<UserServiceEntity>) userServiceDTO);
        var saveUser = userServiceDao.save(userEntity);
        if (saveUser==null){
            throw new DataPersistFailedException("can not save user");
        }
    }

    @Override
    public void updateUser(String email, UserServiceDTO userServiceDTO) {

    }

    @Override
    public void deleteUser(String email) {

    }

    @Override
    public UserResponse getSelectedUser(String email) {
        return null;
    }

    @Override
    public List<UserServiceDTO> getAllUsers() {
        return null;
    }
}
