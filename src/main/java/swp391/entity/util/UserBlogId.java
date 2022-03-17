package swp391.entity.util;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class UserBlogId implements Serializable {
    private String email;
    private Long blogId;
}
