package swp391.dto.blog;

import lombok.Data;

@Data
public class UpdateBlogDto {
    private Long id;

    private String title;

    private String content;

    private String status;
    private String author;
    private int reaction;
}
