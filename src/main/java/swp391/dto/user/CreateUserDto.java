package swp391.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;



import java.time.LocalDate;
@Data
public class CreateUserDto {

    private String email;

    private String password;

    private String confirmPassword;

    private String roleId;
}
