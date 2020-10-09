package dev.elektronika.meteoradar.services;

import dev.elektronika.meteoradar.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    void register(User user);

    void registerByAdmin(User user);

    boolean confirm(String token);

    List<User> findAll();

    Optional<User> find(String email);

    void delete(User user);

    void add(User user);

    void updateStatus(User user);


    Optional<User> findByToken(String token);
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
}
