package dev.elektronika.meteoradar.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"users"})
public class Role extends BaseEntity implements GrantedAuthority {

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<User> users;

    @Override
    public String getAuthority() {
        return name;
    }
}