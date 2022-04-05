package swp391.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import swp391.dto.course_qa.ModifiCourse_qa;
import swp391.dto.course_qa.UpdateCourse_qa;
import swp391.entity.Course_QA;
import swp391.service.CourseService;
import swp391.service.Course_QAService;

import java.util.List;

@RestController
@RequestMapping("/api/course-q&a")
@CrossOrigin(origins = "http://localhost:4200")
public class Course_QAController {
    private Course_QAService course_qaService;
    private CourseService courseService;

    public Course_QAController(Course_QAService course_qaService, CourseService courseService) {
        this.course_qaService = course_qaService;
        this.courseService = courseService;
    }

    @GetMapping
    private ResponseEntity getAll() {
        List<Course_QA> course_qaList = course_qaService.getAll();
        return ResponseEntity.ok().body(course_qaList);
    }

    @PostMapping("/create-course-q&a")
    public ResponseEntity create(@RequestBody ModifiCourse_qa dto) {

        if (!courseService.isExisted(dto.getCourseId())) {
            return ResponseEntity.badRequest().body("Course Id is not found");
        }
        Course_QA course_qa = course_qaService.create(dto);

        return ResponseEntity.ok().body(course_qa);
    }

    @PutMapping
    private ResponseEntity update(@RequestBody UpdateCourse_qa dto) {
        if (!courseService.isExisted(dto.getCourseId())) {
            return ResponseEntity.badRequest().body("Course Id is not found");
        }
        Course_QA course_qa = course_qaService.update(dto.getId(), dto);
        return ResponseEntity.ok().body(course_qa);
    }

    @DeleteMapping()
    private ResponseEntity delete(@RequestParam Long id) {
        course_qaService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get-by-id")
    private ResponseEntity getById(@RequestParam Long id) {
        Course_QA course_qa = course_qaService.getById(id);
        return ResponseEntity.ok().body(course_qa);
    }
    @GetMapping("/get-by-course-id")
    private ResponseEntity getByCourseId(@RequestParam String id) {
        List<Course_QA> course_qa = course_qaService.getByCourseId(id);
        return ResponseEntity.ok().body(course_qa);
    }
}
