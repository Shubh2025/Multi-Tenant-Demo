package bham.tenant.controller;

import bham.tenant.dto.ProjectDTO;
import bham.tenant.repo.SignupRepo;
import bham.tenant.service.TenantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProjectController {

    private final SignupRepo signupRepo;
    private final TenantService tenantService;

    public ProjectController(SignupRepo signupRepo, TenantService tenantService) {
        this.signupRepo = signupRepo;
        this.tenantService = tenantService;
    }

    @PostMapping("/project")
    public ResponseEntity<String> addProject(@RequestHeader("X-Tenant-ID") String tenantId,
                                             @RequestBody ProjectDTO projectDTO) {
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

