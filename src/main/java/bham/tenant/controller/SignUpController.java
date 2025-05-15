package bham.tenant.controller;

import bham.tenant.dto.SignupDTO;
import bham.tenant.entity.Signup;
import bham.tenant.repo.SignupRepo;
import bham.tenant.service.SignupServiceImpl;
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

    private final SignupServiceImpl signupService;

    public SignUpController(SignupServiceImpl signupService) {
        this.signupService = signupService;
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<String> signupWithUser(
            @Valid @RequestBody SignupDTO signupDTO
            ){
        ResponseEntity<String> result = signupService.isTenantExist(signupDTO);
        return result;
    }
}
