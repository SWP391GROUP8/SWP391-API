package swp391.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swp391.entity.Course_Resource;

import java.util.List;

@Repository
public interface Course_ResourceRepository extends JpaRepository<Course_Resource,Long> {


    List<Course_Resource> getCourse_ResourcesByCourse_Id(String id);
}
