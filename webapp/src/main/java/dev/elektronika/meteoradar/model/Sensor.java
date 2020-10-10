package dev.elektronika.meteoradar.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Value;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.HashMap;

@Data
@Entity
@Table(name = "Sensor")
@ToString
@NoArgsConstructor
public class Sensor extends BaseEntity {
    String name;
    Float value;
    String unit;
    @ManyToOne
    Device device;
}
