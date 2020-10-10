package dev.elektronika.meteoradar.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.HashMap;

@Data
@Entity
@Table(name = "Sensor")
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Sensor extends BaseEntity {
    String name;
    Float value;
    String unit;
    SensorType sensorType;
    Long innerId;
    @ManyToOne
    Device device;
}
