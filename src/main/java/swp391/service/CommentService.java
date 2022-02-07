package swp391.service;

import swp391.dto.comment.ModifiCommentDto;
import swp391.entity.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getAll();

    Comment create(ModifiCommentDto dto);

    void delete(String id);
}
