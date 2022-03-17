package swp391.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import swp391.dto.comment.ModifiCommentDto;
import swp391.entity.Comment;
import swp391.service.CommentService;
import swp391.service.UserService;
import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    private CommentService commentService;
    private UserService userService;

    public CommentController(CommentService commentService, UserService userService) {
        this.commentService = commentService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity getAll() {
        List<Comment> commentList = commentService.getAll();
        return ResponseEntity.ok(commentList);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody ModifiCommentDto dto) {
        if (!userService.isExisted(dto.getUserId())) {
            return ResponseEntity.badRequest().body("User Id not found !");
        }
        Comment comment = commentService.create(dto);
        return ResponseEntity.ok(comment);
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestParam Long id) {
        commentService.delete(id);
        return ResponseEntity.ok("Successful");
    }
    @GetMapping("/get-by-blogId")
    public ResponseEntity getByBlogId(@RequestParam Long blogId){
        List<Comment> commentList = commentService.getByBlogID(blogId);
        return ResponseEntity.ok(commentList);
    }
    @GetMapping("/get-by-course-qaId")
    public ResponseEntity getByCourseQAId(@RequestParam Long qaId){
        List<Comment> commentList = commentService.getByQAId(qaId);
        return ResponseEntity.ok(commentList);
    }

}
