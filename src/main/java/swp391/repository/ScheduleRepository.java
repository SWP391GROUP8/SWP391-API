package swp391.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import swp391.entity.Schedule;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule,String> {

    @Query("select s from Schedule s join s.course c where c.id=?1" )
    List<Schedule> getAllByCourse(String id);
}
