package dev.elektronika.meteoradar.dto;

import dev.elektronika.meteoradar.model.Sensor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiDeviceInit extends ApiRequestDto{
    Long gpsX;
    Long gpsY;
    List<SensorDto> sensors;
}
