package bham.tenant.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
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

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String companyName;
    private String country;
    private Integer numberOfUsers;
    @Column(name = "tenant_id")
    private String webAddress;
    @Column(name = "webAdressUrl")
    private String webAdressUrl;

}
