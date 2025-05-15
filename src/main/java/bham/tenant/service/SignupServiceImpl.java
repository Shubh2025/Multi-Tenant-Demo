package bham.tenant.service;

import bham.tenant.dto.SignupDTO;
import bham.tenant.entity.Signup;
import bham.tenant.repo.SignupRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SignupServiceImpl  {

    private final SignupRepo signupRepo;
    private final TenantService tenantService;

    public SignupServiceImpl(SignupRepo signupRepo, TenantService tenantService) {
        this.signupRepo = signupRepo;
        this.tenantService = tenantService;
    }

    public ResponseEntity<String> isTenantExist(SignupDTO signupDTO){
        if (signupRepo.existsByWebAddress(signupDTO.getWebAddress())){
            ResponseEntity.status(HttpStatus.CONFLICT)
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
