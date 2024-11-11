package lk.ijse.gdse68.CropMonitoringSystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lk.ijse.gdse68.CropMonitoringSystem.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "User")
public class UserServiceEntity implements SuperEntity{
    @Id
    private String email;
    private String password;
    private Role role;
}
