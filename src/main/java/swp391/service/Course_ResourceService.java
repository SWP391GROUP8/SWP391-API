package swp391.service;

import swp391.dto.course_ResourceDto.ModifiCourse_ResourceDto;
import swp391.dto.course_ResourceDto.UpdateCourse_ResourceDto;
import swp391.entity.Course_Resource;

import java.util.List;

public interface Course_ResourceService {
    List<Course_Resource> getAll();

    Course_Resource create(ModifiCourse_ResourceDto dto);

    void delete(Long id);

    Course_Resource getById(Long id);

    Course_Resource update(UpdateCourse_ResourceDto dto);
}
