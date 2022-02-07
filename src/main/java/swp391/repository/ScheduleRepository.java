package swp391.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swp391.entity.Schedule;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule,String> {
}
