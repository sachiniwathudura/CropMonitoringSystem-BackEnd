package lk.ijse.gdse68.CropMonitoringSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "CropDetails")
public class MonitoringLogEntity implements SuperEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String logCode;
    private Date logDate;
    private String observationDetails;

    @ManyToOne
    @JoinColumn(name = "fieldCode")
    private FieldEntity field;

    @ManyToOne
    @JoinColumn(name = "cropCode")
    private CropEntity crop;

    @ManyToOne
    @JoinColumn(name = "staffId")
    private StaffEntity staff;
}
