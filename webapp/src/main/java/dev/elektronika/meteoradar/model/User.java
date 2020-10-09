package dev.elektronika.meteoradar.model;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
@ToString
@NoArgsConstructor
public class User extends BaseEntity{

    @Column(name = "age")
    private Integer age;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "description", length = 3000)
    private String description;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "gender")
    private String gender;

    @Column(name = "token", length = 501)
    private String token;

    @Column(name = "general_skill")
    private String general_skill;

    @Column(name = "education")
    private String education;

    @Column(name = "native_language")
    private String native_language;

    @Column(name = "link_img")
    private String link_img;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
    joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    @Fetch(value = FetchMode.SELECT)
    private Set<Role> roles;

    @Transient
    private  String roleFromForm;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
