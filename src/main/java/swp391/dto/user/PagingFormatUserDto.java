package swp391.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import swp391.entity.User;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagingFormatUserDto<T> {

    int recordCount;
    T response;
//    private int pageNumber;
//
//    private int pageSize;
//
//    private Long totalRecordCount;
//
//    private List<User> records;
}
