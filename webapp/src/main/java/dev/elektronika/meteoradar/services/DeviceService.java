package dev.elektronika.meteoradar.services;

import dev.elektronika.meteoradar.model.Device;
import dev.elektronika.meteoradar.model.User;

import java.util.Optional;

public interface DeviceService {
    Device newDevice(User owner);
    void  deleteDevice(Device device);
    void saveDevice(Device device);
    Optional<Device> getDevice(Long id);
}
