package swp391.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import swp391.entity.File;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {



}
