package swp391.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import swp391.entity.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> getCommentsByBlog_Id(Long blogId);

    @Query("select c " +
            "from Comment c " +
            "where c.course_qa.id =?1")
    List<Comment> getCommentsByQAId(Long qaId);


}
