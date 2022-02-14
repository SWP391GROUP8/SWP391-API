package swp391.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swp391.entity.Course_QA;

@Repository
public interface Course_qaRepository extends JpaRepository<Course_QA,String> {
}
