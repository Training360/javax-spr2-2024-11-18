package empapp.entity;

import lombok.Data;

import jakarta.persistence.*;
import org.hibernate.envers.Audited;

import java.util.List;

@Entity
@Data
@Audited
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Address> addresses;

}
