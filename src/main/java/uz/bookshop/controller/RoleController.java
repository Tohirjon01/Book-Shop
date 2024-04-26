package uz.bookshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.bookshop.dto.request.RoleRequestDTO;
import uz.bookshop.dto.response.RoleResponseDTO;
import uz.bookshop.repository.RoleRepository;
import uz.bookshop.service.RoleService;

@RestController
@RequestMapping("/api")
public class RoleController {
    private final RoleService roleService;
    private final RoleRepository roleRepository;

    public RoleController(RoleService roleService, RoleRepository roleRepository) {
        this.roleService = roleService;
        this.roleRepository = roleRepository;
    }

    @PostMapping("/role")
    public ResponseEntity<RoleResponseDTO> createRole(
            @RequestBody RoleRequestDTO roleRequestDTO) {
        return ResponseEntity.ok(roleService.createRole(roleRequestDTO));
    }

    @PutMapping("/role")
    public ResponseEntity<RoleResponseDTO> updateRole(@RequestBody RoleRequestDTO roleRequestDTO,
                                                      @RequestParam("roleId") Long roleId) {
        return ResponseEntity.ok(roleService.updateRole(roleRequestDTO, roleId));
    }
    @DeleteMapping("/role/{id}")
    public ResponseEntity deleteRole(@PathVariable Long id) {
        try {
            roleService.deleteRole(id);
            String message = "Role deleted successfully";
            return ResponseEntity.ok().body(message);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete role");
        }
    }
    @GetMapping("/role/getAll")
    public ResponseEntity<?> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRole());
    }
}
