package swp391.service;

import swp391.dto.role.CreateRoleDto;
import swp391.entity.Role;

public interface RoleService {
    Role createRole(CreateRoleDto dto);

    boolean isExisted(String roleId);
}
