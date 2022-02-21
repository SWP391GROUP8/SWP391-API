package swp391.dto.user;

import lombok.Data;

@Data
public class ChangePasswordDto {
    private String email;
    private String oldPassword;
    private String newPassword;
    private String comfirmNewPassword;
}
