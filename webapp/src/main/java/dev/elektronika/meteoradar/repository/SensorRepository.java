package dev.elektronika.meteoradar.repository;

import dev.elektronika.meteoradar.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SensorRepository extends JpaRepository<Sensor, Long> {
    Optional<Sensor> findByDeviceAndInnerId(Long deviceId, Long innerId);
}
