package swp391.dto.comment;

import lombok.Data;

@Data
public class ModifiCommentDto {
    private String content;
    private String userId;
    private Long blogId;
    private Long course_qaId;
}
