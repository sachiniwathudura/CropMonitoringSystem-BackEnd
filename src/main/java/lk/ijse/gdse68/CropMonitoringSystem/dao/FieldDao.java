package lk.ijse.gdse68.CropMonitoringSystem.dao;

import lk.ijse.gdse68.CropMonitoringSystem.entity.FieldEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FieldDao extends JpaRepository<FieldEntity,String> {
}
