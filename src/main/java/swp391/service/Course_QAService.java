package swp391.service;

import swp391.dto.course_qa.ModifiCourse_qa;
import swp391.dto.course_qa.UpdateCourse_qa;
import swp391.entity.Course_QA;

import java.util.List;

public interface Course_QAService {
    List<Course_QA> getAll();

    boolean isExisted(Long id);

    Course_QA create(ModifiCourse_qa dto);

    Course_QA update(Long id, UpdateCourse_qa dto);

    void delete(Long id);

    Course_QA getById(Long id);

    List<Course_QA> getByCourseId(String id);
}
