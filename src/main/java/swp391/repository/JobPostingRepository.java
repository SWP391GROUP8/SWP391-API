package swp391.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swp391.entity.JobPosting;
@Repository
public interface JobPostingRepository extends JpaRepository<JobPosting,String> {
}
