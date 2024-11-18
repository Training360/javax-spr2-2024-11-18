package empapp.entity;

import lombok.Data;

import jakarta.persistence.*;
import org.hibernate.envers.Audited;

@Entity
@Data
@Audited
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;

    @ManyToOne
    private Employee employee;

}
