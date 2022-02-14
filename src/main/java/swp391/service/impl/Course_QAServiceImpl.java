package swp391.service.impl;

import org.springframework.stereotype.Service;

import swp391.dto.course_qa.ModifiCourse_qa;
import swp391.entity.Course_QA;
import swp391.repository.CourseRepository;
import swp391.repository.Course_qaRepository;
import swp391.service.Course_QAService;

import java.util.List;

@Service
public class Course_QAServiceImpl implements Course_QAService {
    private Course_qaRepository course_qaRepository;
    private CourseRepository courseRepository;
    public Course_QAServiceImpl(Course_qaRepository course_qaRepository,CourseRepository courseRepository){
        this.course_qaRepository=course_qaRepository;
        this.courseRepository=courseRepository;
    }
    @Override
    public List<Course_QA> getAll() {
        return course_qaRepository.findAll();
    }

    @Override
    public boolean isExisted(String id) {
        return course_qaRepository.existsById(id);
    }

    @Override
    public Course_QA create(ModifiCourse_qa dto) {
        Course_QA course_qa = new Course_QA();
        course_qa.setId(dto.getId());
        course_qa.setTitle(dto.getTitle());
        course_qa.setContent(dto.getContent());
        course_qa.setCourse(courseRepository.getById(dto.getCourseId()));
        return course_qaRepository.save(course_qa);
    }

    @Override
    public Course_QA update(String id, ModifiCourse_qa dto) {
        Course_QA course_qa = course_qaRepository.getById(id);

        course_qa.setTitle(dto.getTitle());
        course_qa.setContent(dto.getContent());
        course_qa.setCourse(courseRepository.getById(dto.getCourseId()));
        return course_qaRepository.save(course_qa);

    }

    @Override
    public void delete(String id) {
        course_qaRepository.deleteById(id);
    }

    @Override
    public Course_QA getById(String id) {
        return course_qaRepository.getById(id);
    }
}
