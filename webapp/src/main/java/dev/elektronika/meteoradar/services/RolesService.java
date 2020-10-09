package dev.elektronika.meteoradar.services;

import dev.elektronika.meteoradar.model.Role;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface RolesService {
    Role save(Role role);
    Optional<Role> findByName(String name);
}
