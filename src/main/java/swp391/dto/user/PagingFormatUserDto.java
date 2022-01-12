package swp391.dto.user;

import lombok.Data;
import swp391.entity.User;

import java.util.List;

@Data
public class PagingFormatUserDto {
    private int pageNumber;

    private int pageSize;

    private Long totalRecordCount;

    private List<User> records;
}
