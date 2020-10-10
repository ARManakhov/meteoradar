package dev.elektronika.meteoradar.model;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User extends BaseEntity{

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "description", length = 3000)
    private String description;

    @Column(name = "token", length = 501)
    private String token;

    @OneToMany(mappedBy = "owner")
    List<Device> devices;

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
