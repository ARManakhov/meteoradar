package dev.elektronika.meteoradar.model;

import lombok.*;
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
@AllArgsConstructor
@Builder
public class Device extends BaseEntity{
    String token;
    String name;
    @ManyToOne
    User owner;
    @OneToMany(mappedBy = "device")
    List<Sensor> sensors;
    String description;
    Float gpsX;
    Float gpsY;

}
