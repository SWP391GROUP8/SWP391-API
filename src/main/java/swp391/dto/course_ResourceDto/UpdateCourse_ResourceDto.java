package swp391.dto.course_ResourceDto;

import lombok.Data;

@Data
public class UpdateCourse_ResourceDto {
    private Long id;
    private String content;
    private Long fileId;
    private String courseId;
}
