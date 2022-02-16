package swp391.sercurity.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class LoginDto {

    @NotBlank(message = "Not blank")
    private String email;
    @NotBlank(message = "Not blank")
    private String password;
}
