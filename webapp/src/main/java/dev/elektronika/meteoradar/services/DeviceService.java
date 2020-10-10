package dev.elektronika.meteoradar.services;

import dev.elektronika.meteoradar.model.Device;
import dev.elektronika.meteoradar.model.User;

import java.util.Optional;

public interface DeviceService {
    Optional<Device> getDeviceByToken(String token);

    Device newDevice(User owner, String name);

    void deleteDevice(Device device);

    void saveDevice(Device device);

    Optional<Device> getDevice(Long id);
}
