package swp391.service;

import swp391.dto.course.CreateCourseDto;
import swp391.entity.Course;

import java.util.List;
import java.util.Set;

public interface CourseService {
    Course create(CreateCourseDto dto);

    boolean isExisted(String id);

    List<Course> getAll();

    Course update(String id, CreateCourseDto dto);

    void delete(String id);

    Course getById(String id);


    void addPreCourse(String courseId, String preCourseId);

    Set<Course> getPreCourse(String courseId);
}
