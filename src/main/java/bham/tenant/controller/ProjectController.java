package bham.tenant.controller;

import bham.tenant.dto.ProjectDTO;
import bham.tenant.service.ProjectServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProjectController {

    private final ProjectServiceImpl projectService;
    public ProjectController(ProjectServiceImpl projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/project")
    public ResponseEntity<String> addProject(@RequestHeader("X-Tenant-ID") String tenantId,
                                            @Valid @RequestBody ProjectDTO projectDTO) {
        if (tenantId.isEmpty()){
            ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Tenant Id should not be empty");
        }
        ResponseEntity<String> response = projectService.addProject(tenantId, projectDTO);
        return response;
    }
}

