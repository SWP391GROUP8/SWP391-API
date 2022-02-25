package swp391.dto.course_qa;

import lombok.Data;

@Data
public class UpdateCourse_qa {
    private String title;
private Long id;
    private String content;

    private String courseId;
}
