package dev.elektronika.meteoradar.dto;

import dev.elektronika.meteoradar.model.Sensor;
import dev.elektronika.meteoradar.model.SensorType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SensorDto {
    Long id;
    Long innerId;
    String name;
    Float value;
    String unit;
    SensorType sensorType;

    public Sensor toSensor() {
        Sensor sensor = Sensor.builder()
                .innerId(innerId)
                .sensorType(sensorType)
                .name(name)
                .unit(unit)
                .value(value)
                .build();
        sensor.setId(id);
        return sensor;
    }
}
