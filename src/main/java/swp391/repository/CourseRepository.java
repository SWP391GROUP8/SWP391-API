package swp391.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swp391.entity.Course;



@Repository
public interface CourseRepository extends JpaRepository<Course,String> {
}
