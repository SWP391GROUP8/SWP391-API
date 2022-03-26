package swp391.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import swp391.entity.Course_QA;

import java.util.List;
import java.util.Optional;

@Repository
public interface Course_qaRepository extends JpaRepository<Course_QA, Long> {

    @Query("select c from Course_QA c where c.id=?1")
    Course_QA findCourse_QAById(Long id);

    List<Course_QA> getAllByCourse_Id(String id);
}
