package dev.elektronika.meteoradar.repository;

import dev.elektronika.meteoradar.model.State;
import dev.elektronika.meteoradar.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByToken(String token);
    Optional<User> findById(Long id);

    @Transactional
    @Modifying
    @Query("update User set status = :status, updated = CURRENT_TIMESTAMP where token = :token")
    public void updateStatusByToken(String token, State status);
}
