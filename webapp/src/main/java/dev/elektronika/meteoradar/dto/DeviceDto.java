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
public class iDeviceDto extends ApiRequestDto{
    Long gpsX;
    Long gpsY;
    List<SensorDto> sensors;
}
