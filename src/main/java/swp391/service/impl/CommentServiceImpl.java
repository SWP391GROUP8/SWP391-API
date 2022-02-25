package swp391.service.impl;

import org.springframework.stereotype.Service;
import swp391.dto.comment.ModifiCommentDto;
import swp391.entity.Comment;
import swp391.repository.BlogRepository;
import swp391.repository.CommentRepository;
import swp391.repository.Course_qaRepository;
import swp391.repository.UserRepository;
import swp391.service.CommentService;
import swp391.service.Course_QAService;

import java.time.LocalDate;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    private UserRepository userRepository;
    private BlogRepository blogRepository;
    private Course_qaRepository course_qaRepository;

    public CommentServiceImpl( Course_qaRepository course_qaRepository,CommentRepository commentRepository, UserRepository userRepository,BlogRepository blogRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.blogRepository=blogRepository;
        this.course_qaRepository=course_qaRepository;
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
        comment.setCourse_qa(course_qaRepository.getById(dto.getCouurse_qaId()));
        return commentRepository.save(comment);
    }

    @Override
    public void delete(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public List<Comment> getByBlogID(Long blogId) {
        return commentRepository.getCommentsByBlog_Id(blogId);
    }

    @Override
    public List<Comment> getByQAId(Long qaId) {
        return commentRepository.getCommentsByQAId(qaId);
    }
}
