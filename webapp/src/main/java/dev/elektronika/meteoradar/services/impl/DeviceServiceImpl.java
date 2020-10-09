package dev.elektronika.meteoradar.services.impl;

import dev.elektronika.meteoradar.model.Device;
import dev.elektronika.meteoradar.model.User;
import dev.elektronika.meteoradar.repository.DeviceRepository;
import dev.elektronika.meteoradar.services.DeviceService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeviceServiceImpl implements DeviceService {

    DeviceRepository deviceRepository;

    public DeviceServiceImpl(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Override
    public Device newDevice(User owner) {
        return null;
    }

    @Override
    public void deleteDevice(Device device) {
        deviceRepository.delete(device);
    }

    @Override
    public void saveDevice(Device device) {
        deviceRepository.save(device);
    }

    @Override
    public Optional<Device> getDevice(Long id) {
        return deviceRepository.findById(id);
    }
}
