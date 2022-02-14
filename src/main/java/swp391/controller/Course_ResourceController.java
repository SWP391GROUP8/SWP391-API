package swp391.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import swp391.dto.course_ResourceDto.ModifiCourse_ResourceDto;
import swp391.dto.course_ResourceDto.UpdateCourse_ResourceDto;

import swp391.entity.Course_Resource;
import swp391.service.CourseService;
import swp391.service.Course_ResourceService;

import java.util.List;

@RestController
@RequestMapping("/api/resource")
public class Course_ResourceController {
    private Course_ResourceService course_resourceService;
    private CourseService courseService;

    public Course_ResourceController(Course_ResourceService course_resourceService, CourseService courseService) {
        this.course_resourceService = course_resourceService;
        this.courseService = courseService;
    }

    @GetMapping
    private ResponseEntity getAll() {
        List<Course_Resource> course_resourceList = course_resourceService.getAll();
        return ResponseEntity.ok().body(course_resourceList);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody ModifiCourse_ResourceDto dto) {

        if (!courseService.isExisted(dto.getCourseId())) {
            return ResponseEntity.badRequest().body("Course Id is not found");
        }
        Course_Resource course_resource = course_resourceService.create(dto);

        return ResponseEntity.ok().body(course_resource);
    }

    @PutMapping
    private ResponseEntity update(@RequestBody UpdateCourse_ResourceDto dto) {
        if (!courseService.isExisted(dto.getCourseId())){
            return ResponseEntity.badRequest().body("Course Id is not found");
        }
        Course_Resource course_resource = course_resourceService.update(dto);
        return ResponseEntity.ok().body(course_resource);
    }

    @DeleteMapping()
    private ResponseEntity delete(@RequestParam Long id) {
        course_resourceService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get-by-id")
    private ResponseEntity getById(@RequestParam Long id) {
        Course_Resource course_resource = course_resourceService.getById(id);
        return ResponseEntity.ok().body(course_resource);
    }
}