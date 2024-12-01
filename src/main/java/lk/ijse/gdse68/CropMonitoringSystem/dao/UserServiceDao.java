package lk.ijse.gdse68.CropMonitoringSystem.dao;

import lk.ijse.gdse68.CropMonitoringSystem.entity.UserServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserServiceDao extends JpaRepository<UserServiceEntity,String> {
    Optional<UserServiceEntity> findByEmail(String email);

}
