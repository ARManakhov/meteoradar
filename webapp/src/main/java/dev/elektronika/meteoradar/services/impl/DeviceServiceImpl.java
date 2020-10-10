package dev.elektronika.meteoradar.services.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import dev.elektronika.meteoradar.model.Device;
import dev.elektronika.meteoradar.model.User;
import dev.elektronika.meteoradar.repository.DeviceRepository;
import dev.elektronika.meteoradar.services.DeviceService;
import dev.elektronika.meteoradar.services.DeviceTokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.token.TokenService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class DeviceServiceImpl implements DeviceService {

    DeviceRepository deviceRepository;
    DeviceTokenService deviceTokenService;

    public DeviceServiceImpl(DeviceRepository deviceRepository, DeviceTokenService deviceTokenService) {
        this.deviceRepository = deviceRepository;
        this.deviceTokenService = deviceTokenService;
    }

    @Override
    public Device newDevice(User owner, String name) {
        Device device = Device.builder().name(name).owner(owner).build();
        saveDevice(device);
        deviceTokenService.generateToken(device);
        saveDevice(device);
        return device;
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

    @Override
    public Optional<Device> getDeviceByToken(String token) {
        return deviceRepository.findById(Long.valueOf(deviceTokenService.getDeviceId(token)));
    }

}
