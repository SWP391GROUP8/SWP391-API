package swp391.service;

import swp391.dto.comment.AddSubComment;
import swp391.dto.comment.ModifiCommentDto;
import swp391.entity.Comment;

import java.util.List;
import java.util.Set;

public interface CommentService {
    List<Comment> getAll();

    Comment create(ModifiCommentDto dto);

    void delete(Long id);

    List<Comment> getByBlogID(Long blogId);

    List<Comment> getByQAId(Long qaId);

    void addSubComment(AddSubComment dto);

    Set<Comment> getSubCommentByCommentId(Long commentId);
}
