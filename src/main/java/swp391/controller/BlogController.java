package swp391.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import swp391.dto.blog.ModifiBlogDto;
import swp391.dto.course.CreateCourseDto;
import swp391.entity.Blog;
import swp391.entity.Course;
import swp391.service.BlogService;

import java.util.List;

@RestController
@RequestMapping("/api/blog")
public class BlogController {
    private BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping
    private ResponseEntity getAll() {
        List<Blog> blogList = blogService.getAll();
        return ResponseEntity.ok().body(blogList);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody ModifiBlogDto dto) {
        if (blogService.isExisted(dto.getId())) {
            return ResponseEntity.badRequest().body("Blog Id is duplicated");
        }
        Blog blog = blogService.create(dto);

        return ResponseEntity.ok().body(blog);
    }
}
