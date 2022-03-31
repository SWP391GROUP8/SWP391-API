package swp391.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import swp391.dto.role.CreateRoleDto;
import swp391.dto.schedule.CreateScheduleDto;
import swp391.entity.Role;

import swp391.entity.Schedule;
import swp391.service.RoleService;

import java.util.List;

@RestController
@RequestMapping("/api/role")
@CrossOrigin(origins = "*", allowedHeaders = "*")
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
    @GetMapping
    private ResponseEntity getAll() {
        List<Role> roleList = roleService.getAll();
        return ResponseEntity.ok().body(roleList);
    }

    @PutMapping
    private ResponseEntity update(@RequestBody CreateRoleDto dto) {
        Role role = roleService.update(dto);
        return ResponseEntity.ok().body(role);
    }

    @DeleteMapping()
    private ResponseEntity delete(@RequestParam String id) {
        roleService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get-by-id")
    private ResponseEntity getById(@RequestParam String id) {
        Role role = roleService.getById(id);
        return ResponseEntity.ok().body(role);
    }
}
