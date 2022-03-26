package swp391.dto.comment;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;
@Data
public class AddSubComment {
    private Long commentId;
    private String contentSubComment;
    private String email;
    private Long blogId;
    private Long courseQAId;
}
