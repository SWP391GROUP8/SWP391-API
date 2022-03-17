package swp391.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import swp391.entity.Blog;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {

    @Query("SELECT b FROM Blog b WHERE b.reaction = (SELECT MAX(b.reaction) FROM Blog b)")
    List<Blog> countMostBlogsByReaction();

//    @Query("SELECT b.reactionList. FROM Blog b join b.comments c join c.user u WHERE u.email=?1 and b.id=?1)")
//    Boolean getReaction(String email,String blogId);


}
