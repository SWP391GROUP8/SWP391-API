package swp391.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swp391.dto.role.CreateRoleDto;
import swp391.entity.Role;

import swp391.service.RoleService;

@RestController
@RequestMapping("/api/role")
public class RoleController {
    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity createRole(@RequestBody CreateRoleDto dto) {
        if(roleService.isExisted(dto.getId())){
            return ResponseEntity.badRequest().body("Role Id is duplicated");
        }
        Role role = roleService.createRole(dto);
        return ResponseEntity.ok().body(role);
    }
}
