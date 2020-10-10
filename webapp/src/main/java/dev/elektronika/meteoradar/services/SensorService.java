package dev.elektronika.meteoradar.services;

import dev.elektronika.meteoradar.model.Sensor;

import java.util.Optional;

public interface SensorService {
    Optional<Sensor> getByDeviceIdAndInnerId(Long deviceId, Long sensorId);
    void add(Sensor sensor);
}
