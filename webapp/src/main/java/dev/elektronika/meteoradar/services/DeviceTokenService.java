package dev.elektronika.meteoradar.services.impl;

import dev.elektronika.meteoradar.model.Device;

public interface DeviceTokenService {
    void generateToken(Device device);

    String getDeviceId(String token);
}
