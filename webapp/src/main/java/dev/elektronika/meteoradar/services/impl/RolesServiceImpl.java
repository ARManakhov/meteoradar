package dev.elektronika.meteoradar.services.impl;

import dev.elektronika.meteoradar.model.Role;
import dev.elektronika.meteoradar.repository.RoleRepository;
import dev.elektronika.meteoradar.services.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RolesServiceImpl implements RolesService {

    private final RoleRepository roleRepository;

    @Autowired
    public RolesServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Optional<Role> findByName(String name) {
        return roleRepository.findByName(name);
    }
}
