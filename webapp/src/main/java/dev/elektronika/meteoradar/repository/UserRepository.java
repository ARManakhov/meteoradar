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
    public void updateStatusByToken(String token, State status) ;

    @Transactional
    @Modifying
    @Query("update User " +
            "set age = :age, " +
            "firstName = :firstName," +
            "lastName = :lastName," +
            "description = :description," +
            "country = :country," +
            "city = :city," +
            "gender = :gender," +
            "general_skill = :generalSkill," +
            "education = :education," +
            "native_language = :nativeLanguage," +
            "link_img = :linkImg," +
            "updated = CURRENT_TIMESTAMP " +
            "where email = :email")
    public void updateByEmail(String email, Integer age, String firstName, String lastName,
                           String description, String country, String city, String gender,
                           String generalSkill, String education, String nativeLanguage,
                           String linkImg);


}
