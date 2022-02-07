package swp391.dto.course;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
public class CreateCourseDto {
    private String id;

    private String author;

    private String name;

    private String code;

    private String status;
}
