package bham.tenant.service;

import bham.tenant.dto.SignupDTO;
import bham.tenant.entity.Signup;
import bham.tenant.repo.SignupRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SignupServiceImpl  {

    private final SignupRepo signupRepo;
    private final TenantService tenantService;

    @Value(value = "${webaddress.extension.url}")
    private String extension;

    public SignupServiceImpl(SignupRepo signupRepo, TenantService tenantService) {
        this.signupRepo = signupRepo;
        this.tenantService = tenantService;
    }

    public ResponseEntity<String> createTenant(SignupDTO signupDTO){
        if (signupRepo.existsByWebAddress(signupDTO.getWebAddress())){
            ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Tenant Id already exist");
        }

        Signup signup = new Signup();
        signup.setFirstName(signupDTO.getFirstName());
        signup.setLastName(signupDTO.getLastName());
        signup.setEmail(signupDTO.getEmail());
        signup.setPhoneNumber(signupDTO.getPhoneNumber());
        signup.setCompanyName(signupDTO.getCompanyName());
        signup.setCountry(signupDTO.getCountry());
        signup.setWebAddress(signupDTO.getWebAddress());
        signup.setWebAdressUrl(signupDTO.getWebAddress()+extension);
        signupRepo.save(signup);

        tenantService.createTenantDatabase(signupDTO.getWebAddress());
        return ResponseEntity.ok("Tenant Created Successfully");
    }
}
