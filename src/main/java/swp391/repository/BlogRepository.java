package swp391.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import swp391.entity.Blog;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
//.reaction, count (b.reaction)
    //@Query("SELECT b,MAX(b.reaction) FROM Blog b GROUP BY b ")
    @Query("SELECT b FROM Blog b WHERE b.reaction = (SELECT MAX(b.reaction) FROM Blog b)")
    List<Blog> countMostBlogsByReaction();


}
