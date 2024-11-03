package lk.ijse.gdse68.CropMonitoringSystem.dao;

import lk.ijse.gdse68.CropMonitoringSystem.entity.CropEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CropDao extends JpaRepository<CropEntity, String> {
}
