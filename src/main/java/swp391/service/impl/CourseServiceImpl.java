package swp391.service.impl;

import org.springframework.stereotype.Service;
import swp391.dto.course.CreateCourseDto;
import swp391.entity.Course;
import swp391.repository.CourseRepository;
import swp391.service.CourseService;

import java.util.List;
import java.util.Set;

@Service
public class CourseServiceImpl implements CourseService {
    private CourseRepository courseRepository;


    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course create(CreateCourseDto dto) {
        Course course = new Course();
        course.setId(dto.getId());
        course.setAuthor(dto.getAuthor());
        course.setName(dto.getName());
        course.setCode(dto.getCode());
        course.setStatus(dto.getStatus());
        return courseRepository.save(course);
    }

    @Override
    public boolean isExisted(String id) {
        return courseRepository.existsById(id);
    }

    @Override
    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course update(String id, CreateCourseDto dto) {
        Course course = courseRepository.getById(dto.getId());
        course.setAuthor(dto.getAuthor());
        course.setName(dto.getName());
        course.setCode(dto.getCode());
        course.setStatus(dto.getStatus());
        return courseRepository.save(course);

    }

    @Override
    public void delete(String id) {
        courseRepository.deleteById(id);
    }

    @Override
    public Course getById(String id) {
        return courseRepository.getById(id);
    }

    @Override
    public void addPreCourse(String courseId, String preCourseId) {
        Course course = courseRepository.getById(courseId);
        Course preCourse=courseRepository.getById(preCourseId);
        course.addPreCourse(preCourse);
        courseRepository.save(course);
    }

    @Override
    public Set<Course> getPreCourse(String courseId) {
        return courseRepository.getById(courseId).getCourses();

    }


}
