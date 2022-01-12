package swp391.service.impl;

import org.springframework.stereotype.Service;
import swp391.dto.role.CreateRoleDto;
import swp391.entity.Role;
import swp391.repository.RoleRepository;
import swp391.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role createRole(CreateRoleDto dto) {
        Role role = new Role();
        role.setId(dto.getId());
        role.setName(dto.getName());
        return roleRepository.save(role);
    }

    @Override
    public boolean isExisted(String roleId) {
        return roleRepository.existsById(roleId);
    }
}
