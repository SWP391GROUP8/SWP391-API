package swp391.service.impl;

import org.springframework.stereotype.Service;
import swp391.dto.role.CreateRoleDto;
import swp391.entity.Role;
import swp391.repository.RoleRepository;
import swp391.service.RoleService;

import java.util.List;

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

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role update(CreateRoleDto dto) {
        Role role = roleRepository.getById(dto.getId());
        role.setName(dto.getName());
        return roleRepository.save(role);
    }

    @Override
    public void delete(String id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Role getById(String id) {
        return roleRepository.getById(id);
    }
}
