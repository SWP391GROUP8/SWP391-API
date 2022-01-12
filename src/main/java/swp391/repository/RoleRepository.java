package swp391.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swp391.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,String> {
}
