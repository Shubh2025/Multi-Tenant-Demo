package bham.tenant.controller;

import bham.tenant.dto.SignupDTO;
import bham.tenant.entity.Signup;
import bham.tenant.repo.SignupRepo;
import bham.tenant.service.TenantService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Validated
public class SignUpController {

    private final SignupRepo signupRepo;
    private final TenantService tenantService;

    public SignUpController(SignupRepo signupRepo, TenantService tenantService) {
        this.signupRepo = signupRepo;
        this.tenantService = tenantService;
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<String> signupWithUser(
            @Valid @RequestBody SignupDTO signupDTO
            ){
        if (signupRepo.existsByWebAddress(signupDTO.getWebAddress())){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Tenant Id already exist");
        }

        Signup signup = new Signup();
        signup.setName(signupDTO.getName());
        signup.setEmail(signupDTO.getEmail());
        signup.setPassword(signupDTO.getPassword());
        signup.setWebAddress(signupDTO.getWebAddress());

        signupRepo.save(signup);
        tenantService.createTenantDatabase(signupDTO.getWebAddress());

    return ResponseEntity.ok("Tenant Created Successfully");
    }
}
