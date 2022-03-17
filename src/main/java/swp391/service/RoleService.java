package swp391.service;

import swp391.dto.role.CreateRoleDto;
import swp391.entity.Role;

import java.util.List;

public interface RoleService {
    Role createRole(CreateRoleDto dto);

    boolean isExisted(String roleId);

    List<Role> getAll();

    Role update(CreateRoleDto dto);

    void delete(String id);

    Role getById(String id);
}
