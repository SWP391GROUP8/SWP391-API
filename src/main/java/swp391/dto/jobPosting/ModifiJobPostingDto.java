package swp391.dto.jobPosting;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;


@Data
public class ModifiJobPostingDto {
    private String id;

    private String title;

    private String content;

    private String status;

    private String userId;
}
