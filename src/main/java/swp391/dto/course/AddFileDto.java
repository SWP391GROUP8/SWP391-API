package swp391.dto.course;

import lombok.Data;

import java.util.List;
@Data
public class AddFileDto {
    private String courseId;
    private List<Long> fileIdList;
}
