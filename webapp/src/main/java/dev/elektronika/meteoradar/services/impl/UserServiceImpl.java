package dev.elektronika.meteoradar.services.impl;

import dev.elektronika.meteoradar.model.Role;
import dev.elektronika.meteoradar.model.State;
import dev.elektronika.meteoradar.model.User;
import dev.elektronika.meteoradar.repository.UserRepository;
import dev.elektronika.meteoradar.services.RolesService;
import dev.elektronika.meteoradar.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RestTemplate restTemplate;
    private final RolesService rolesService;
    private final AuthenticationManager authenticationManager;


    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository, RestTemplate restTemplate, RolesService rolesService, AuthenticationManager authenticationManager) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
        this.rolesService = rolesService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void register(User user) {
        registrationProcess(user, "ROLE_USER", "NOT_ACTIVE");
        User registeredUser = userRepository.save(user);
        log.info("IN register - user: {} successfully registered", registeredUser);
    }

    @Override
    public void registerByAdmin(User user) {
        //password,name,email
        registrationProcess(user, user.getRoleFromForm(), "ACTIVE");
        User registeredUser = userRepository.save(user);
        log.info("IN register - user: {} successfully registered by admin", registeredUser);
    }

    private static String generateNewToken() {
        int length = 500;
        Random r = new Random();
        return r.ints(48, 122)
                .filter(i -> (i < 57 || i > 65) && (i < 90 || i > 97))
                .mapToObj(i -> (char) i)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }

    @Override
    @Transactional
    public boolean confirm(String token) {
        Optional<User> optionalUser = findByToken(token);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setStatus(State.ACTIVE);
            updateStatus(user);
            log.info("A user with this mail has confirmed it: " + user.getEmail());
            return true;
        } else {
            log.info("User attempt to confirm mail failed: " + token);
            return false;
        }
    }

    void registrationProcess(User user, String role, String status){
        try {
            Optional<Role> roleUser = rolesService.findByName(role);
            if (roleUser.isPresent()) {
                Set<Role> userRoles = new HashSet<>();
                userRoles.add(roleUser.get());
                user.setRoles(userRoles);
            }
            String hashPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(hashPassword);
            user.setStatus(State.valueOf(status));
            user.setToken(generateNewToken());
            user.setCreated(new Date());
            user.setUpdated(new Date());
        } catch (IllegalArgumentException e){
            log.warn("unnable to create user : " + e.getMessage());
        }
    }
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> find(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
        log.info("{} was deleted", user);
    }

    @Override
    public void add(User user) {
        userRepository.save(user);
        log.info("user: {} was added to database", user );
    }

    @Override
    public void updateStatus(User user) {
        userRepository.updateStatusByToken(user.getToken(), State.ACTIVE);
    }

    @Override
    public Optional<User> findByToken(String token) {
        return userRepository.findByToken(token);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
