package dev.elektronika.meteoradar.controllers;

import dev.elektronika.meteoradar.model.Device;
import dev.elektronika.meteoradar.model.User;
import dev.elektronika.meteoradar.security.details.UserDetailsImpl;
import dev.elektronika.meteoradar.services.DeviceService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;
import java.util.Optional;

@Controller
public class DeviceController {

    DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @PostMapping("/device/")
    String newDevice(Authentication authentication, String name) {
        User clientUser = ((UserDetailsImpl) authentication.getPrincipal()).getUser();
        if (clientUser != null) {
            Device device = deviceService.newDevice(clientUser,name);
            return "redirect:/device/" + device.getId();
        }
        return "redirect:signIn";
    }

    @GetMapping("/device/{id}")
    String getDevice(@PathVariable Long id, Map<String, Object> model, Authentication authentication) {
        Optional<Device> deviceOptional = deviceService.getDevice(id);
        if (deviceOptional.isPresent()) {
            User clientUser = ((UserDetailsImpl) authentication.getPrincipal()).getUser();
            model.put("client_user", clientUser);
            model.put("device", deviceOptional.get());
            return "device";
        } else return "redirect:404";
    }

    @DeleteMapping("/device/{id}")
    String deleteDevice(@PathVariable Long id, Authentication authentication) {
        User clientUser = ((UserDetailsImpl) authentication.getPrincipal()).getUser();
        Optional<Device> device = deviceService.getDevice(id);
        if (clientUser != null && device.isPresent() && clientUser.getId().equals(device.get().getId())) {
            deviceService.deleteDevice(device.get());
        }
        return "redirect:/profile/" + clientUser.getId();
    }

    @PostMapping("/device-description/{id}")
    String updateDetails(Long id, String text, Authentication authentication) {
        User clientUser = ((UserDetailsImpl) authentication.getPrincipal()).getUser();
        Optional<Device> deviceOptional = deviceService.getDevice(id);
        if (deviceOptional.isPresent()) {
            Device device = deviceOptional.get();
            if (clientUser != null && device.getId().equals(clientUser.getId())) {
                device.setDescription(text);
                deviceService.saveDevice(device);
                return "redirect:/device/" + device.getId();
            }
        }
        return "redirect:404";
    }
}
