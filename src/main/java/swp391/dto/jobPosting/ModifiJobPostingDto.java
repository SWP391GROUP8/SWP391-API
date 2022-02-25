package swp391.dto.jobPosting;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.validation.constraints.Email;


@Data
public class ModifiJobPostingDto {
    private String id;

    private String title;

    private String content;

    private String status;
    private String city;

    private String address;

    @Email
    private String mail;

    private String phoneNumber;

    private String userId;
}
