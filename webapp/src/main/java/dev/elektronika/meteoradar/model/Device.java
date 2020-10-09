package dev.elektronika.meteoradar.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.Session;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(name = "device")
@ToString
@NoArgsConstructor
@Builder
public class Device extends BaseEntity{
    String token;
    String name;
    @ManyToOne
    User owner;
    @OneToMany
    List<Sensor> sensors;
}
