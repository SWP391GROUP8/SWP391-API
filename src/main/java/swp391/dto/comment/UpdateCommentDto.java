package swp391.dto.comment;

import lombok.Data;

@Data
public class UpdateCommentDto {
    private Long id;
    private String content;
    private String userId;
    private Long blogId;
    private Long couurse_qaId;
}
