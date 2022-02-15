package swp391.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import swp391.entity.Role;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    @Query("SELECT r from Role r where r.id =?1")
    Role findByRoleId(String id);


    @Query("SELECT r.name from Role r join r.users u  where u.email=?1")
    String getRoleByEmail(String email);
}
