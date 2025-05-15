package bham.tenant.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class SignupDTO {

    @NotBlank(message = "First Name is required")
    private String firstName;

    @NotBlank(message = "Last Name is required")
    private String lastName;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @Pattern(
            regexp = "^[0-9]{10}$",
            message = "Phone number must be 10 digits"
    )
    private String phoneNumber;

    @NotBlank(message = "Company Name is required")
    private String companyName;

    @NotBlank(message = "Please select Country")
    private String country;

    @NotNull(message = "Please Select Number Of Users")
    private Integer numberOfUsers;

    @NotBlank(message = "Web address is mandatory")
    private String webAddress;

}
