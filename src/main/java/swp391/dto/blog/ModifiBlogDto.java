package swp391.dto.blog;

import lombok.Data;



@Data
public class ModifiBlogDto {
    private String id;

    private String title;

    private String content;

    private String status;

    private int reaction;
}
