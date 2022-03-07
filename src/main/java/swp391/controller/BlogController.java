package swp391.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import swp391.dto.blog.ModifiBlogDto;
import swp391.dto.blog.UpdateBlogDto;
import swp391.entity.Blog;
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

        Blog blog = blogService.create(dto);

        return ResponseEntity.ok().body(blog);
    }

    @PutMapping
    private ResponseEntity update(@RequestBody UpdateBlogDto dto) {
        Blog blog = blogService.update(dto.getId(), dto);
        return ResponseEntity.ok().body(blog);
    }

    @DeleteMapping()
    private ResponseEntity delete(@RequestParam Long id) {
        blogService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get-by-id")
    private ResponseEntity getById(@RequestParam Long id) {
        Blog blog = blogService.getById(id);
        return ResponseEntity.ok().body(blog);
    }

    @PostMapping("/{blogId}")
    private ResponseEntity reaction(@PathVariable Long blogId) {
        int numOfReaction = blogService.reaction(blogId);
        return ResponseEntity.ok().body(numOfReaction);
    }

    @GetMapping("/ranking")
    private ResponseEntity ranking() {
        List<Blog> blogList = blogService.ranking();
        return ResponseEntity.ok().body(blogList);
    }
}
