package swp391.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swp391.entity.Blog;

@Repository
public interface BlogRepository extends JpaRepository<Blog,String> {

}
