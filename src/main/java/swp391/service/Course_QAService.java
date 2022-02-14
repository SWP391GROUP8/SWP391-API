package swp391.service;

import swp391.dto.blog.ModifiBlogDto;
import swp391.dto.course_qa.ModifiCourse_qa;
import swp391.entity.Course_QA;

import java.util.List;

public interface Course_QAService {
    List<Course_QA> getAll();

    boolean isExisted(String id);

    Course_QA create(ModifiCourse_qa dto);

    Course_QA update(String id, ModifiCourse_qa dto);

    void delete(String id);

    Course_QA getById(String id);
}
