package swp391.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import swp391.dto.course.AddFileDto;
import swp391.dto.course.CreateCourseDto;

import swp391.entity.Course;
import swp391.service.CourseService;
import swp391.service.FileService;

import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseController {
    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody CreateCourseDto dto) {
        if (courseService.isExisted(dto.getId())) {
            return ResponseEntity.badRequest().body("Course Id is duplicated");
        }
        Course course = courseService.create(dto);

        return ResponseEntity.ok().body(course);
    }

    @GetMapping
    private ResponseEntity getAll() {
        List<Course> courseList = courseService.getAll();
        return ResponseEntity.ok().body(courseList);
    }

    @PutMapping
    private ResponseEntity update(@RequestBody CreateCourseDto dto) {
        Course course = courseService.update(dto.getId(), dto);
        return ResponseEntity.ok().body(course);
    }

    @DeleteMapping()
    private ResponseEntity delete(@RequestParam String id) {
        courseService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get-by-id")
    private ResponseEntity getById(@RequestParam String id) {
        Course course = courseService.getById(id);
        return ResponseEntity.ok().body(course);
    }

}
