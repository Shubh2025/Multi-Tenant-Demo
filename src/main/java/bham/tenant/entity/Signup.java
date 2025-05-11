package bham.tenant.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "signup")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Signup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;
    @Column(name = "tenant_id")
    private String webAddress;

}
