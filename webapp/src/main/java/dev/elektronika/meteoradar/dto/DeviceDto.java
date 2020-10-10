package dev.elektronika.meteoradar.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeviceDto{
    Long id;
    Float gpsX;
    Float gpsY;
    List<SensorDto> sensors;
}
