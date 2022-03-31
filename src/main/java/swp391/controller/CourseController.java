package swp391.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import swp391.dto.course.AddFileDto;
import swp391.dto.course.CreateCourseDto;

import swp391.entity.Course;
import swp391.service.CourseService;
import swp391.service.FileService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/course")
@CrossOrigin(origins = "http://localhost:4200")
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

    @GetMapping("/get-all")
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

    @PostMapping("/add-preCourse")
    private ResponseEntity addPreCourse(@RequestParam String courseId, @RequestParam String preCourseId) {
        courseService.addPreCourse(courseId, preCourseId);
        return ResponseEntity.ok().body("Successful");
    }

    @GetMapping("/get-PreCourse-by-course-id")
    private ResponseEntity getPreCourse(@RequestParam String courseId) {
        Set<Course> courseList = courseService.getPreCourse(courseId);
        return ResponseEntity.ok().body(courseList);
    }
}
