package swp391.service.impl;

import org.springframework.stereotype.Service;
import swp391.dto.course_ResourceDto.ModifiCourse_ResourceDto;
import swp391.dto.course_ResourceDto.UpdateCourse_ResourceDto;
import swp391.entity.Course_Resource;
import swp391.repository.CourseRepository;
import swp391.repository.Course_ResourceRepository;
import swp391.service.Course_ResourceService;

import java.util.List;

@Service
public class Course_ResourceServiceImpl implements Course_ResourceService {
    private Course_ResourceRepository course_resourceRepository;
    private CourseRepository courseRepository;

    public Course_ResourceServiceImpl(Course_ResourceRepository course_resourceRepository, CourseRepository courseRepository) {
        this.course_resourceRepository = course_resourceRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course_Resource> getAll() {
        return course_resourceRepository.findAll();
    }

    @Override
    public Course_Resource create(ModifiCourse_ResourceDto dto) {
        Course_Resource course_resource = new Course_Resource();
        course_resource.setContent(dto.getContent());
        course_resource.setCourse(courseRepository.getById(dto.getCourseId()));
        return course_resourceRepository.save(course_resource);
    }


    @Override
    public void delete(Long id) {
        course_resourceRepository.deleteById(id);
    }

    @Override
    public Course_Resource getById(Long id) {
        return course_resourceRepository.getById(id);
    }

    @Override
    public Course_Resource update(UpdateCourse_ResourceDto dto) {
        Course_Resource course_resource = course_resourceRepository.getById(dto.getId());
        course_resource.setContent(dto.getContent());
        course_resource.setCourse(courseRepository.getById(dto.getCourseId()));
        return course_resourceRepository.save(course_resource);
    }
}
