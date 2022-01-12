package swp391.dto.role;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
public class CreateRoleDto {
    private String id;

    private String name;
}
