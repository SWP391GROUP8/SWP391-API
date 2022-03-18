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

    @Query("SELECT bl.isReaction FROM User u join u.userBlogList bl join bl.blog b WHERE u.email = ?1 AND b.id =?2")
    Boolean getReaction(String email,Long blogId);

    @Query("SELECT u FROM User u  WHERE u.email=?1")
    User getUserById(String id);
}
