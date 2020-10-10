package dev.elektronika.meteoradar.services.impl;

import dev.elektronika.meteoradar.model.Sensor;
import dev.elektronika.meteoradar.repository.SensorRepository;
import dev.elektronika.meteoradar.services.SensorService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SensorServiceImpl implements SensorService {
    SensorRepository sensorRepository;

    public SensorServiceImpl(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Override
    public Optional<Sensor> getByDeviceIdAndInnerId(Long deviceId, Long innerId) {
        return sensorRepository.findByDeviceAndInnerId(deviceId, innerId);
    }

    @Override
    public void add(Sensor sensor) {
        sensorRepository.save(sensor);
    }
}
