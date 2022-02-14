package swp391.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import swp391.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    User findByEmailAndStatus(String email,String status);

    @Query("SELECT COUNT(u.email) FROM User u join u.schedules s WHERE s.id = ?1 AND u.email =?2")
    int findUserByScheduleIdAndUserId(String scheduleId, String userId);
}
