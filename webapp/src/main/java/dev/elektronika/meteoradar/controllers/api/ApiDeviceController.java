package dev.elektronika.meteoradar.controllers.api;

import dev.elektronika.meteoradar.dto.ApiDeviceInit;
import dev.elektronika.meteoradar.dto.ApiResponseDto;
import dev.elektronika.meteoradar.dto.ApiSensorUpdate;
import dev.elektronika.meteoradar.dto.SensorDto;
import dev.elektronika.meteoradar.model.Device;
import dev.elektronika.meteoradar.model.Sensor;
import dev.elektronika.meteoradar.services.DeviceService;
import dev.elektronika.meteoradar.services.SensorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ApiDeviceController {
    DeviceService deviceService;
    SensorService sensorService;

    public ApiDeviceController(DeviceService deviceService, SensorService sensorService) {
        this.deviceService = deviceService;
        this.sensorService = sensorService;
    }

    @PostMapping("/api/device/init")
    ApiResponseDto<Object> init(ApiDeviceInit clientDeviceInit) {
        Optional<Device> deviceByToken = deviceService.getDeviceByToken(clientDeviceInit.getToken());
        if (deviceByToken.isPresent()){
            Device dbDevice = deviceByToken.get();
            for (SensorDto clientSensor : clientDeviceInit.getSensors()){
                boolean inDb = false;
                for (Sensor sensor : dbDevice.getSensors()){
                    if (sensor.getInnerId().equals(clientSensor.getInnerId())){
                        inDb = true;
                        break;
                    }
                }
                if (!inDb){
                    dbDevice.getSensors().add(clientSensor.toSensor());
                }
            }
            if (clientDeviceInit.getGpsX() != null){
             //todo add coordinates saving
            }
            if (clientDeviceInit.getGpsX() != null){

            }
            deviceService.saveDevice(dbDevice);
            return new ApiResponseDto<>().builder().status("ok").build();
        }
        else return new ApiResponseDto<>().builder().status("err").build();
    }

    @PostMapping("/api/device/update")
    ApiResponseDto<Object> update(ApiSensorUpdate sensorInfo) {
        Optional<Device> deviceByToken = deviceService.getDeviceByToken(sensorInfo.getToken());
        if (deviceByToken.isPresent()){
            Optional<Sensor> byDeviceIdAndInnerId = sensorService.getByDeviceIdAndInnerId(deviceByToken.get().getId(), sensorInfo.getSensor().getInnerId());
            if (byDeviceIdAndInnerId.isPresent()){
                Sensor sensor = byDeviceIdAndInnerId.get();
                sensor.setValue(sensorInfo.getSensor().getValue());
                sensorService.add(sensor);
                return new ApiResponseDto<>().builder().status("ok").build();
            }
        }
        return new ApiResponseDto<>().builder().status("err").build();
    }
}
