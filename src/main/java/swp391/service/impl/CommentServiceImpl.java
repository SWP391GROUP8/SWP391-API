package swp391.service.impl;

import org.springframework.stereotype.Service;
import swp391.dto.comment.ModifiCommentDto;
import swp391.entity.Comment;
import swp391.repository.BlogRepository;
import swp391.repository.CommentRepository;
import swp391.repository.UserRepository;
import swp391.service.CommentService;

import java.time.LocalDate;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    private UserRepository userRepository;
    private BlogRepository blogRepository;

    public CommentServiceImpl(CommentRepository commentRepository, UserRepository userRepository,BlogRepository blogRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.blogRepository=blogRepository;
    }

    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment create(ModifiCommentDto dto) {
        Comment comment = new Comment();
        comment.setContent(dto.getContent());
        comment.setUser(userRepository.getById(dto.getUserId()));
        comment.setCreateDate(LocalDate.now());
        comment.setBlog(blogRepository.getById(dto.getBlogId()));
        return commentRepository.save(comment);
    }

    @Override
    public void delete(String id) {
        commentRepository.deleteById(id);
    }
}
