package bham.tenant.service;

import bham.tenant.dto.ProjectDTO;
import bham.tenant.repo.SignupRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl {

    private final SignupRepo signupRepo;
    private final TenantService tenantService;

    public ProjectServiceImpl(SignupRepo signupRepo, TenantService tenantService) {
        this.signupRepo = signupRepo;
        this.tenantService = tenantService;
    }

    public ResponseEntity<String> addProject(String tenantId , ProjectDTO projectDTO){

        // Step 1: Validate tenant exists
        if (!signupRepo.existsByWebAddress(tenantId)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid tenant ID");
        }

        try {
            tenantService.insertProjectIntoTenant(tenantId, projectDTO);
            return ResponseEntity.ok("Project added for tenant: " + tenantId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }
}
