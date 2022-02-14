package swp391.dto.schedule;

import lombok.Data;

import java.util.List;
@Data
public class AddUserDto {
//    @FindCourseId
    private String scheduleId;
    private List<String> userIdList;
}
