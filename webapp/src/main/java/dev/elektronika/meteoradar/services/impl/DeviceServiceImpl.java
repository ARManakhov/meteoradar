package dev.elektronika.meteoradar.services.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import dev.elektronika.meteoradar.model.Device;
import dev.elektronika.meteoradar.model.User;
import dev.elektronika.meteoradar.repository.DeviceRepository;
import dev.elektronika.meteoradar.services.DeviceService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class DeviceServiceImpl implements DeviceService {
    @Value("${security.jwt.token.secret-key:secret}")
    private String secretKey = "secret";

    DeviceRepository deviceRepository;
    Algorithm algorithm = Algorithm.HMAC256(secretKey);

    public DeviceServiceImpl(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Override
    public Device newDevice(User owner, String name) {
        Device device = Device.builder().name(name).owner(owner).build();
        saveDevice(device);
        generateToken(device);
        saveDevice(device);
        return device;
    }

    private void generateToken(Device device){ //todo impl in separated method due security issues
        Date now = new Date();
        String token = JWT.create()
                .withClaim("deviceId",device.getId())
                .withClaim("ownerId",device.getOwner().getId())
                .withIssuedAt(now).sign(algorithm);
        device.setToken(token);
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
